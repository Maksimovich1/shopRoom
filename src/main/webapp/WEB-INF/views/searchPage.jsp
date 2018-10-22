<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 31.08.2018
  Time: 8:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Result Search</title>

    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="static/style.css" rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body class="bacgr">
<jsp:include page="_navbar.jsp"/>
<br>
<div class="container-fluid">
    <div class="row content">
        <form action="${pageContext.request.contextPath}/secure/productList" method="get">
        <div class="col-sm-3 sidenav hidden-xs col-sm-3my">
            <h2>Поиск <span class="glyphicon glyphicon-home"></span></h2>
            <div class="form-group">
                <label for="bedroom"> Количество комнат</label>
                <select class="form-control" id="bedroom" name="bedroom">
                    <option <c:if test="${param.bedroom == '1'}">
                        selected="selected"
                    </c:if>>1</option>
                    <option <c:if test="${param.bedroom == '2'}">
                        selected="selected"
                    </c:if>>2</option>
                    <option <c:if test="${param.bedroom == '3'}">
                        selected="selected"
                    </c:if> >3</option>
                </select>
            </div>
            <div class="form-group">
                <label for="countP"><span class="glyphicon glyphicon-user"></span> Количество людей</label>
                <select class="form-control" id="countP" name="countP">
                    <option <c:if test="${param.countP == '1'}">
                        selected="selected"
                    </c:if>>1</option>
                    <option <c:if test="${param.countP == '2'}">
                        selected="selected"
                    </c:if>>2</option>
                    <option <c:if test="${param.countP == '3'}">
                        selected="selected"
                    </c:if>>3</option>
                    <option <c:if test="${param.countP == '4'}">
                        selected="selected"
                    </c:if>>4</option>
                </select>
            </div>
            <div class="form-group">
                <label for="countC"> Количество детей</label>
                <select class="form-control" id="countC" name="countC">
                    <option<c:if test="${param.countC == '0'}">
                        selected="selected"
                    </c:if>>0</option>
                    <option <c:if test="${param.countC == '1'}">
                        selected="selected"
                    </c:if>>1</option>
                    <option <c:if test="${param.countC == '2'}">
                        selected="selected"
                    </c:if>>2</option>
                    <option <c:if test="${param.countC == '3'}">
                        selected="selected"
                    </c:if>>3</option>
                </select>
            </div>
            <div class="form-group">
                <label for="district"><span class="glyphicon glyphicon-globe"></span> Район</label>
                <select class="form-control" id="district" name="district">
                    <option <c:if test="${param.district == 'A'}">
                        selected="selected"
                    </c:if>>A</option>
                    <option <c:if test="${param.district == 'B'}">
                        selected="selected"
                    </c:if> >B</option>
                    <option <c:if test="${param.district == 'C'}">
                        selected="selected"
                    </c:if>>C</option>
                    <option <c:if test="${param.district == 'D'}">
                        selected="selected"
                    </c:if>>D</option>
                </select>
            </div>
            <div class="form-group">
                <label for="dateIn"><span class="glyphicon glyphicon-log-in"></span> Дата въезда</label>
                <input type="date" class="form-control" id="dateIn" name="dateIn"  value="${param.dateIn}"/>
            </div>
            <div class="form-group">
                <label for="dateOut"><span class="glyphicon glyphicon-log-out"></span> Дата выезда</label>
                <input type="date" class="form-control" id="dateOut" name="dateOut"  value="${param.dateOut}"/>
            </div>
            <div class="form-group">
                <label for="priceMax"><span class="glyphicon glyphicon-euro"></span> Максимальная цена:</label>
                <input type="text" class="form-control" id="priceMax" value="" autocomplete="off" placeholder="Цена" name="priceMax">

            </div>
            <button type="submit" class="btn btn-primary btnmy">Find Now</button>
            <br>
        </div>
</form>
        <div class="col-sm-9">
            <div class="row">
                        <c:forEach items="${apartmentList}" var="apartment">

                        <div class="col-md-4">
                            <div class="well">
                        <div class="thumbnail">
                        <a href="${pageContext.request.contextPath}/images/ava.jpg">
                        <img src="${pageContext.request.contextPath}/images/ava.jpg" alt="Lights" style="width:100%">
                        <div class="caption row">
                        <p class="col-md-6">Комнаты: ${apartment.getBedroom()}</p>
                        <p class="col-md-6">Люди: ${apartment.getPeople()}</p>
                        <p class="col-md-6">Дети: ${apartment.getChildren()}</p>
                        <p class="col-md-6">Цена: ${apartment.getPrice() * countDay} $</p>
                        </div>
                        </a>
                            <div>
                                <button type="button" class="btn btn-info" data-toggle="collapse" data-target="#demo${apartment.getId()}">О номере</button>
                                <div id="demo${apartment.getId()}" class="collapse">
                                    ${apartment.getAbout()}
                                </div>
                            </div>
                        <form action="${pageContext.request.contextPath}/secure/order" method="post">
                        <input name="idApartment" value="${apartment.getId()}" type="hidden">
                        <button class="btn btn-primary">Заказать</button>
                        </form>
                        </div>
                        </div>
                            </div>
                        </c:forEach>
                </div>
            </div>

        </div>
    </div>

<br>
<jsp:include page="_footer.jsp"/>
</body>
</html>
