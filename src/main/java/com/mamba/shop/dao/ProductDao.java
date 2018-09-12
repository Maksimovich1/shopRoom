package com.mamba.shop.dao;

import com.mamba.shop.entity.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getAllProduct();
    List<Product> getProductForParam(int countPeople, int countChild, double priceMin, double priceMax);

    void addProduct(Product product);
    void deleteProduct(Product product);
    void updateProduct(Product product);
}
