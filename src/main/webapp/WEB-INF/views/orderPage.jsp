<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <c:if test="${message != null && !message.equals('')}">
        <div class="alert alert-info" style="height: auto">
            <strong>${message}</strong>
        </div>
    </c:if>
        <%--Форма поиска для покупок--%>
    <security:authorize access="hasRole('ADMIN')">
        <br>
        <br>
    <form class="form-inline" action="${pageContext.request.contextPath}/admin/orders" method="post">
        <div class="form-group">
            <label for="room">ID комнаты:</label>
            <input type="text" class="form-control width_OrderParameter" id="room" name="idRoom">
        </div>
        <div class="form-group">
        <label for="status">Статус:</label>
        <input type="text" class="form-control" id="status" name="status" placeholder="Ожидает оплаты(0)">
    </div>
        <div class="form-group">
            <label for="orderId">ID заказа:</label>
            <input type="text" class="form-control width_OrderParameter" id="orderId" name="idOrder">
        </div>
        <%--<div class="form-group">
            <label for="dateOrder">ID заказа:</label>
            <input type="date" class="form-control" id="dateOrder" name="dateOrder">
        </div>--%>
        <div class="form-group">
            <label for="username">Покупатель:</label>
            <input type="text" class="form-control" id="username" name="username">
        </div>
        <button type="submit" class="btn btn-default">Поиск</button>
    </form>
    </security:authorize>
            <%--Вывод списка ордеров--%>
    <c:if test="${orders != null && orders.size() != 0 }">
    <table class="table table-hover table-inverse">
        <thead>
        <tr>
            <th><spring:message code="orderPage.viewOrder.title.number"/></th>
            <th><spring:message code="orderPage.viewOrder.title.dateOrder"/></th>
            <th><spring:message code="orderPage.viewOrder.title.datePeriod"/></th>
            <th><spring:message code="orderPage.viewOrder.title.numberRoom"/></th>
            <th><spring:message code="orderPage.viewOrder.title.price"/></th>
            <th><spring:message code="orderPage.viewOrder.title.status"/></th>
            <security:authorize access="hasRole('ADMIN')">
                <th><spring:message code="orderPage.viewOrder.title.confirm"/></th>
            </security:authorize>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${orders}" var="order">
            <tr>
                <th scope="row">

                        №${order.getId_order()}
                </th>
                <td>${order.getDate_order()}</td>
                <td>[ ${order.getDate_in()} ]-[ ${order.getDate_out()} ]</td>
                <td><spring:message code="orderPage.viewOrder.body.room"/> ${order.getId_product_buy()}</td>
                <td>${order.getPrice()}</td>
                <td>
                    <c:if test="${order.getStatus() == 1}">
                        <div class="alert alert-success">
                            <strong><spring:message code="orderPage.viewOrder.body.paid"/></strong>
                        </div>
                    </c:if>
                    <c:if test="${order.getStatus() == 0}">
                        <div class="alert alert-danger">
                            <strong><spring:message code="orderPage.viewOrder.body.awaitingpayment"/></strong>
                        </div>
                    </c:if><c:if test="${order.getStatus() == 2}">
                    <div class="alert alert-warning">
                        <strong><spring:message code="orderPage.viewOrder.body.awaitingadmin"/></strong>
                    </div>
                </c:if>
                </td>
                <td>
                    <security:authorize access="hasRole('ADMIN')">
                        <c:if test="${order.getStatus() == 0}">
                            <form action="${pageContext.request.contextPath}/admin/confirmPaymentUser" method="post">
                                <input type="hidden" value="${order.getId_order()}" name="id">
                                <input type="hidden" value="-1" name="status">
                                <button type="submit">Удалить заказ</button>
                            </form>
                        </c:if>
                        <c:if test="${order.getStatus() == 1}">
                            <form action="${pageContext.request.contextPath}/admin/confirmPaymentUser" method="post">
                                <input type="hidden" value="${order.getId_order()}" name="id">
                                <input type="hidden" value="0" name="status">
                                <button type="submit">Отменить оплату</button>
                            </form>
                        </c:if>
                        <c:if test="${order.getStatus() == 2}">
                            <form action="${pageContext.request.contextPath}/admin/confirmPaymentUser" method="post">
                                <input type="hidden" value="${order.getId_order()}" name="id">
                                <input type="hidden" value="1" name="status">
                                <button type="submit">Подтвердить оплату</button>
                            </form>
                            <form action="${pageContext.request.contextPath}/admin/confirmPaymentUser" method="post">
                                <input type="hidden" value="${order.getId_order()}" name="id">
                                <input type="hidden" value="0" name="status">
                                <button type="submit">Неоплачен</button>
                            </form>
                        </c:if>

                    </security:authorize>
                    <security:authorize access="hasRole('USER')">
                        <c:if test="${order.getStatus() == 2}">
                    <%--Оплачен, ожидается подтверждение админа--%>
                        </c:if>
                        <c:if test="${order.getStatus() == 1}">
                            <%--Оплачен действий нет--%>
                            <div class="alert alert-success">
                                <strong><span class="glyphicon glyphicon-ok"></span></strong>
                            </div>
                        </c:if>
                        <c:if test="${order.getStatus() == 0}">
                            <%--Не оплачен--%>
                            <form action="${pageContext.request.contextPath}/secure/confirmPaymentUser" method="post">
                                <input type="hidden" value="${order.getId_order()}" name="id">
                                <input type="hidden" value="2" name="status">
                                <button class="btn btn-success" type="submit"><spring:message code="orderPage.viewOrder.body.confirmbyuser"/></button>
                            </form>
                        </c:if>

                    </security:authorize>
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
            <security:authorize access="hasRole('USER')">
            <strong><spring:message code="orderPage.viewOrder.body.orderList"/></strong>
            </security:authorize>
            <security:authorize access="hasRole('ADMIN')">
                <strong>Список покупок пуст. Укажите параметры поиска!</strong>
            </security:authorize>
        </div>
    </c:if>
</div>
</body>
</html>
