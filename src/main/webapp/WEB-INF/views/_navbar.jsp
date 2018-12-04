<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 05.09.2018
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="<c:url value="/"/>"><spring:message code="navbar.title"/> </a>
        </div>
            <ul class="nav navbar-nav ">
                <li class="active"><a href= "<c:url value="/"/>" >Home</a></li>
                <sec:authorize access="hasRole('USER')">
                    <li><a href="<c:url value="/secure/product"/>"><spring:message code="navbar.search.apartment"/></a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ANONYMOUS') or hasRole('USER')">
                    <%--<li><a href="<c:url value="/contacts"/>"><spring:message code="navbar.callback"/> </a></li>--%>
                    <li><a href="<c:url value="/about"/>"><spring:message code="navbar.about"/></a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="<c:url value="/admin/blocks"/>"><spring:message code="navbar.block"/> </a></li>
                    <li><a href="<c:url value="/admin/orders"/>"><spring:message code="navbar.manager_sale"/> </a></li>
                </sec:authorize>
            </ul>

        <ul class="nav navbar-nav navbar-right">
            <li><a href="<c:url value="?lang=en"/>">EN</a></li>
            <li><a href="<c:url value="?lang=ru"/>">RU</a></li>
            <sec:authorize access="hasRole('ANONYMOUS')">

            <li><a href="<c:url value="/secure/product"/>"><spring:message code="navbar.login"/> <span class="glyphicon glyphicon-user"></span></a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="<c:url value="/"/>"
                ><span class="glyphicon glyphicon-user"></span> <sec:authentication property="principal.username"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="<c:url value="/admin/updateOrAdd"/>"><span class="glyphicon glyphicon-cog"></span> Управление аппартаментами</a></li>
                    <li><a href="<c:url value="/admin/control"/>"><span class="glyphicon glyphicon-trash"></span> Удалить или получить ссылку на аппартамент</a></li>
                    <li><a href="<c:url value="/admin/control"/>"><span class="glyphicon glyphicon-user"></span> Работа с клиентами</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('USER')">
                        <li><a href="<c:url value="/secure/my_order"/>"><span class="glyphicon glyphicon-list-alt"></span> <spring:message code="navbar.myOrders"/></a></li>
                    </sec:authorize>
                    <li><a href="<c:url value="/logout"/>"><span class="glyphicon glyphicon-log-out"></span> <spring:message code="navbar.Logout"/></a></li>
                </ul>
        </sec:authorize>
            </li>
        </ul>
    </div>
</nav>