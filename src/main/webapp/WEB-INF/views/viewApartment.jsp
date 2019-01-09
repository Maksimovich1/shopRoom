<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 05.12.2018
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Apartment</title>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="static/style.css" rel="stylesheet">
    <link href="static/style-viewApartment.css" rel="stylesheet">
    <script src="webjars/jquery/3.3.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="static/js/loaderPage.js"></script>
</head>
<body style="background-color: #f8f8f8;">
<jsp:include page="_loader.jsp"/>
<jsp:include page="_navbar.jsp"/>
<div class="container">
    <div class="row divCont">
        <div class="col-sm-8 divContent">
            <div class="divContentTitle">${apartment.getNameApartment()}</div>
            <div class="divContentPick">
                <span class="glyphicon glyphicon-plane divSpan" title="Airport"></span>
                <span class="glyphicon glyphicon-shopping-cart divSpan"></span>
                <span class="glyphicon glyphicon-thumbs-up divSpan" title="We recommendation"></span>
                <span class="glyphicon glyphicon-cutlery divSpan"></span>
                <span class="glyphicon glyphicon-phone-alt divSpan"></span>
                <span class="glyphicon glyphicon-bed divSpan"></span>
                <span class="glyphicon glyphicon-signal divSpan"></span>
            </div>
            <div>
                <div class="w3-content">
                    <c:forEach items="${apartment.getPictures()}" var="pic" varStatus="loop">
                        <img class="mySlides" src="${pageContext.request.contextPath}/secure/getImage/${pic.getId()}"
                            <c:if test="${loop.index != 1}">
                                style="display:none";
                        </c:if>
                        >
                    </c:forEach>

                    <%--<img class="mySlides" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room1" style="display:none">
                    <img class="mySlides" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room11" style="display:none">
                    <img class="mySlides" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room12" style="display:none">
                    <img class="mySlides" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room13" style="display:none">
                    <img class="mySlides" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room3" style="display:none">
                    <img class="mySlides" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room14" style="display:none">
                    <img class="mySlides" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room4" style="display:none">--%>


                    <div class="w3-row-padding w3-section">
                        <c:forEach items="${apartment.getPictures()}" var="pic" varStatus="loop">
                            <div class="w3-col">
                                <img class="demo w3-opacity w3-hover-opacity-off" src="${pageContext.request.contextPath}/secure/getImage/${pic.getId()}" style="width:100%;cursor:pointer; max-height: 55px;" onclick="currentDiv(${loop.index + 1})">
                            </div>
                        </c:forEach>
                        <%--<div class="w3-col ">
                            <img class="demo w3-opacity w3-hover-opacity-off" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room1" style="width:100%;cursor:pointer; max-height: 55px;" onclick="currentDiv(2)">
                        </div>
                        <div class="w3-col">
                            <img class="demo w3-opacity w3-hover-opacity-off" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room11" style="width:100%;cursor:pointer; max-height: 55px;" onclick="currentDiv(3)">
                        </div>
                        <div class="w3-col">
                            <img class="demo w3-opacity w3-hover-opacity-off" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room12" style="width:100%;cursor:pointer; max-height: 55px;" onclick="currentDiv(4)">
                        </div>
                        <div class="w3-col">
                            <img class="demo w3-opacity w3-hover-opacity-off" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room13" style="width:100%;cursor:pointer; max-height: 55px;" onclick="currentDiv(5)">
                        </div>
                        <div class="w3-col">
                            <img class="demo w3-opacity w3-hover-opacity-off" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room3" style="width:100%;cursor:pointer; max-height: 55px;" onclick="currentDiv(6)">
                        </div>
                        <div class="w3-col">
                            <img class="demo w3-opacity w3-hover-opacity-off" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room14" style="width:100%;cursor:pointer; max-height: 55px;" onclick="currentDiv(7)">
                        </div>
                        <div class="w3-col">
                            <img class="demo w3-opacity w3-hover-opacity-off" src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room4" style="width:100%;cursor:pointer; max-height: 55px;" onclick="currentDiv(8)">
                        </div>--%>
                    </div>
                </div>
                <script>
                    function currentDiv(n) {
                        showDivs(n);
                    }

                    function showDivs(n) {
                        var i;
                        var slideIndex = n;
                        var x = document.getElementsByClassName("mySlides");
                        var dots = document.getElementsByClassName("demo");
                        if (n > x.length) {slideIndex = 1}
                        if (n < 1) {slideIndex = x.length}
                        for (i = 0; i < x.length; i++) {
                            x[i].style.display = "none";
                        }
                        for (i = 0; i < dots.length; i++) {
                            dots[i].className = dots[i].className.replace(" w3-opacity-off", "");
                        }
                        x[slideIndex-1].style.display = "block";
                        dots[slideIndex-1].className += " w3-opacity-off";
                    }
                </script>
            </div>
        </div>
        <div class="rightColumn">
            <div class="row rightColumnRow">
                <div class="price">
                    <h2>Полная стоимость: ${paymentCustomModel.getSummary()} <span class="glyphicon glyphicon-euro"></span></h2>
                </div>
                <div class="priceColumnBody">
                    <div class="row rowMy1">
                        <%--<div class="col-sm-6">
                            <h5 class="viewStyle">Дата заезда</h5>
                            <h5 class="viewStyle">Дата выезда</h5>
                            <c:if test="${oldPrice != null}">
                                <h5 class="viewStyle">Стандарт цена</h5>
                            </c:if>
                            <h5 class="viewStyle" style="color: red">Текущая цена</h5>


                        </div>
                        <div class="col-sm-6">
                            <h5 class="viewStyle">${paymentCustomModel.getDateIn()}</h5>
                            <h5 class="viewStyle">${paymentCustomModel.getDateOut()}</h5>
                            <c:if test="${oldPrice != null}">
                                <h5 class="viewStyle">${oldPrice}</h5>
                            </c:if>
                            <h5 class="viewStyle">${apartment.getPrice()}</h5>
                        </div>--%>
                        <table class="table">
                            <tbody>
                            <tr>
                                <th scope="row">Дата заезда</th>
                                <td>${paymentCustomModel.getDateIn()}</td>
                            </tr>
                            <tr>
                                <th scope="row">Дата выезда</th>
                                <td>${paymentCustomModel.getDateOut()}</td>
                            </tr>
                            <tr>
                                <th scope="row">Ночей</th>
                                <td>${paymentCustomModel.getCountNight()}</td>
                            </tr>
                            <tr>
                                <th scope="row">Текущая цена</th>
                                <td>${apartment.getPrice()}</td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                    <div class="divButton">
                        <form action="${pageContext.request.contextPath}/secure/order" method="post">
                            <input name="dateIn" value="${paymentCustomModel.getDateIn()}" type="hidden"/>
                            <input name="dateOut" value="${paymentCustomModel.getDateOut()}" type="hidden"/>
                            <input name="idApartment" value="${paymentCustomModel.getApartmentName()}" type="hidden"/>
                            <c:if test="${checkCreditPayment == true || checkFullPayment == true}">
                                <button class="buttonBuyNow" type="submit" title="submit">Buy now ${percent} $</button>
                            </c:if>
                            <c:if test="${checkCreditPayment != true && checkFullPayment != true}">
                                <label style="color: red">Недостаточно средств на балансе</label>
                            </c:if>
                            <div style="padding: 5px;color: brown;">
                                Есть два способа бронирования: предоплата и полная оплата. Первый, внести % от полной стоимости за бронирование,
                                а полный расчет происходит на месте при заселении. Предоплата возращается после выезда на счет. Второй способ,
                                списание со счета всей суммы, на месте ничего платить не надо.
                            </div>
                            <c:if test="${checkCreditPayment != true}">
                                <div>
                                    <label style="color: orangered">
                                        <%--credit payment--%>
                                        Для бронирования этого номера по предоплате вам необходимо ${percent} euro.
                                        Данная сумма превышает Ваш баланс.
                                    </label>
                                </div>
                            </c:if>
                            <c:if test="${checkCreditPayment == true}">
                            <div>
                                <label>
                                    <input type="radio" name="paymentMethod" value="1"> <%--credit payment--%>
                                    Для бронирования этого номера по предоплате вам необходимо ${percent} euro.
                                    Эта сумма будет списана с вашего счета.
                                </label>
                            </div>
                            </c:if>
                            <c:if test="${checkFullPayment == true}">
                            <div>
                                <label>
                                    <input type="radio" name="paymentMethod" value="2"> <%--full payment--%>
                                    Для бронирования этого номера по полной оплате вам необходимо ${paymentCustomModel.getSummary()} euro.
                                    Эта сумма будет списана с вашего счета.
                                </label>
                            </div>
                            </c:if>
                            <c:if test="${checkFullPayment != true}">
                                <div>
                                    <label style="color: orangered">
                                        <%--full payment--%>
                                        Для бронирования этого номера по полной оплате вам необходимо ${paymentCustomModel.getSummary()} euro.
                                        Данная сумма превышает Ваш баланс.
                                    </label>
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <div class="body_about" style="background-color: white">
        <div class="aboutTitle">
            <h2>Описание номера, местоположение.</h2>
        </div>
        <div class="row rowsMy">
            <div class="aboutMapDiv">
                <div id="map-canvas" style="height:100%; width:100%"></div>
            </div>
            <div class="aboutDiv">
                <table class="table">
                    <tbody>
                    <tr>
                        <th scope="row">Площадь(общая)</th>
                        <td>${apartment.getSize_room()} m2</td>
                    </tr>
                    <tr>
                        <th scope="row">Спальни</th>
                        <td>${apartment.getBedroom()} шт.</td>
                    </tr>
                    <tr>
                        <th scope="row">Макс. число людей(с детьми)</th>
                        <td>${apartment.getPeople() + apartment.getChildren()} шт.</td>
                    </tr>
                    <tr>
                        <th scope="row">Количество ванных</th>
                        <td>${apartment.getBathroom()} шт.</td>
                    </tr>
                    <tr>
                        <th scope="row">Район</th>
                        <td>${apartment.getDistrict()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Адрес</th>
                        <td>${apartment.getAddress()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Односпальных</th>
                        <td>${apartment.getBed1()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Двухспальных</th>
                        <td>${apartment.getBed2()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Заезд с</th>
                        <td>${apartment.getCheck_in()}</td>
                    </tr>
                    <tr>
                        <th scope="row">Выезд до</th>
                        <td>${apartment.getCheck_out()}</td>
                    </tr>
                    <%--<tr class="active">
                        <th scope="row">О нас</th>
                        <td>${apartment.getAbout()}</td>
                    </tr>--%>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
    <div style="background-color: white">
        <div class="titlePrice">
            <h2>Описание стоимости заказа по ночам</h2>
            <h5>Текущая цена <div class="newPrice"> </div></h5>
            <h5>Старая цена <div class="oldPrice"> </div></h5>
        </div>
        <div class="row mainDivPrice">
            <c:forEach items="${pricesModelList}" var="priceModel">
                <div class="col-sm-1 labelPrice">
                    <div class="labelPriceTitle">
                            ${priceModel.getDate()}
                    </div>
                    <div class="labelPriceBody <c:if test="${priceModel.isFlag()}">labelPriceBodyAnyPrice</c:if>">
                            ${priceModel.getPrice()}<span class="glyphicon glyphicon-euro"></span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <script
            src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCJhRpqD0PfvesswFwc3_YIaSjS8Mc-JI4&callback=initMap">
    </script>
    <script>
        var map;
        function initialize() {
            var uluru = {lat: ${apartment.getLatitude()}, lng: ${apartment.getLongitude()}};
            var mapOptions = {
                zoom: 8,
                center: new google.maps.LatLng(uluru)
            };
            map = new google.maps.Map(document.getElementById('map-canvas'),
                mapOptions);
            var marker = new google.maps.Marker({position: uluru, map: map});
        }

        google.maps.event.addDomListener(window, 'load', initialize);
    </script>

</div>
</body>
</html>
