<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <h2>Проведи время красиво</h2>
                    <p>Ты не забудешь никогда.</p>
                </div>
            </div>

            <div class="item">
                <img src="<c:url value="/images/3.jpg"/>" alt="Image">
                <div class="carousel-caption">
                    <h3>Потрясающие восходы</h3>
                    <p>Огненные закаты.</p>
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
        <h3>Что Вас ждет :)</h3><br>
        <div class="row">
            <div class="col-sm-4">
                <img src="<c:url value="/images/1.jpg"/>" class="img-responsive img-rounded" style="width: 100%; height: 400px;" alt="Image">
                <p>Отдыхай красиво!</p>
            </div>
            <div class="col-sm-4">
                <img src="<c:url value="/images/2.jpg"/>" class="img-responsive img-rounded" style="width: 100%; height: 400px;" alt="Image">
                <p>Отдыхай качественно!</p>
            </div>
            <div class="col-sm-4">
                <div class="well">
                    <p>
                        Равным образом рамки и место обучения кадров представляет собой интересный
                        эксперимент проверки систем массового участия. Значимость этих проблем настолько
                        очевидна, что дальнейшее развитие различных форм деятельности требуют определения
                        и уточнения модели развития. Повседневная практика показывает, что реализация
                        намеченных плановых заданий позволяет выполнять важные задания по разработке
                        системы обучения кадров, соответствует насущным потребностям.
                    </p>
                </div>
            </div>
        </div>
    </div><br>
    <jsp:include page="_footer.jsp"/>
    </body>
</html>
