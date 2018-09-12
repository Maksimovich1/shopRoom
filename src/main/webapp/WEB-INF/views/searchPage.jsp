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
<jsp:include page="_navbar.jsp"/>

<input type="date">

<div class="container">
    <h2>Search</h2>
    <form class="form-inline" action="#">
        <div class="form-group">
            <label for="countp">Кол-во людей(18+):</label>
            <select class="form-control" id="countp">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
            </select>
        </div>
        <div class="form-group">
            <label for="countc">Количество детей:</label>
            <select class="form-control" id="countc">
                <option>1</option>
                <option>2</option>
                <option>3</option>
                <option>4</option>
            </select>
        </div><br>
        <div class="checkbox">
            <label><input type="checkbox" name="remember"> Remember me</label>
        </div><br>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<img src="${pageContext.request.contextPath}/images/123.jpg" alt="picturesa"/>



<div class="container">
    <h3>Результаты:</h3>
    <c:forEach items="${productList}" var="product">
        <div class="row">
            <div class="col-sm-6 col-md-10">
                <div class="thumbnail">
                    <%--<img src="<c:url value="../resources/images/123.jpg"/> " alt="Картинка">--%>
                    <div class="caption">
                        <h3>${product.getName()}</h3>
                        <p>${product.getPrice()}$</p>
                        <p><a href="#" class="btn btn-primary" role="button"> Аренда</a> </p>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
