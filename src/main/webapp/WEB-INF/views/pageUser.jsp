<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 28.12.2018
  Time: 9:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Работа с пользователями</title>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="static/style.css" rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="static/js/pageUser.js"></script>
</head>
<body>
<jsp:include page="_navbar.jsp"/>
<div class="container">
    <div class="search_user">
        <form class="form-inline" action="#" id="search_form">
            <div class="form-group">
                <label for="user_select">Выберите пользователя</label>
                <select class="form-control search_user_select" id="user_select" name="user_select">
                    <c:forEach items="${userList}" var="user">
                        <option value="${user.getUsername()}">
                                ${user.getUsername()}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <button type="submit" class="btn btn-info" id="search_btn">Поиск</button>
        </form>
    </div>
    <div id="mainDivUserBody" class="search_user_div_body">
        <div class="row">
        <div class="col-sm-3"></div>
        <div class="col-sm-3">
            <h3 id="nickname"></h3>
        <table style="font-size: small">
            <tbody>
            <tr>
                <th scope="row">Полное имя</th>
                <td><label id="fullName"></label></td>
            </tr>
            <tr>
                <th scope="row">Блокирован?</th>
                <td><label id="enabled"></label></td>
            </tr>
            <tr>
                <th scope="row">Роль в системе</th>
                <td><label id="roleInSystem"></label></td>
            </tr>
            <tr>
                <th scope="row">Сумма заказов</th>
                <td><label id="orderAmount"></label></td>
            </tr>
            <tr>
                <th scope="row">Долг по брони</th>
                <td><label id="summaryOfDebt"></label></td>
            </tr>
            </tbody>
        </table>
        </div>
    </div>
        <div>
            <div>Изменение параметров</div>
        </div>
    </div>

</div>
</body>
</html>