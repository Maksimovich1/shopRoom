<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 07.09.2018
  Time: 14:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="login.title"/> </title>
</head>
<body>
<br>
<br>
<div class="container" style="width: 25%">
<form class="text-center border border-light p-5" action='<spring:url value="/loginAction"/>' method="post">

    <p class="h4 mb-4"><spring:message code="login.singin"/></p>

    <!-- Email -->
    <input type="text" name="username" id="username" class="form-control mb-4"
           placeholder=<spring:message code="login.email"/> >
    <br>
    <!-- Password -->
    <input type="password" name="password" id="password" class="form-control mb-4"
           placeholder=<spring:message code="login.pass"/> >
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
    <button class="btn btn-info btn-block my-4" type="submit"><spring:message code="login.button"/></button>

    <!-- Register -->
    <p><spring:message code="login.noauth"/>
        <a href="${pageContext.request.contextPath}/productList"><spring:message code="login.conwauth"/>.</a>
    </p>

</form>
</div>
<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
