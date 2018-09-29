<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 31.08.2018
  Time: 8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Result Search</title>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>
<div class="container">
    <jsp:include page="_navbar.jsp"/>
</div>
<div class="container" style="background-color: orange">
    <h2>Search</h2>
    <jsp:include page="_search.jsp"/>
</div>
<div class="container">
    <p>Результаты:</p><br>
    <div class="row">
    <c:forEach items="${apartmentList}" var="apartment">

            <div class="col-md-3">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/images/ava.jpg">
                        <img src="${pageContext.request.contextPath}/images/ava.jpg" alt="Lights" style="width:100%">
                        <div class="caption row">
                            <p class="col-md-6">Комнаты: ${apartment.getBedroom()}</p>
                            <p class="col-md-6">Люди: ${apartment.getPeople()}</p>
                            <p class="col-md-6">Дети: ${apartment.getChildren()}</p>
                            <p class="col-md-6">Цена: ${apartment.getPrice()}</p>
                        </div>
                    </a>
                    <button type="button" class="btn btn-success">Заказать</button>
                </div>
            </div>
    </c:forEach>
    </div>
</div>
</body>
</html>
