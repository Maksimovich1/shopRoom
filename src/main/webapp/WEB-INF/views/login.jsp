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
    <link href="${pageContext.request.contextPath}/static/style-regist.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="login.title"/> </title>
</head>
<body>
<div class="container">
    <div id="logbox">
            <%--
            Ошибка при попытке входа
            --%>
        <c:if test="${param.error != null}">
        <div class="alert alert-danger" style="height: auto">
            <strong><spring:message code="login.error"/></strong> <spring:message code="login.error_message"/>
        </div>
        </c:if>
            <%--
            Сообщение о удачной регистрации
            --%>
        <c:if test="${registrationSuccess == true}">
            <div class="alert alert-success" style="height: auto">
               <spring:message code="login.success_reg"/>
            </div>
        </c:if>
            <%--
            Сообщение о НЕ удачной регистрации
            --%>
                <c:if test="${registrationSuccess == false}">
                    <div class="alert alert-danger" style="height: auto">
                        <spring:message code="login.error_reg"/>
                    </div>
                </c:if>
                <%--
                Сообщение о НЕ удачной регистрации сбой верификации!
                --%>
                <c:if test="${verificationError == true}">
                    <div class="alert alert-danger" style="height: auto">
                        <spring:message code="login.error_verif_reg"/>
                    </div>
                </c:if>
                <%--
                Сообщение о НЕ удачной регистрации сбой верификации!
                --%>
                <c:if test="${duplicate_user == true}">
                    <div class="alert alert-danger" style="height: auto">
                        <spring:message code="login.error_duplicate_reg"/>
                    </div>
                </c:if>
                <%--
                Форма входа_________
                --%>
                <form id="signup" action='<spring:url value="/loginAction"/>' method="post">
                    <h1><spring:message code="login.singin"/></h1>
                    <input type="text" autocomplete="off" name="username" class="input pass" placeholder="<spring:message code="login.email"/>" />
                    <input type="password" name="password" id="password" required="required" class="input pass" placeholder="<spring:message code="login.pass"/>" />
                    <input type="submit" class="inputButton" value=<spring:message code="login.button"/>/>
                    <div class="text-center">
                        <a href="${pageContext.request.contextPath}/singUp"><spring:message code="login.conwauth"/></a>
                        -
                        <a href="${pageContext.request.contextPath}/" id=""><spring:message code="login.toIndex"/> </a>
                    </div>

                </form>

    </div>

</div>

<script src="webjars/jquery/3.3.1/jquery.min.js"></script>
<script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
