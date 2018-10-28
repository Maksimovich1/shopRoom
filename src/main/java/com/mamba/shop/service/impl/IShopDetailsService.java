package com.mamba.shop.service.impl;

import com.mamba.shop.dao.ApartmentDao;
import com.mamba.shop.dao.OrderDao;
import com.mamba.shop.dao.PictureDao;
import com.mamba.shop.dao.UserDetailsDao;
import com.mamba.shop.entity.*;
import com.mamba.shop.entity.custom_entity.SearchCustomModel;
import com.mamba.shop.service.DownloadFile;
import com.mamba.shop.service.MailService;
import com.mamba.shop.service.ShopService;
import com.mamba.shop.service.uploadFile.FileBucket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class IShopDetailsService implements ShopService, MailService{

    private final ApartmentDao apartmentDao;
    private final JavaMailSender mailSender;
    private final DownloadFile downloadFile;
    private final UserDetailsDao userDetailsDao;
    private final OrderDao orderDao;
    private final PictureDao pictureDao;

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    public IShopDetailsService(ApartmentDao apartmentDao, JavaMailSender mailSender,
                               DownloadFile downloadFile, UserDetailsDao userDetailsDao,
                               OrderDao orderDao, PictureDao pictureDao) {
        this.apartmentDao = apartmentDao;
        this.mailSender = mailSender;
        this.downloadFile = downloadFile;
        this.userDetailsDao = userDetailsDao;
        this.orderDao = orderDao;
        this.pictureDao = pictureDao;
    }
    @Transactional(readOnly = true)
    @Override
    public List<Apartment> searchFreeApartmentsWithDependency(
            String countPeople, String countChild,
            String district, String priceMax,
            String dateIn, String dateOut, String bedroom) {

        SearchCustomModel model = null;
        try {
            model = new SearchCustomModel(
                    Integer.parseInt(bedroom), Integer.parseInt(countPeople),
                    Integer.parseInt(countChild), Integer.parseInt(priceMax),
                    Integer.parseInt(district),
                    new SimpleDateFormat(DATE_FORMAT).parse(dateIn),
                    new SimpleDateFormat(DATE_FORMAT).parse(dateOut));
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Не удалось преобразовать дату в сервисе.");
        }

        List<Apartment> listResult = new ArrayList<>();
        List<Apartment> listResultDao = apartmentDao.getAllFreeApartmentsBySearchCustomModelWithDependency(model);
        for (Apartment apartment :
                listResultDao) {
            assert model != null;
            if (checkPeriodApartment(apartment, model.getSearch_date_in(), model.getSearch_date_out()))
                listResult.add(apartment);
        }

        return listResult;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Apartment> getAllApartments() {
        return apartmentDao.getAllApartmentListWithDependency();
    }
    @Transactional(readOnly = true)
    @Override
    public Apartment getByIdWithDependency(String id) {
        return apartmentDao.findByIdWithDependency(id);
    }
    @Transactional(readOnly = true)
    @Override
    public Apartment getById(String id) {
        return apartmentDao.findById(id);
    }
    @Override
    public void addApartment(Apartment apartment) {
        apartmentDao.addApartment(apartment);
    }
    @Override
    public void deleteApartment(String id) {
        apartmentDao.deleteApartment(apartmentDao.findById(id));
    }
    @Override
    public void updateApartment(Apartment apartment) {
        apartmentDao.updateApartment(apartment);
    }

    //проверяет входимость одного периода в другой ,true значит период уникальный
    private boolean checkPeriodApartment(Apartment apartment, Date thisDate_in, Date thisDate_out){
        if (apartment.getPeriods().size() == 0) return true;
        int countEquals = apartment.getPeriods().size();
        for (Period periodArchive :
                apartment.getPeriods()) {
            countEquals--;
            Date archiveDateIn = periodArchive.getDate_in();
            Date archiveDateOut = periodArchive.getDate_out();
            if (thisDate_in.before(archiveDateIn) && thisDate_out.before(archiveDateIn)){

                if (countEquals <= 0)
                return true;
            }
            else if (thisDate_in.after(archiveDateOut) && thisDate_out.after(archiveDateOut)){
                if (countEquals <= 0)
                return true;
            }
            else return false;
        }
        return false;
    }


    /*Запуск по расписанию обновления бд, каждые 10 минут */
    @Override
    @Scheduled(cron = "0 */10 * * * *")
    public void console(){
        System.out.println("######## timer! This method execute every 10 min");
        List<Apartment> apartments = getAllApartments();
        refreshDataBaseDate(apartments);
        System.out.println("##### finish refresh!");
    }

    /*обновление бд*/

    private void refresh(String id) throws IOException {
        Apartment apartment = getByIdWithDependency(id);
        if (apartment.getUrlBooking() != null && !apartment.getUrlBooking().equals("")) {
            downloadFile.saveFile("room32" + id, apartment.getUrlBooking());
            List<Period> periods = downloadFile.listenCalendarICS("room32" + id);

            for (Period prd :
                    periods) {
                if (equalsDatePeriods(apartment, prd)) {
                    //add period in this apartment
                    apartment.getPeriods().add(prd);
                    apartmentDao.updateApartment(apartment);
                    System.out.println("####Период был уникальный для этого апартамента id:  = "
                            + apartment.getId() + "period = " + prd.toString());
                } else
                    System.out.println("В данном апартаменте id =" + apartment.getId() + ": присутствует period = " + prd);
            }
        }
        else System.out.println("Нет урла в апартаменте");
    }
    private boolean equalsDatePeriods(Apartment apartment, Period periodBooking){
        return checkPeriodApartment(apartment, periodBooking.getDate_in(), periodBooking.getDate_out());
    }

    @Override
    public boolean refreshDataBaseDate(List<Apartment> apartments) {
        try {
            for (Apartment apartment :
                    apartments) {
                System.out.println("Идет синхронизация id: " + apartment.getId());
                refresh(apartment.getId());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setBufferUrlBooking(String idApartment) {
        String url = "http://localhost:8080/get.download/jsd1134is6chd_uhc_sid/sdcs32dvg2222112/" + idApartment +"5987412365/bzs123fff_gbc)ss";
        StringSelection selection = new StringSelection(url);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        System.out.println("#___________copy");
    }

    @Override
    public void downloadMyCalendar() {

    }

    @Override
    public int getCountOrderDay(String _dateIn, String _dateOut) {
        int count = 0;
        try {
            Date dateIn = new SimpleDateFormat(DATE_FORMAT).parse(_dateIn);
            Date dateOut = new SimpleDateFormat(DATE_FORMAT).parse(_dateOut);

            long dayIn = dateIn.getTime();
            long dayOut = dateOut.getTime();
            long qwe = dayOut - dayIn;
            count = (int) (qwe/86400000);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Не удалось преоброзавать дату из строки!");
        }
        return count;
    }

    @Transactional(readOnly = true)
    @Override
    public User getCurrentUser() throws NotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null)
            throw new NotFoundException("");

        Object obj = authentication.getPrincipal();
        String username;

        if (obj instanceof UserDetails)
            username = ((UserDetails) obj).getUsername();
        else
            username = obj.toString();
        User user = userDetailsDao.findUserByUsername(username);
        System.out.println("user = " + username);
        System.out.println(user.toString());
        return user;
    }

    @Override
    public void sendEmail(Object sendObject, String email) {
        if (!(sendObject instanceof Apartment))
            throw new ClassCastException("Передан не апартамент");
        Apartment apartmentSend = (Apartment) sendObject;

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,false,"utf-8");
            String htmlMsg = "<h3> Hello world</h3>";
            mimeMessage.setContent(htmlMsg, "text/html");
            messageHelper.setTo(email);
            messageHelper.setSubject("This message is me! \n apartment id= " + apartmentSend.getId());
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    @Transactional
    @Override
    public void setCompleteOrder(Orders order, String apartmentId, Period period, String username){
        if (order != null) {
            downloadFile.writeCalendar("\\room234" + apartmentId + ".ics", period,
                    username, "Europe/Moscow");
            Apartment apartment = apartmentDao.findByIdWithDependency(apartmentId);
            apartment.getPeriods().add(period);
            apartmentDao.updateApartment(apartment);
            orderDao.addOrder(order);
        }
    }

    @Override
    public Orders createOrder(String email, String nameUser, String dateOrder,
                            Date dateIn, Date dateOut, String apartmentId,
                            int status, String summary) {
        Date date;
        Orders order = new Orders();
        try {
            date = new SimpleDateFormat(IShopDetailsService.DATE_FORMAT).parse(dateOrder);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        order.setCustomer_email(email);
        order.setCustomer_name(nameUser);
        order.setDate_order(date);
        order.setDate_in(dateIn);
        order.setDate_out(dateOut);
        order.setId_product_buy(apartmentId);
        order.setPrice(summary);
        order.setStatus(status);
        return order;
    }

    @Override
    public void confirmOrderPaymentStatus(String idOrder, int status) {
        Orders orders = orderDao.getOrderById(idOrder);
        orders.setStatus(status);
        System.out.println("### установлен статус " + status);
        orderDao.updateOrder(orders);
    }

    @Override
    public List<Orders> getAllOrdersForDate(Date dateBefore) {
        return orderDao.getAllOrdersForDate(dateBefore);
    }

    @Override
    public List<Orders> getAllOrdersActive(int status) {
        return null;
    }

    @Override
    public List<Orders> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Orders> getOrderByUsername(String username) {
        return orderDao.getOrderByUsername(username);
    }

    @Override
    public void deleteOrder(String orderId) {
        orderDao.deleteOrder(orderDao.getOrderById(orderId));
    }

    @Override
    public void updateStatusOrders(Orders order) {

    }

    ////////////////////////////////////////
    @Override
    public void saveImageForApartment(CommonsMultipartFile[] file, String idApartment) {

        Apartment apartment = apartmentDao.findById(idApartment);
        System.out.println("Запись картинки в бд.________");
        if ((file != null) && (file.length > 0)){
            for (CommonsMultipartFile multipartFile: file){
                if (multipartFile.isEmpty()){
                    System.out.println("name = " + multipartFile.getOriginalFilename());
                    if (!multipartFile.getOriginalFilename().equals("")){
                        Picture picture = new Picture();
                        picture.setPict(multipartFile.getBytes());
                        picture.setApartment(apartment);
                        pictureDao.addPicture(picture);
                        System.out.println("Картинка успешно добавлена!--------");
                    }
                }
            }// конец цикла
        }else {
            System.out.println("____переданный массив из байтов пуст, null");
        }

    }
}
