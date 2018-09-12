<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 05.09.2018
  Time: 8:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
            <li data-target="#myCarousel" data-slide-to="2"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner">
            <div class="item active">
                <img src="${pageContext.request.contextPath}/images/1.jpg" alt="Los Angeles" style="width:100%; height: 50%">
            </div>

            <div class="item">
                <img src="${pageContext.request.contextPath}/images/2.jpg" alt="Chicago" style="width:100%; height: 50%">
            </div>

            <div class="item">
                <img src="${pageContext.request.contextPath}/images/3.jpg" alt="New york" style="width:100%; height: 50%">
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>

