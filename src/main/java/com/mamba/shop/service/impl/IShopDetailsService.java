package com.mamba.shop.service.impl;

import com.mamba.shop.dao.ApartmentDao;
import com.mamba.shop.entity.Apartment;
import com.mamba.shop.entity.Period;
import com.mamba.shop.entity.custom_entity.SearchCustomModel;
import com.mamba.shop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class IShopDetailsService implements ShopService{

    private final ApartmentDao apartmentDao;

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Autowired
    public IShopDetailsService(ApartmentDao apartmentDao) {
        this.apartmentDao = apartmentDao;
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

}
