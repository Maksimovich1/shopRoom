<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 07.09.2018
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <title>Авторизация</title>
</head>
<body>
<%--<div class="container">--%>

    <%--<form class="form-horizontal" action='<spring:url value="/loginAction"/>' method="post">--%>
        <%--<div class="form-group">--%>
            <%--<label class="control-label col-sm-2" for="username">Email or Phone</label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<input type="text" class="form-control" id="username" placeholder="Enter email or Phone" name="username">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<label class="control-label col-sm-2" for="password">Password:</label>--%>
            <%--<div class="col-sm-10">--%>
                <%--<input type="password" class="form-control" id="password" placeholder="password" name="password">--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<div class="col-sm-offset-2 col-sm-10">--%>
                <%--<div class="checkbox">--%>
                    <%--<label><input type="checkbox" name="accept"> Я даю согласие на обработку личной информации</label>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
        <%--<div class="form-group">--%>
            <%--<div class="col-sm-offset-2 col-sm-10 ">--%>
                <%--<button type="submit" class="btn btn-primary">Submit</button>--%>
            <%--</div>--%>


            <%--<c:if test="${param.error != null}">--%>
                <%--<p>--%>
                    <%--Invalid username and password.--%>
                <%--</p>--%>
            <%--</c:if>--%>


        <%--</div>--%>
    <%--</form>--%>
<%--</div>--%>
<div class="container" style="width: 25%">
<form class="text-center border border-light p-5" action='<spring:url value="/loginAction"/>' method="post">

    <p class="h4 mb-4">Sign in</p>

    <!-- Email -->
    <input type="text" name="username" id="username" class="form-control mb-4" placeholder="E-mail">
    <br>
    <!-- Password -->
    <input type="password" name="password" id="password" class="form-control mb-4" placeholder="Password">
    <br>
    <div class="d-flex justify-content-around">
        <div>
            <!-- Remember me -->
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="defaultLoginFormRemember">
                <label class="custom-control-label" for="defaultLoginFormRemember">Remember me</label>
            </div>
        </div>
        <div>
            <!-- Forgot password -->
            <a href="#">Forgot password?</a>
        </div>
    </div>

    <!-- Sign in button -->
    <button class="btn btn-info btn-block my-4" type="submit">Sign in</button>

    <!-- Register -->
    <p>No Auth?
        <a href="${pageContext.request.contextPath}/productList">Continue without auth.</a>
    </p>

</form>
</div>
<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
