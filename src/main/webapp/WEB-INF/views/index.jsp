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
    <title>Канарский отдых</title>
    <link href="static/style.css" rel="stylesheet">
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>
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
                <img src="${pageContext.request.contextPath}/images/1.jpg" alt="Image">
                <div class="carousel-caption">
                    <h3>Holiday</h3>
                    <p>Money Money.</p>
                </div>
            </div>

            <div class="item">
                <img src="${pageContext.request.contextPath}/images/1.jpg" alt="Image">
                <div class="carousel-caption">
                    <h3>More Sell $</h3>
                    <p>Lorem ipsum...</p>
                </div>
            </div>
        </div>

        <!-- Left and right controls -->
        <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
            <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
            <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>


    <div class="container text-center">
        <h3>What We Do</h3><br>
        <div class="row">
            <div class="col-sm-4">
                <img src="${pageContext.request.contextPath}/images/ava.jpg" class="img-responsive" style="width:100%" alt="Image">
                <p>Current Project</p>
            </div>
            <div class="col-sm-4">
                <img src="${pageContext.request.contextPath}/images/ava.jpg" class="img-responsive" style="width:100%" alt="Image">
                <p>Project 2</p>
            </div>
            <div class="col-sm-4">
                <div class="well">
                    <p>Some text..</p>
                </div>
                <div class="well">
                    <p>Some text..</p>
                </div>
            </div>
        </div>
    </div><br>

    <footer class="container-fluid text-center">
        <p>Footer Text</p>
    </footer>

    </body>
</html>
