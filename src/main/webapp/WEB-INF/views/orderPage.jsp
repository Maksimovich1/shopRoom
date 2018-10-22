<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 18.10.2018
  Time: 9:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="static/style.css" rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Order</title>
</head>
<body class="bacgr">
<jsp:include page="_navbar.jsp"/>
<div class="container">
    <c:if test="${orders != null && orders.size() != 0 }">
    <table class="table table-hover table-inverse">
        <thead>
        <tr>
            <th>№ заказа</th>
            <th>Дата заказа</th>
            <th>Период проживания</th>
            <th>Номер комнаты</th>
            <th>Сумма</th>
            <th>Статус оплаты</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <th scope="row">

                        Заказ №${order.getId_order()}
                </th>
                <td>${order.getDate_order()}</td>
                <td>C: ${order.getDate_in()} по: ${order.getDate_out()}</td>
                <td>Room №${order.getId_product_buy()}</td>
                <td>${order.getPrice()}</td>
                <td>
                    <c:if test="${order.getStatus() == 1}">
                        <div class="alert alert-success">
                            <strong>Оплачен</strong>
                        </div>
                    </c:if>
                    <c:if test="${order.getStatus() == 0}">
                        <div class="alert alert-danger">
                            <strong>Не оплачен</strong>
                        </div>
                    </c:if>
                </td>
                </tr>
        </c:forEach>
        </tbody>
    </table>
    </c:if>
    <c:if test="${orders == null || orders.size() == 0}">
        <br>
        <br>
        <div class="alert alert-danger">
            <strong>Ваш список заказов пуст.</strong>
        </div>
    </c:if>
</div>
</body>
</html>
