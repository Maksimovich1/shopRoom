<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 29.08.2018
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Канарский отдых</title>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">--%>
    <%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>--%>
    <%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>--%>
</head>
<body>
<div class="container" style="background-color: #e3f2fd">
    <jsp:include page="_navbar.jsp"/>
    <jsp:include page="_carousel.jsp"/>

    <br>
    <div class="container">
    <!-- Left-aligned media object -->
    <div class="media">
        <div class="media-left">
            <img src="${pageContext.request.contextPath}/images/ava.jpg" class="media-object" style="width:60px">
        </div>
        <div class="media-body">
            <h4 class="media-heading">О СДЕЛКАХ</h4>
            <p>САМЫЕ НИЗКИЕ ЦЕНЫ ПОМОЩЬ КЛИЕНТАМ</p>
        </div>
    </div>
        <div class="media">
            <div class="media-body">
                <p>какой то текст</p>
            </div>
            <div class="media-right">
                <img src="${pageContext.request.contextPath}/images/ava.jpg" class="media-object" style="width:60px">
            </div>
        </div>
    </div>
    <button type="button" class="btn btn-success btn-block" onclick="${"location.href='/secure/search'"}">ПРИСТУПИТЬ К ПОИСКУ</button>
</div>
</body>
</html>
