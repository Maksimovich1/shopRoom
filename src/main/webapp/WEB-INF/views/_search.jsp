<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 05.09.2018
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form class="form-inline" action="#">
    <div class="form-row">
    <div class="form-group col-lg-3">
        <label for="bedroom">Количество комнат:</label>
        <select class="form-control" id="bedroom">
            <option>0</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
        </select>
    </div>
    <div class="form-group col-lg-3">
        <label for="countp">Кол-во людей(18+):</label>
        <select class="form-control" id="countp">
            <option>0</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
            <option>4</option>
        </select>
    </div>
    <div class="form-group col-lg-3">
        <label for="countc">Количество детей:</label>
        <select class="form-control" id="countc">
            <option>0</option>
            <option>1</option>
            <option>2</option>
            <option>3</option>
        </select>
    </div>

    <div class="form-group col-lg-3">
        <label for="district">Район:</label>
        <select class="form-control" id="district">
            <option>A</option>
            <option>B</option>
            <option>C</option>
            <option>D</option>
        </select>
    </div>
        <br><br>
        <div class="form-group col-lg-4">
    <label for="datein">Дата въезда:</label>
    <input type="date" class="date-cell" id="datein">
        </div>
        <div class="form-group col-lg-4">
    <label for="dateout">Дата выезда:</label>
    <input type="date" id="dateout">
        </div>
        <div class="form-group col-lg-4">
            <label for="price">Максимальная цена:</label>
            <input type="text" class="form-control" id="price" placeholder="price">
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
