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
<h1>Поздравляем! На указанный вами Email было выслано письмо по оплате.
    <br>Мы не поддерживаем оплату через сервисы, для Вашей же безопастности.
    <br>На вдладке "Мои заказы" после оплаты подтвердите заказ. Спасибо.</h1>
<a href="<c:url value="/secure/my_order"/>">Перейти к Мои заказы</a>
</body>
</html>
