<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 25.09.2018
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Update or Add Apartment</title>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="_navbar.jsp"/>
</div>
<div class="container">

    <c:if test="${param.addStatus == 'true'}">
        <div class="alert alert-success">
            <strong>Success!</strong> Apartment is add.
        </div>
    </c:if>
    <c:if test="${param.addStatus == 'false'}">
        <div class="alert alert-warning">
            <strong>Error!</strong> Апартамент не был добавлен!
        </div>
    </c:if>
    <!-- Trigger the modal with a button -->
    <div>
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">+ Добавить апартамент</button>
    </div>

    <form action="${pageContext.request.contextPath}/admin/searchForId">
        <div class="input-group col-xs-4">
            <input type="text" class="form-control input-lg" placeholder="Search" name="ida">
            <div class="input-group-btn">
                <button class="btn btn-success btn-lg" type="submit">Поиск</button>
            </div>
        </div>
    </form>
    <!-- Modal -->
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Добавление апартамента</h4>
                </div>
                <form action="${pageContext.request.contextPath}/admin/addApartment" method="get">
                <div class="modal-body">
                    <div class="col-xs-6">
                        <label for="id1">ID:</label>
                        <input type="text" class="form-control" id="id1" placeholder="ID" name="id1">
                    </div>
                    <div class="col-xs-6">
                        <label for="countP">Колличество людей 18+:</label>
                        <input type="text" class="form-control" id="countP" placeholder="Количество людей 18+" name="people">
                    </div>
                    <div class="col-xs-6">
                        <label for="countC">Колличество детей:</label>
                        <input type="text" class="form-control" id="countC" placeholder="Количество детей" name="children">
                    </div>
                    <div class="col-xs-6">
                        <label for="bedroom">Колличество комнат</label>
                        <input type="text" class="form-control" id="bedroom" placeholder="Количество комнат" name="bedroom">
                    </div>
                    <div class="col-xs-6">
                        <label for="price">Цена:</label>
                        <input type="text" class="form-control" id="price" placeholder="Цена" name="price">
                    </div>
                    <div class="col-xs-6">
                        <label for="district">Район</label>
                        <input type="text" class="form-control" id="district" placeholder="Район:" name="district">
                    </div>
                    <div>
                        <label for="about">О апартаменте:</label>
                        <textarea class="form-control" rows="5" id="about" name="about"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-success" id="idb">Добавить апартамент</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>
                </form>
            </div>
        </div>
    </div>
    <c:if test="${apartment == null}">
        <p>Ничего не найдено :(</p>
    </c:if>
        <c:if test="${apartment != null}">
        <div class="row">
            <div class="col-md-4">
                <div class="thumbnail">
                    <a href="${pageContext.request.contextPath}/images/ava.jpg">
                        <img src="${pageContext.request.contextPath}/images/ava.jpg" alt="Lights" style="width:100%">
                    </a>
                </div>
            </div>
            <div class="col-md-8">
                <form action="${pageContext.request.contextPath}/admin/update">
                    <div class="form-group">
                        <label for="ida">ID:</label>
                        <input class="form-control input-sm" id="ida" type="text" name="ida" value="${apartment.getId()}">
                        <label for="bedroomUpdate">Количество комнат:</label>
                        <input class="form-control input-sm" id="bedroomUpdate" type="text" name="bedroom" value="${apartment.getBedroom()}">
                        <label for="peopleUpdate">Количество людей:</label>
                        <input class="form-control input-sm" id="peopleUpdate" type="text" name="people" value="${apartment.getPeople()}">
                        <label for="chUpdate">Количество детей:</label>
                        <input class="form-control input-sm" id="chUpdate" type="text" name="children" value="${apartment.getChildren()}">
                        <label for="priceUpdate">Цена:</label>
                        <input class="form-control input-sm" id="priceUpdate" type="text" name="price" value="${apartment.getPrice()}">
                        <label for="districtUpdate">Район:</label>
                        <input class="form-control input-sm" id="districtUpdate" type="text" name="district" value="${apartment.getDistrict()}">
                        <label for="aboutUpdate">О номере:</label>
                        <input class="form-control input-sm" id="aboutUpdate" type="text" name="about" value="${apartment.getAbout()}">
                    </div>
                    <button type="submit" class="btn btn-success">Изменить</button>
                </form>
                <%--<div class="caption">--%>
                    <%--<p>${apartment.getBedroom()}</p>--%>
                    <%--<p>${apartment.getPeople()}</p>--%>
                    <%--<p>${apartment.getChildren()}</p>--%>
                    <%--<p>${apartment.getPrice()}</p>--%>
                    <%--<p>${apartment.getDistrict()}</p>--%>
                    <%--<p>${apartment.getAbout()}</p>--%>
                <%--</div>--%>
            </div>
            </div>
        </c:if>
</div>
</body>
</html>
