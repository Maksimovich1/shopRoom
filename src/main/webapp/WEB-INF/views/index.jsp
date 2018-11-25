<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 29.08.2018
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="index.title"/></title>

    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="static/style.css" rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body class="bacgr">
    <jsp:include page="_navbar.jsp"/>
    <div id="myCarousel" class="carousel slide" data-ride="carousel">
        <!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
            <li data-target="#myCarousel" data-slide-to="1"></li>
        </ol>

        <!-- Wrapper for slides -->
        <div class="carousel-inner" role="listbox">
            <div class="item active">
                <img src="<c:url value="/images/3.jpg"/>" alt="Image">
                <div class="carousel-caption">
                    <h2><spring:message code="index.slide1.h2"/></h2>
                    <p><spring:message code="index.slide1.p"/></p>
                </div>
            </div>

            <div class="item">
                <img src="<c:url value="/images/3.jpg"/>" alt="Image">
                <div class="carousel-caption">
                    <h2><spring:message code="index.slide2.h2"/></h2>
                    <p><spring:message code="index.slide2.p"/></p>
                </div>
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only"><spring:message code="index.slide.left"/> </span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only"><spring:message code="index.slide.right"/></span>
        </a>
    </div>

    <div class="container text-center">
        <h3><spring:message code="index.div.title"/></h3><br>
        <div class="row">
            <div class="col-sm-4">
                <img src="<c:url value="/images/1.jpg"/>" class="img-responsive img-rounded" style="width: 100%; height: 400px;" alt="Image">
                <p><spring:message code="index.div.block1"/></p>
            </div>
            <div class="col-sm-4">
                <img src="<c:url value="/images/2.jpg"/>" class="img-responsive img-rounded" style="width: 100%; height: 400px;" alt="Image">
                <p><spring:message code="index.div.block2"/></p>
            </div>
            <div class="col-sm-4">
                <div class="well">
                    <p>
                        <spring:message code="index.div.text"/>
                    </p>
                </div>
            </div>
        </div>
    </div><br>
    <jsp:include page="_footer.jsp"/>
    </body>
</html>
