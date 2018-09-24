<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<jsp:include page="_navbar.jsp"/>
<div class="container">

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
            <th>About</th>
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
                <td>${apartment.isEnable()}</td>
                <td>${apartment.getAbout()}</td>
            </tr>
        </c:forEach>

        </tbody>
    </table>

</div>
</body>
</html>
