<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 21.12.2018
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="countP">Колличество людей 18+:</label>
    <input type="text" class="form-control" id="countP" placeholder="Количество людей 18+" name="people" <c:if test="${apartment != null}"> value="${apartment.getPeople()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="countC">Колличество детей:</label>
    <input type="text" class="form-control" id="countC" placeholder="Количество детей" name="children" <c:if test="${apartment != null}"> value="${apartment.getChildren()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="bedroom">Колличество комнат</label>
    <input type="text" class="form-control" id="bedroom" placeholder="Количество комнат" name="bedroom" <c:if test="${apartment != null}"> value="${apartment.getBedroom()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="price">Цена:</label>
    <input type="text" class="form-control" id="price" placeholder="Цена" name="price" <c:if test="${apartment != null}"> value="${apartment.getPrice()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="bathroom">Кол-во ванных:</label>
    <input type="text" class="form-control" id="bathroom" placeholder="0" name="bathroom" <c:if test="${apartment != null}"> value="${apartment.getBathroom()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="bathroom">Площадь:</label>
    <input type="text" class="form-control" id="size" placeholder="Введите площадь" name="size" <c:if test="${apartment != null}"> value="${apartment.getSize_room()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="address">Адрес:</label>
    <input type="text" class="form-control" id="address" placeholder="Адрес" name="address" <c:if test="${apartment != null}"> value="${apartment.getAddress()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="nameApar">Адрес:</label>
    <input type="text" class="form-control" id="nameApar" placeholder="Имя апартамента" name="nameApar" <c:if test="${apartment != null}"> value="${apartment.getNameApartment()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="nameManager">Имя менеджера:</label>
    <input type="text" class="form-control" id="nameManager" placeholder="Имя менеджера" name="nameManager" <c:if test="${apartment != null}"> value="${apartment.getNameManager()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="phoneManager">Телефон менеджера:</label>
    <input type="text" class="form-control" id="phoneManager" placeholder="Телефон менеджера" name="phoneManager" <c:if test="${apartment != null}"> value="${apartment.getPhoneManager()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="emailManager">Email менеджера:</label>
    <input type="text" class="form-control" id="emailManager" placeholder="email менеджера" name="emailManager" <c:if test="${apartment != null}"> value="${apartment.getEmailManager()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="checkIn">Check In:</label>
    <input type="text" class="form-control" id="checkIn" placeholder="Check In" name="checkIn" <c:if test="${apartment != null}"> value="${apartment.getCheck_in()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="checkOut">Check Out:</label>
    <input type="text" class="form-control" id="checkOut" placeholder="Check Out" name="checkOut" <c:if test="${apartment != null}"> value="${apartment.getCheck_out()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="possessions">Залог за сохранность:</label>
    <input type="text" class="form-control" id="possessions" placeholder="0 - не требуется" name="possessions" <c:if test="${apartment != null}"> value="${apartment.getPossessions()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="discount">Скидка:</label>
    <input type="text" class="form-control" id="discount" placeholder="0" name="discount" <c:if test="${apartment != null}"> value="${apartment.getDiscount()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="percent">Процент списывания со счета</label>
    <input type="text" class="form-control" id="percent" placeholder="20" name="percent" <c:if test="${apartment != null}"> value="${apartment.getPercent_of_price()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="bed1">Кол-во односпальных</label>
    <input type="text" class="form-control" id="bed1" placeholder="1" name="bed1" <c:if test="${apartment != null}"> value="${apartment.getBed1()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="bed2">Кол-во двухспальных</label>
    <input type="text" class="form-control" id="bed2" placeholder="0" name="bed2" <c:if test="${apartment != null}"> value="${apartment.getBed2()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="namepartner">Владелец апартамента</label>
    <select class="form-control" id="namepartner" name="namepartner" >
        <%--<c:forEach items="${apartmentList}" var="apartment">
            <option value="${apartment.getId()}">
                Apartment name: ${apartment.getId()}
            </option>
        </c:forEach>--%>
    </select>
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="district">Район</label>
    <%--<input type="text" class="form-control" id="district" placeholder="Район:" name="district">--%>
    <select class="form-control" name="district" id="district">
        <option
                <c:if test="${apartment.getDistrict() == '1'}">
                    selected="selected"
                </c:if> value="1">
            <spring:message code="searchPage.search.district1"/>
        </option>
        <option
                <c:if test="${apartment.getDistrict() == '2'}">
                    selected="selected"
                </c:if> value="2">
            <spring:message code="searchPage.search.district2"/>
        </option>
        <option
                <c:if test="${apartment.getDistrict() == '3'}">
                    selected="selected"
                </c:if> value="3">
            <spring:message code="searchPage.search.district3"/>
        </option>
        <option
                <c:if test="${apartment.getDistrict() == '4'}">
                    selected="selected"
                </c:if> value="4" >
            <spring:message code="searchPage.search.district4"/>
        </option>
        <option
                <c:if test="${apartment.getDistrict() == '5'}">
                    selected="selected"
                </c:if> value="5" >
            <spring:message code="searchPage.search.district5"/>
        </option>
        <option
                <c:if test="${apartment.getDistrict() == '6'}">
                    selected="selected"
                </c:if> value="6" >
            <spring:message code="searchPage.search.district6"/>
        </option>
    </select>
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="urlbooking">URL</label>
    <input type="text" class="form-control" id="urlbooking" placeholder="URL Booking" name="urlbooking" <c:if test="${apartment != null}"> value="${apartment.getUrlBooking()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd" >
    <label for="latbooking">Latitude</label>
    <input type="number" class="form-control" id="latbooking" placeholder="Latitude (default 0)" name="lat" <c:if test="${apartment != null}"> value="${apartment.getLatitude()}" </c:if> >
</div>
<div class="col-xs-6 marginUpdateOrAdd">
    <label for="lngbooking">Longitude</label>
    <input type="number" class="form-control" id="lngbooking" placeholder="Latitude (default 0)" name="lng" <c:if test="${apartment != null}"> value="${apartment.getLongitude()}" </c:if> >
</div>
<div class="col-sm-2" style="width: 12%; border: 1px solid aqua; margin-left: 15px; margin-right: 10px;">
    <label for="wifi">wi-fi</label>
    <input type="checkbox" class="form-control" id="wifi" value="1" name="wifi" <c:if test="${apartment != null && apartment.isWifi() == true}"> checked </c:if> >
</div>
<div class="col-xs-2" style="border: 1px solid chartreuse; margin-right: 10px;">
    <label for="pool">Бассейн</label>
    <input type="checkbox" class="form-control" id="pool" value="1" name="pool" <c:if test="${apartment != null && apartment.isPool() == true}"> checked</c:if> >
</div>
<div class="col-xs-3 marginUpdateOrAdd" style="border: 1px solid chartreuse; width: 35%; margin-right: 10px;">
    <label for="heated_pool">Бассейн с подогревом</label>
    <input type="checkbox" class="form-control" id="heated_pool" value="1" name="heated_pool" <c:if test="${apartment != null && apartment.isHeated_pool() == true}"> checked </c:if> >
</div>
<div class="col-xs-2 marginUpdateOrAdd" style="border: 1px solid #67ffd1; width: 35%; margin-right: 10px;">
    <label for="animal">Животные:</label>
    <input type="checkbox" class="form-control" id="animal" value="1" name="animal" <c:if test="${apartment != null && aprtment.isAnimal() == true}"> checked </c:if> >
</div>
<div class="col-xs-12 marginUpdateOrAdd">
    <label for="about">О апартаменте:</label>
    <textarea class="form-control" rows="5" id="about" name="about"><c:if test="${apartment != null}"> ${apartment.getAbout()}</c:if></textarea>
</div>