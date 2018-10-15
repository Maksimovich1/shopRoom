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
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Проверка:</h2>
    <div class="panel panel-default">
        <div class="panel-heading"><h1>Счет №56489</h1></div>
        <div class="panel-body form-inline"><h4>№ апартамента:</h4> ${id}</div>
        <div class="panel-body"><h4>Дата оформления аренды: </h4>${dateNow}</div>
        <div class="panel-body"><h4>Период проживания:</h4> ${period_str}</div>
        <div class="panel-body"><h4>Количество ночей:</h4> ${countNight}</div>
        <div class="panel-body"><h4>Цена за 1 ночь:</h4> ${apartment.getPrice()}</div>
        <div class="panel-footer"><h2>Итого:</h2> <p>${summary}</p> <h3>$</h3></div>
        <div class="panel-footer">
            <form action="${pageContext.request.contextPath}/secure/complete" method="post" class="form-inline">
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input type="text" class="form-control" id="phone" placeholder="Enter phone" name="phone">
                </div>
                <input name="apartment" type="hidden" value="${id}">
                <button type="submit" class="btn btn-default">Оформить заказ</button>
            </form>
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
