<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.10.2018
  Time: 15:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="static/style.css" rel="stylesheet">
</head>
<body>
<c:if test="${createNewOrder == 1}">
    <div class="successOrderDiv">
        <h1>Поздравляем! На указанный вами Email было выслано письмо о совершенной Вами брони.
            <br>Мы не поддерживаем оплату через сервисы, для Вашей же безопастности.
            <br>На вдладке "Мои заказы" можете просмотреть свои заказы. Спасибо.</h1>
        <a class="successOrderA" href="<c:url value="/secure/my_order"/>">Перейти к Мои заказы</a>
    </div>

</c:if>
<c:if test="${createNewOrder == 2}">
    <h1 style="background-color: red">На эти даты апартаменты был арендован другим пользователем</h1>
    <a class="successOrderA" href="<c:url value="/secure/product"/>">Вернуться</a>
</c:if>
<c:if test="${createNewOrder == 3}">
    <h1 style="background-color: orange">Ошибка отправки сообщения</h1>
    <div class="successOrderDiv">
        <h1>Поздравляем! На указанный вами Email было выслано письмо о совершенной Вами брони.
            <br>Мы не поддерживаем оплату через сервисы, для Вашей же безопастности.
            <br>На вдладке "Мои заказы" можете просмотреть свои заказы. Спасибо.
            <br>Письмо не было отправленно, свяжитесь с нами, отправьте номер заказа .
        </h1>
        <a class="successOrderA" href="<c:url value="/secure/my_order"/>">Перейти к Мои заказы</a>
    </div>
</c:if>
<c:if test="${createNewOrder == 4}">
    <h1 style="background-color: red;  border-radius: 3px">Неизвестная ошибка на сервере. Попробуйте позже</h1>
</c:if>
<c:if test="${createNewOrder == 5}">
    <h1 style="background-color: #ffc208; border-radius: 3px">Не достаточно средств для совершения брони.</h1>
    <a class="successOrderA" href="<c:url value="/secure/product"/>">Вернуться</a>
</c:if>
</body>
</html>