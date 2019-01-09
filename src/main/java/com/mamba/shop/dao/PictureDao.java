package com.mamba.shop.dao;

import com.mamba.shop.entity.Picture;

import java.util.List;

public interface PictureDao {
    void addPicture(Picture picture);
    void deletePicture(Picture picture);
    List<Picture> getAllPictureByApartment(String idApartment);
    Picture getPictureById(int id);
}
