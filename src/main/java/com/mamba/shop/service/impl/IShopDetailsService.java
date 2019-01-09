package com.mamba.shop.service.impl;

import com.mamba.shop.dao.*;
import com.mamba.shop.entity.*;
import com.mamba.shop.entity.custom_entity.CustomPricesModel;
import com.mamba.shop.entity.custom_entity.PeriodLongCustomModel;
import com.mamba.shop.entity.custom_entity.SearchCustomModel;
import com.mamba.shop.service.DownloadFile;
import com.mamba.shop.service.MailService;
import com.mamba.shop.service.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class IShopDetailsService implements ShopService, MailService{

    private final ApartmentDao apartmentDao;
    private final JavaMailSender mailSender;
    private final DownloadFile downloadFile;
    private final UserDetailsDao userDetailsDao;
    private final OrderDao orderDao;
    private final PictureDao pictureDao;
    private final PeriodDao periodDao;
    private final PriceForPeriodDao priceForPeriodDao;

    @Value(value = "${app.service_shop.host}")
    private String host;
    @Value(value = "${app.service_shop.url_before}")
    private String url_before;
    @Value(value = "${app.service_shop.url_after}")
    private String url_after;

    @Value(value = "${app.controllerAccess.district1}")
    private String district1;
    @Value(value = "${app.controllerAccess.district2}")
    private String district2;
    @Value(value = "${app.controllerAccess.district3}")
    private String district3;
    @Value(value = "${app.controllerAccess.district4}")
    private String district4;
    @Value(value = "${app.controllerAccess.district5}")
    private String district5;
    @Value(value = "${app.controllerAccess.district6}")
    private String district6;

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    public IShopDetailsService(ApartmentDao apartmentDao, JavaMailSender mailSender,
                               DownloadFile downloadFile, UserDetailsDao userDetailsDao,
                               OrderDao orderDao, PictureDao pictureDao, PeriodDao periodDao,
                               PriceForPeriodDao priceForPeriodDao) {
        this.apartmentDao = apartmentDao;
        this.mailSender = mailSender;
        this.downloadFile = downloadFile;
        this.userDetailsDao = userDetailsDao;
        this.orderDao = orderDao;
        this.pictureDao = pictureDao;
        this.periodDao = periodDao;
        this.priceForPeriodDao = priceForPeriodDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Apartment> searchFreeApartmentsWithDependency(
            String countPeople, String countChild,
            String district, String priceMax,
            String dateIn, String dateOut, String bedroom) throws ParseException, NumberFormatException {

        SearchCustomModel model;

            model = new SearchCustomModel(
                    Integer.parseInt(bedroom), Integer.parseInt(countPeople),
                    Integer.parseInt(countChild), Integer.parseInt(priceMax),
                    Integer.parseInt(district),
                    new SimpleDateFormat(DATE_FORMAT).parse(dateIn),
                    new SimpleDateFormat(DATE_FORMAT).parse(dateOut));

        List<Apartment> listResult = new ArrayList<>();
        List<Apartment> listResultDao = apartmentDao.getAllFreeApartmentsBySearchCustomModelWithDependency(model);
        for (Apartment apartment :
                listResultDao) {
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
    public void addApartment(Apartment apartment) throws IllegalArgumentException {
        if (apartmentDao.findById(apartment.getId()) != null)
            throw new IllegalArgumentException("Апартамент с таким номером существует " + apartment.getId());
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

    @Override
    public boolean checkFreeApartmentForDate(String apartmentId, Date in, Date out) {
        return checkPeriodApartment(apartmentDao.findByIdWithDependency(apartmentId), in, out);
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
    public  void console(){
        System.out.println("######## timer! This method execute every 10 min");
        //List<Apartment> apartments = getAllApartments();
        List<Apartment> apartments = apartmentDao.getAllApartmentListWithDependency();
        if (apartments == null){
            System.out.println("Апартаментов не найдено для обновления!");
        }
        else {
            refreshDataBaseDate(apartments);
            System.out.println("##### finish refresh!");
        }
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
                    /*apartment.getPeriods().add(prd);
                    apartmentDao.updateApartment(apartment);*/
                    apartmentDao.addPeriodInApartment(prd, apartment.getId());
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
    public String setBufferUrlBooking(String idApartment) {
        /* StringSelection selection = new StringSelection(url);

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
        System.out.println("#___________copy");*/
       return host + url_before + idApartment + url_after;
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
    public User getUserByUserName(String userName) {
        return userDetailsDao.findUserByUsername(userName);
    }

    @Override
    public void sendEmail(Object sendObject, String email, String idOrder, int status) throws ClassCastException, MessagingException {
        Orders orderSend;
        if (idOrder == null){
        if (!(sendObject instanceof Orders))
            throw new ClassCastException("Передан не заказ");
        orderSend = (Orders) sendObject;}
        else {
            orderSend = orderDao.getOrderById(idOrder);
        }
        String htmlMsg = "";
        String bank_book = "1233123312331233";
        switch (status){
            case 1:
                htmlMsg = "<h1>Dear " + orderSend.getCustomer_name() + "<h1><br>" +
                        "<h3>You have already book the apartment " + orderSend.getId_product_buy() +
                        " from " + new SimpleDateFormat(DATE_FORMAT).format(orderSend.getDate_in()) + " to " + new SimpleDateFormat(DATE_FORMAT).format(orderSend.getDate_out()) + "</h3><br>" +
                        "To end your booking you have to make a payment of 200 euro on the account" +
                        " " + bank_book + "<br>" +
                        "The apartment is holded for you during this 1 hour.";
                break;
            case 2:
                htmlMsg = "<h1>Username - " + orderSend.getCustomer_name() + " payment order numb. " + orderSend.getId_order() + "<h1><br>" +
                        "<h3>You check complete payment<br> Order<br> № apartment" + orderSend.getId_product_buy() +
                        " <br> from: " + new SimpleDateFormat(DATE_FORMAT).format(orderSend.getDate_in()) +
                        " <br> to: " + new SimpleDateFormat(DATE_FORMAT).format(orderSend.getDate_out()) +
                        " <br> price - " + orderSend.getPrice() +
                        "</h3>";
                break;
            case 3:
                htmlMsg = "<h1>Dear " + orderSend.getCustomer_name() + "<h1><br>" +
                        "<h3>You success book apartment from " + new SimpleDateFormat(DATE_FORMAT).format(orderSend.getDate_in()) +
                        "to " + new SimpleDateFormat(DATE_FORMAT).format(orderSend.getDate_out()) +
                        "</h3>";
                break;
        }
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            try {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,false,"utf-8");

            mimeMessage.setContent(htmlMsg, "text/html");
            messageHelper.setTo(email);
            messageHelper.setSubject("TENERIFE-PERFECT");
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new MessagingException("Письмо не отправлено.");
        }

    }

    @Override
    public void setCompleteOrder(Orders order, String apartmentId, Period period, String username){
        if (order != null) {
//            downloadFile.writeCalendar("\\room234" + apartmentId + ".ics", period,
//                    username, "Europe/Moscow");
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
            date.setTime(date.getTime() + 54000000); // + 15 hours
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
        order.setPrice(Integer.parseInt(summary));
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
    public Orders getOrderById(String id) {
        return orderDao.getOrderById(id);
    }

    @Override
    public int deleteOrder(String orderId, String userRole) {
        int penya = 0;
        Orders order = orderDao.getOrderById(orderId);
        String aparId = order.getId_product_buy();
        Period periodOrder = new Period();
        periodOrder.setDate_in(order.getDate_in());
        periodOrder.setDate_out(order.getDate_out());
        Apartment apartment = apartmentDao.findByIdWithDependency(aparId);
        int idPeriodRemove = -1;
        for (Period period :
                apartment.getPeriods()) {
            if (equalsDate(periodOrder.getDate_in(), period.getDate_in())
                    && equalsDate(periodOrder.getDate_out(), period.getDate_out())){
                apartment.getPeriods().remove(period);
                idPeriodRemove = period.getId();
                break;
            }
        }
        if (idPeriodRemove < 0){
            System.out.println("Не найден удаляемый период");
        }
        else {
            if (userRole.equals("user"))
                penya = (int) (order.getPledge() * 0.1);
            penya = order.getPledge() - penya;
            periodDao.deletePeriod(periodDao.getById(idPeriodRemove));
            orderDao.deleteOrder(order);
        }
        return penya;
    }

    ////////////////////////////////////////
    @Override
    public void saveImageForApartment(MultipartFile file, String idApartment, String pathNewImage) throws IOException {
        Apartment apartment = apartmentDao.findByIdWithDependency(idApartment);
        System.out.println("Запись пути картинки в бд.________");
        Picture picture = new Picture();
        picture.setApartment(apartment);
        picture.setPict(pathNewImage);
        apartment.getPictures().add(picture);
        File transferFile = new File(pathNewImage);
        if (!transferFile.exists()){ // проверка на существование папки
            boolean result = transferFile.mkdirs();
            if (result)
                System.out.println("create new folder");
        }
        file.transferTo(transferFile);
    }

    @Override
    public boolean deletePicture(int id) {
        Picture picture = pictureDao.getPictureById(id);
        String path = picture.getPict();
        if (new File(path).delete()){
            pictureDao.deletePicture(picture);
            return true;
        }
        return false;
    }

    @Override
    public byte[] getImageForApartment(String path) throws IOException {
        BufferedImage image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] imageInByte;
        File file = new File(path);
        System.out.println(file.getAbsolutePath());
        image = ImageIO.read(file);
        ImageIO.write( image, "jpg", baos );
        baos.flush();
        imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

    @Override
    public Picture getPicture(int id) {
        return pictureDao.getPictureById(id);
    }

    ///////////////////////
    @Override
    public void addNewPrice(String name, String begin, String end, String price, String idApartment) throws ParseException {
        long beginDate = new SimpleDateFormat(DATE_FORMAT).parse(begin).getTime();
        long endDate = new SimpleDateFormat(DATE_FORMAT).parse(end).getTime();
        PriceForThePeriod priceForThePeriod =
                new PriceForThePeriod(name, beginDate, endDate, price, idApartment);
        priceForPeriodDao.addPriceForPeriod(priceForThePeriod);
    }

    @Override
    public List<CustomPricesModel> setPriceForThePeriod(Apartment apartment, String in, String out) throws ParseException {

        String idApartment = apartment.getId();
        List<CustomPricesModel> customPricesModelList = new ArrayList<>();
        List<PriceForThePeriod> listPricesForThePeriod = priceForPeriodDao.getPriceForThePeriodByIdApartment(idApartment);
        long dateIn = new SimpleDateFormat(DATE_FORMAT).parse(in).getTime();
        long dateOut = new SimpleDateFormat(DATE_FORMAT).parse(out).getTime();
        long hour24 = 86400000;

        for (long i = dateIn + hour24; i <= dateOut; i += hour24){
            System.out.println("Проверяем эту дату" +  new SimpleDateFormat(DATE_FORMAT).format(new Date(i)));
            customPricesModelList.add(isEntersThisDay(i, listPricesForThePeriod, apartment));
        }

        return customPricesModelList;
    }

    @Override
    public String getNameDistrict(int id) {
        String district;
        switch (id){
            case 1:
                district = district1;
                break;
            case 2:
                district = district2;
                break;
            case 3:
                district = district3;
                break;
            case 4:
                district = district4;
                break;
            case 5:
                district = district5;
                break;
            case 6:
                district = district6;
                break;
            default:
                district = district1;
        }
        return district;
    }

    @Override
    public boolean hasThePriceChanged(List<CustomPricesModel> pricesModelList) {
        for (CustomPricesModel cpm :
                pricesModelList) {
            if (cpm.isFlag())
                return true;
        }
        return false;
    }

        /*
        * false старая цена
        * */
    private CustomPricesModel isEntersThisDay(long dateThis, List<PriceForThePeriod> listPricesForThePeriod, Apartment apartment){
        String datestr = new SimpleDateFormat(DATE_FORMAT).format(new Date(dateThis));
        for (PriceForThePeriod pr :
                listPricesForThePeriod) {
            if (dateThis >= pr.getBegin_period() && dateThis <= pr.getEnd_period())
                return new CustomPricesModel(
                        datestr,
                        pr.getPriceOfPeriod(),
                        true
                );
        }
        return new CustomPricesModel(
                datestr,
                String.valueOf(apartment.getPrice()),
                false
        );
    }

    private boolean equalsDate(Date date1, Date date2){
        Calendar calendar1 = Calendar.getInstance();
        Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }
}
