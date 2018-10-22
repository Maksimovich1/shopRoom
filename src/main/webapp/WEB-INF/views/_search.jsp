<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 05.09.2018
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-inline" action="${pageContext.request.contextPath}/secure/productList">
    <div class="form-row">
    <div class="form-group col-lg-3">
        <label for="bedroom">Количество комнат:</label>
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
    <div class="form-group col-lg-3">
        <label for="countP">Кол-во людей(18+):</label>
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
    <div class="form-group col-lg-3">
        <label for="countC">Количество детей:</label>
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

    <div class="form-group col-lg-3">
        <label for="district">Район:</label>
        <select class="form-control" id="district" name="district" selected="${param.district}">
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
        <br><br>
        <div class="form-group col-lg-4">
    <label for="dateIn">Дата въезда:</label>
    <input type="date" class="date-cell" id="dateIn" name="dateIn" value="${param.dateIn}">
        </div>
        <div class="form-group col-lg-4">
    <label for="dateOut">Дата выезда:</label>
    <input type="date" id="dateOut" name="dateOut" value="${param.dateOut}">
        </div>
        <div class="form-group col-lg-4">
            <label for="priceMax">Максимальная цена:</label>
            <input type="text" class="form-control" id="priceMax" value="${param.priceMax}" autocomplete="off" placeholder="price" name="priceMax">
        </div>
            <br>
    <div class="checkbox">
        <label><input type="checkbox" name="remember">Склейка апартаментов</label>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-primary">Поиск</button>
    </div>
</form>
