<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 13.09.2018
  Time: 9:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="static/style.css" rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="static/js/blockDatePage.js"></script>
</head>
<body>
<jsp:include page="_navbar.jsp"/>
<br>
<c:if test="${alert != null}">
<div class="info">
    <h4>
        <strong>${alert}</strong>
    </h4>
</div>
    <br>
</c:if>
<div class="row" style="width: 100%">
    <div class="col-sm-6 leftBody">
        <div class="leftBodyLock">
            <div class="leftBodyTitle"><h2>Блокировка дат на апартамент</h2></div>
            <div>
                <form>
                    <div class="selectForm colInp">
                        <label for="idApartment">Апартамент</label>
                        <select class="form-control " id="idApartment" name="idApartment">
                            <c:forEach items="${apartmentList}" var="apartment">
                                <option value="${apartment.getId()}">
                                    Apartment name: ${apartment.getId()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <div class="form-group colInp">
                            <label for="periodIn"><span class="glyphicon glyphicon-log-in"></span> Начало периода</label>
                            <input type="date" class="form-control " id="periodIn" name="periodIn"  value="${param.dateIn}"/>
                        </div>
                        <div class="form-group colInp">
                            <label for="periodOut"><span class="glyphicon glyphicon-log-out"></span> Конец периода</label>
                            <input type="date" class="form-control " id="periodOut" name="periodOut"/>
                        </div>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-success">Заблокировать даты</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="leftBodyUnlock">
            <div class="leftBodyTitle"><h4>Просмотреть и разблокировать даты на апартамент</h4></div>
            <div>
                <form id="searchBlockDateForApartment">
                <div class="selectForm colInp">
                    <label for="idApartmentPeriod">Апартамент</label>
                    <select class="form-control " id="idApartmentPeriod" name="idPeriod">
                         <c:forEach items="${apartmentList}" var="apartment">
                             <option value="${apartment.getId()}">
                                 Apartment name: ${apartment.getId()}
                             </option>
                         </c:forEach>
                    </select>
                </div>
                <div>
                    <button type="submit" class="btn btn-success">Отобразить даты</button>
                </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-sm-6 rightBody" >
        <div class="rightBodyTitle"><h2>Создать новую цену на апартамент</h2></div>
        <form action="${pageContext.request.contextPath}/admin/createNewPrice" method="get">
            <div class="selectForm colInp">
                <label for="idApar">Выберите апартамент</label>
                <select class="form-control" id="idApar" name="idApar">
                    <c:forEach items="${apartmentList}" var="apartment">
                        <option value="${apartment.getId()}">
                            Apartment name: ${apartment.getId()}
                        </option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group colInp">
                <label for="newPeriodIn"><span class="glyphicon glyphicon-log-in"></span> Начало периода</label>
                <input type="date" class="form-control " id="newPeriodIn" name="newPeriodIn"/>
            </div>
            <div class="form-group colInp">
                <label for="newPeriodOut"><span class="glyphicon glyphicon-log-out"></span> Конец периода</label>
                <input type="date" class="form-control " id="newPeriodOut" name="newPeriodOut"/>
            </div>
            <div class="form-group colInp">
                <label for="namePeriod">Имя периода</label>
                <input type="text" class="form-control " id="namePeriod" value="" autocomplete="off" placeholder="Имя периода" name="namePeriod">
            </div>
            <div class="form-group colInp">
                <label for="newPrice">Новая цена</label>
                <input type="text" class="form-control colInp" id="newPrice" value="" autocomplete="off" placeholder="Новая цена" name="newPrice">
            </div>
            <div>
                <button type="submit" class="btn btn-success">Создать</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>