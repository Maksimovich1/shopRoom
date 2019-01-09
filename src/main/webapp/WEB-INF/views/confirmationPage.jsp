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
<body style="background-color: gainsboro">
<div class="container">
    <h2 style="margin-left: 42%">Проверка:</h2>
    <div class="panel panel-default marg">
        <%--<div class="panel-body form-inline"><h4>Apartment: ${paymentCustomModel.getApartmentName()}</h4></div>
        <div class="panel-body"><h4>Дата оформления аренды: ${dateNow}</h4></div>
        <div class="panel-body"><h4>Период проживания: { ${paymentCustomModel.getDateIn()} : ${paymentCustomModel.getDateOut() } }</h4></div>
        <div class="panel-body"><h4>Количество ночей: ${paymentCustomModel.getCountNight()}</h4></div>
        <div class="panel-body"><h4>Цена за 1 ночь: ${apartment.getPrice()}</h4></div>
        <div class="panel-body">
            <h4>Способ оплаты:
                <c:if test="${sessionScope.get('paymentMethod').equals('1')}" >
                    credit payment
                </c:if>
                <c:if test="${sessionScope.get('paymentMethod').equals('2')}" >
                    full payment
                </c:if>
                <c:if test="${!sessionScope.get('paymentMethod').equals('1') && !sessionScope.get('paymentMethod').equals('2')}" >
                    full payment
                </c:if>
            </h4>
        </div>--%>
        <table class="table">
            <tbody>
            <tr>
                <th scope="row">Название апартамента</th>
                <td>${paymentCustomModel.getApartmentName()}</td>
            </tr>
            <tr>
                <th scope="row">Дата оформления заказа</th>
                <td>${dateNow}</td>
            </tr>
            <tr>
                <th scope="row">Период проживания</th>
                <td>с ${paymentCustomModel.getDateIn()}  по ${paymentCustomModel.getDateOut() } </td>
            </tr>
            <tr>
                <th scope="row">Количество ночей</th>
                <td>${paymentCustomModel.getCountNight()}</td>
            </tr>
            <tr>
                <th scope="row">Цена за 1 ночь</th>
                <td>${apartment.getPrice()}</td>
            </tr>
            <tr>
                <th scope="row">Способ оплаты</th>
                <td>
                    <c:if test="${sessionScope.get('paymentMethod').equals('1')}" >
                        Предоплата ${paymentCustomModel.getPledge()} euro
                    </c:if>
                    <c:if test="${sessionScope.get('paymentMethod').equals('2')}" >
                        Предоплата ${paymentCustomModel.getPledge()} euro
                    </c:if>
                    <c:if test="${!sessionScope.get('paymentMethod').equals('1') && !sessionScope.get('paymentMethod').equals('2')}" >
                        Предоплата ${paymentCustomModel.getPledge()} euro
                    </c:if>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="panel-footer"><h2>Итого: ${paymentCustomModel.getSummary()} $</h2></div>
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
                <input name="apartment" type="hidden" value="${apartment.getId()}">
                <input name="dateIn" type="hidden" value="${paymentCustomModel.getDateIn()}">
                <input name="dateOut" type="hidden" value="${paymentCustomModel.getDateOut()}">
                <button type="submit" class="btn btn-default">Оформить заказ</button>
            </form>
            <a href="<c:url value="/secure/product"/>" class="btn btn-danger">Вернуться</a>
        </div>
    </div>
</div>
<br>

</body>
</html>