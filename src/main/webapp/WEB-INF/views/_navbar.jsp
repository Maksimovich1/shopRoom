<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            <a class="navbar-brand" href="${pageContext.request.contextPath}/">Tenerife Perfect</a>
        </div>
            <ul class="nav navbar-nav ">
                <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/secure/product">Поиск апартаментов</a></li>
                <sec:authorize access="hasRole('ANONYMOUS') or hasRole('USER')">
                    <li><a href="${pageContext.request.contextPath}/contacts">Обратная связь</a></li>
                    <li><a href="${pageContext.request.contextPath}/about">О нас</a></li>
                </sec:authorize>
                <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/admin/orders">Продажи</a></li>
                </sec:authorize>
            </ul>

        <ul class="nav navbar-nav navbar-right">
            <sec:authorize access="hasRole('ANONYMOUS')">
            <li><a href="${pageContext.request.contextPath}/admin/control">Login ADMIN <span class="glyphicon glyphicon-user"></span></a></li>
        </sec:authorize>
        <sec:authorize access="hasRole('USER') or hasRole('ADMIN')">
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="${pageContext.request.contextPath}/"
                ><span class="glyphicon glyphicon-user"></span> <sec:authentication property="principal.username"/>!
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <sec:authorize access="hasRole('ADMIN')">
                    <li><a href="${pageContext.request.contextPath}/admin/control">Admin</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/updateOrAdd"><span class="glyphicon glyphicon-cog"></span> Добавить или редактировать</a></li>
                    <li><a href="${pageContext.request.contextPath}/admin/control"><span class="glyphicon glyphicon-trash"></span> Удалить или получить ссылку</a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('USER')">
                        <li><a href="${pageContext.request.contextPath}/secure/my_order"><span class="glyphicon glyphicon-list-alt"></span> Мои заказы</a></li>
                    </sec:authorize>
                    <li><a href="${pageContext.request.contextPath}/logout"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
        </sec:authorize>
            </li>
        </ul>
    </div>
</nav>