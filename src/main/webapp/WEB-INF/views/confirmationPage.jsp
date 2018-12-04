<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 11.10.2018
  Time: 8:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Confirm</title>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/style.css" rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2 style="margin-left: 42%">Проверка:</h2>
    <div class="panel panel-default marg">
        <div class="panel-body form-inline"><h4>№ апартамента: ${id}</h4></div>
        <div class="panel-body"><h4>Дата оформления аренды: ${dateNow}</h4></div>
        <div class="panel-body"><h4>Период проживания: ${period_str}</h4></div>
        <div class="panel-body"><h4>Количество ночей: ${countNight}</h4></div>
        <div class="panel-body"><h4>Цена за 1 ночь: ${apartment.getPrice()}</h4></div>
        <div class="panel-footer"><h2>Итого: ${summary} $</h2></div>
        <div class="panel-footer">
            <form action="${pageContext.request.contextPath}/secure/complete" method="post" class="form-inline">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" value="${email}">
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" class="form-control" id="phone" placeholder="Enter phone" name="phone">
                </div>
                <input name="apartment" type="hidden" value="${id}">
                <button type="submit" class="btn btn-default">Оформить заказ</button>
            </form>
            <a href="<c:url value="/secure/product"/>" class="btn btn-danger">Вернуться</a>
        </div>
    </div>
</div>
<br>

<%--<form action="${pageContext.request.contextPath}/payment" method="POST">--%>
    <%--<script th:inline="javascript"--%>
            <%--src="https://checkout.stripe.com/checkout.js" class="stripe-button"--%>
            <%--data-key="pk_test_sjBV4ZqrUCQ6YZjiAC4eufFN"--%>
            <%--data-currency="eur"--%>
            <%--data-amount="100"--%>
            <%--data-name="Payment"--%>
            <%--data-description="Demo"--%>
            <%--data-image="/images/ava.jpg"--%>
            <%--data-locale="auto">--%>
    <%--</script>--%>
<%--</form>--%>
</body>
</html>
