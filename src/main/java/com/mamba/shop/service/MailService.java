package com.mamba.shop.service;

import javax.mail.MessagingException;

public interface MailService{
/*
* Если id передан, то sendObject игнорируется
* если idOrder null id sendObject
* email почта получателя
* status 1 - письмо для оплаты на лицевой
*        2 - письмо админу после оплаты пользователем
*        3 - письмо клиенту что он успешно арендовал номер
* */
    void sendEmail(Object sendObject, String email, String idOrder, int status) throws ClassCastException, MessagingException;

}
