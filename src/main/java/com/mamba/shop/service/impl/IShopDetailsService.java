package com.mamba.shop.service.impl;

import com.mamba.shop.dao.ApartmentDao;
import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.Period;
import com.mamba.shop.entity.custom_entity.SearchCustomModel;
import com.mamba.shop.service.DownloadFile;
import com.mamba.shop.service.MailService;
import com.mamba.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class IShopDetailsService implements ShopService, MailService{

    private final ApartmentDao apartmentDao;
    private final JavaMailSender mailSender;
    private final DownloadFile downloadFile;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    public IShopDetailsService(ApartmentDao apartmentDao, JavaMailSender mailSender, DownloadFile downloadFile) {
        this.apartmentDao = apartmentDao;
        this.mailSender = mailSender;
        this.downloadFile = downloadFile;
    }

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

    @Override
    public List<Apartment> getAllApartments() {
        return apartmentDao.getAllApartmentListWithDependency();
    }

    @Override
    public Apartment getByIdWithDependency(String id) {
        return apartmentDao.findByIdWithDependency(id);
    }

    @Override
    public Apartment getById(String id) {
        return null;
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

    }

    @Override
    public boolean refreshDataBaseDate(String idApartment) {
        try {
            refresh(idApartment);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    //проверяет входимость одного периода в другой
    private boolean checkPeriodApartment(Apartment apartment, Date thisDate_in, Date thisDate_out){
        if (apartment.getPeriods().size() == 0) return true;
        for (Period periodArchive :
                apartment.getPeriods()) {
            Date archiveDateIn = periodArchive.getDate_in();
            Date archiveDateOut = periodArchive.getDate_out();
            if (thisDate_in.before(archiveDateIn) && thisDate_out.before(archiveDateIn)){
                return true;
            }
            else if (thisDate_in.after(archiveDateOut) && thisDate_out.after(archiveDateOut)){
                return true;
            }
        }
        return false;
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
            messageHelper.setSubject("This message is me! \n apartament id= " + apartmentSend.getId());
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }

    private void refresh(String id) throws IOException {
        Apartment apartment = getById(id);
        downloadFile.saveFile("room32" + id, apartment.getId(/*здесь должен быть урл*/));
        List<Period> periods = downloadFile.listenCalendarICS("room32" + id);

        for (Period prd :
                periods) {
            if (equalsDatePeriods(apartment, prd))
            {
                //add period in this apartment
            }
        }

    }
    private boolean equalsDatePeriods(Apartment apartment, Period periodBooking){
        return false;
    }
}
