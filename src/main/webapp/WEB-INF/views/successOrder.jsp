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
</head>
<body>
<c:if test="${createNewOrder == 1}">
    <h1>Поздравляем! На указанный вами Email было выслано письмо по оплате.
        <br>Мы не поддерживаем оплату через сервисы, для Вашей же безопастности.
        <br>На вдладке "Мои заказы" после оплаты подтвердите заказ. Спасибо.</h1>
    <a href="<c:url value="/secure/my_order"/>">Перейти к Мои заказы</a>
</c:if>
<c:if test="${createNewOrder == 2}">
    <h1>На эти даты апартаменты был арендован другим пользователем</h1>
</c:if>
<c:if test="${createNewOrder == 3}">
    <h1>Ошибка отправки сообщения</h1>
</c:if>
<c:if test="${createNewOrder == 4}">
    <h1>Неизвестная ошибка</h1>
</c:if>
</body>
</html>