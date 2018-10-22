<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 21.09.2018
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Control Panel</title>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="_navbar.jsp"/>
    <c:if test="${param.delete == 'true'}">
        <div class="alert alert-success">
            <strong>Success!</strong> Apartment is delete.
        </div>
    </c:if>
    <table class="table table-hover table-inverse">
        <thead>
        <tr>
            <th>#</th>
            <th>Count bedroom</th>
            <th>Count people</th>
            <th>Count child</th>
            <th>Price</th>
            <th>District</th>
            <th>Enable</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${apartmentList}" var="apartment">
            <tr>
                <th scope="row">${apartment.getId()}</th>
                <td>${apartment.getBedroom()}</td>
                <td>${apartment.getPeople()}</td>
                <td>${apartment.getChildren()}</td>
                <td>${apartment.getPrice()}</td>
                <td>${apartment.getDistrict()}</td>
                <td>
                    <c:if test="${apartment.isEnable() == 1}">Доступен</c:if>
                    <c:if test="${apartment.isEnable() == 0}">Недоступен</c:if>
                </td>
                <td><a href=" <c:url value="${pageContext.request.contextPath}/admin/delete?id=${apartment.getId()}"/> "> Delete</a></td>
                <td><a href=" <c:url value="${pageContext.request.contextPath}/admin/getUrl?id=${apartment.getId()}"/> ">Ссылка на календарь.</a> </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

        <%--<form:form  action="${pageContext.request.contextPath}/admin/save_image" method="post" enctype="multipart/form-data">--%>
            <%--<input id="inpId" type="text" name="idApartment" value="1">--%>
            <%--<input id="inpUpload" type="file" name="file">--%>
            <%--<input id="btnUpload" type="submit" value="submit"/>--%>
        <%--</form:form>--%>
</div>
</body>
</html>