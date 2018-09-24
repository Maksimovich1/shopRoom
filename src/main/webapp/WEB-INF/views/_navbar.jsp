<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 05.09.2018
  Time: 8:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar fixed-top navbar-light" style="background-color: #cefbfd;">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Tenerife Perfect</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/secure/search">Поиск апартаментов</a></li>
            <li><a href="${pageContext.request.contextPath}/contacts">Обратная связь</a></li>
            <li><a href="${pageContext.request.contextPath}/about">О нас</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <sec:authorize access="hasRole('ANONYMOUS')">
            <li class="navbar-right"><a href="${pageContext.request.contextPath}/admin/control">Admin Panel</a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
            <li class="navbar-right dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="${pageContext.request.contextPath}/"
                >Hello <sec:authentication property="principal.username"/>!
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="${pageContext.request.contextPath}/admin/control">Admin Panel</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
                </ul>
        </sec:authorize>
            </li>
        </ul>
    </div>
</nav>