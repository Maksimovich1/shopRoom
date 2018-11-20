<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 04.11.2018
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/style-regist.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="registration.title"/> </title>
</head>
<body>
<div class="container">
        <div id="logbox">
            <form id="signup" name="registration" method="post" action="${pageContext.request.contextPath}/registration" onsubmit="return checkInput('<spring:message code="registration.alertError"/>')">
                <h1><spring:message code="registration.create"/> </h1>
                <input name="username" type="text" autofocus="autofocus" class="input pass" required="required" placeholder="<spring:message code="registration.placeholder_username"/>" />
                <input name="email" type="email" required="required" class="input pass" placeholder="<spring:message code="registration.placeholder_email"/>" />
                <input name="phone" type="text" class="input pass" placeholder="<spring:message code="registration.placeholder_phone"/>" />
                <input name="password1" type="password" required="required" class="input pass" placeholder="<spring:message code="registration.placeholder_pass1"/>" />
                <input name="password2" type="password" required="required" class="input pass" placeholder="<spring:message code="registration.placeholder_pass2"/>" />
                <input name="service_code" type="number" class="input pass" placeholder="<spring:message code="registration.placeholder_service_code"/>" />
                <input type="submit" class="inputButton" value="<spring:message code="registration.submit"/>" />
                <div class="text-center">
                    <spring:message code="registration.back_to_login"/> <a href="<c:url value="/login"/>" id="login_id"><spring:message code="registration.back_to_login_link"/> </a>
                </div>
            </form>
            <script>
                function checkInput(mess) {
                    var username = document.forms["registration"]["username"].value;
                    var pass1 = document.forms["registration"]["password1"].value;
                    var pass2 = document.forms["registration"]["password2"].value;
                    var service_code = document.forms["registration"]["service_code"].value;

                    var username_regex =/^[a-zA-Z]{3,16}$/;
                    var service_regex = /^[0-9]{4,6}$/;
                    if (!username.match(username_regex) ||
                        !service_code.match(service_regex)){
                        alert(mess);
                        return false;
                    }
                    if (pass1 !== pass2){
                        alert("Пароли не совпадают!");
                        return false;
                    }
                    return true;

                }
            </script>
        </div>
    </div>
</body>
</html>
