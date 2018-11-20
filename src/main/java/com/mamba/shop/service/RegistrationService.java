package com.mamba.shop.service;




public interface RegistrationService {

    void regUser(String username, String pass, String email,
                    String phone, String service_cod)
            throws IllegalAccessException, IllegalArgumentException;

}
