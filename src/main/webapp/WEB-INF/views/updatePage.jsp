<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="url" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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
    <link href="static/style.css" rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #f9f6f6">
<jsp:include page="_navbar.jsp"/>
<div class="container">

    <c:if test="${addStatus == 'true'}">
        <div class="alert alert-success">
            <strong>Success!</strong> Apartment is add.
        </div>
    </c:if>
    <c:if test="${addStatus == 'false'}">
        <div class="alert alert-warning">
            <strong>Error!</strong> Апартамент не был добавлен!
                ${message}
        </div>
    </c:if>
    <!-- Trigger the modal with a button -->
    <div class="row addorupdate">
        <div class="col-md-6">
            <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#myModal">+ Добавить апартамент</button>
        </div>

        <div class="col-md-6">
            <form action="${pageContext.request.contextPath}/admin/searchForId">
                <div class="input-group col-xs-4" style="width: 70%;">
                    <input type="text" class="form-control input-lg" placeholder="Search" name="ida">
                    <div class="input-group-btn">
                        <button class="btn btn-success btn-lg" type="submit">Поиск</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

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
                        <div class="col-xs-6 marginUpdateOrAdd">
                            <label for="id1">ID:</label>
                            <input type="text" class="form-control" id="id1" placeholder="ID" name="id1">
                        </div>
                        <jsp:include page="_viewmodel_apartment.jsp"/>
                        <div class="col-xs-12 " >
                            <button type="submit" class="btn btn-success" id="idb">Добавить апартамент</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        </div>

                    </div>
                    <div class="modal-footer" style="border-top: 0">

                    </div>
                </form>
            </div>
        </div>
    </div>
    <c:if test="${apartment == null}">
        <p>Ничего не найдено :(</p>
    </c:if>
    <c:if test="${apartment != null}">
        <div class="row" style="border: 1px solid antiquewhite;margin-top: 1%;background-color: white; margin-bottom: 1%">
            <div class="col-md-4" style="padding-top: 2%">
                <div class="row">
                    <c:forEach items="${pictureList}" var="pic">
                        <div class="col-md-6 divMd6Image">
                            <div>
                            <a class="deleteImage" href=" <c:url value="/admin/deleteImage">
                                        <c:param name="idImage" value="${pic.getId()}"/>
                                        <c:param name="idApart" value="${apartment.getId()}"/>
                                      </c:url> " >X</a>
                            </div>
                            <a href="/secure/getImage/${pic.getId()}">
                                <img src="/secure/getImage/${pic.getId()}" alt="Lights" style="width:100%; max-height: 100px">
                            </a>
                        </div>
                    </c:forEach>


                </div>
            </div>
            <div class="col-md-8">
                <form action="${pageContext.request.contextPath}/admin/update">

                    <div class="form-group" style="margin-bottom: 20px">
                        <div class="col-xs-12" >
                            <label for="ida">ID:</label>
                            <input class="form-control input-sm" id="ida" type="text" name="ida" value="${apartment.getId()}"  >
                        </div>
                        <jsp:include page="_viewmodel_apartment.jsp"/>
                        <div class="col-xs-8" style="margin-bottom: 20px">
                            <button type="submit" class="btn btn-success">Применить изменения</button>
                        </div>
                    </div>
                </form>
                <div     style="margin-bottom: 10px">
                    <form name="fileUpload" class="form-inline" method="POST" action="${pageContext.request.contextPath}/admin/save_image" enctype="multipart/form-data">
                        <label>Select File</label> <br />
                        <input type="file" name="file" class="fileUploadStyle" />
                        <input type="hidden" value="${apartment.getId()}" name="idApart">
                        <input type="submit" class="btn btn-success" name="submit" value="Upload" />
                    </form>
                </div>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>
