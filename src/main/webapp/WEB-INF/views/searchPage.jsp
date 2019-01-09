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
    <script src="static/js/loaderPage.js"></script>
</head>
<body class="bacgr">
<jsp:include page="_loader.jsp"/>
<jsp:include page="_navbar.jsp"/>
<c:if test="${view == true && apartmentList.size() > 0}">
    <div class="info">
        <h5>
            <strong><spring:message code="searchPage.info.countDay"/>:</strong> ${countDay}.
            <strong><spring:message code="searchPage.info.maxPrice"/>:</strong> ${price} $
            <strong><spring:message code="searchPage.info.countPerson"/>:</strong> ${param.countP}
            <strong><spring:message code="searchPage.info.countChildren"/>:</strong> ${param.countC}
            <strong><spring:message code="searchPage.info.staytion"/>:</strong> [ ${param.dateIn} - ${param.dateOut} ]
        </h5>
    </div>
</c:if>
<c:if test="${view == false}">
    <br>
</c:if>
<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3my">
            <form action="${pageContext.request.contextPath}/secure/productList" method="get" name="searchApartment" onsubmit="return onsub()">
                <div>
                    <h2 style="margin-left: 75px;"><spring:message code="searchPage.search.title"/><span class="glyphicon glyphicon-home"></span></h2>
                    <div class="form-group">
                        <label for="bedroom"><spring:message code="searchPage.search.countBedroom"/></label>
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
                        <label for="countP"><span class="glyphicon glyphicon-user"></span> <spring:message code="searchPage.info.countPerson"/></label>
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
                        <label for="countC"> <spring:message code="searchPage.info.countChildren"/></label>
                        <select class="form-control" id="countC" name="countC">
                            <option
                                    <c:if test="${param.countC == '0'}">
                                        selected="selected"
                                    </c:if> >
                                0
                            </option>
                            <option
                                    <c:if test="${param.countC == '1'}">
                                        selected="selected"
                                    </c:if> >
                                1
                            </option>
                            <option
                                    <c:if test="${param.countC == '2'}">
                                        selected="selected"
                                    </c:if> >
                                2
                            </option>
                            <option
                                    <c:if test="${param.countC == '3'}">
                                        selected="selected"
                                    </c:if> >
                                3
                            </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="district"><span class="glyphicon glyphicon-globe"></span> <spring:message code="searchPage.search.districtTitle"/></label>
                        <select class="form-control" id="district" name="district">
                            <option
                                    <c:if test="${param.district == '1'}">
                                        selected="selected"
                                    </c:if> value="1">
                                <spring:message code="searchPage.search.district1"/>
                            </option>
                            <option
                                    <c:if test="${param.district == '2'}">
                                        selected="selected"
                                    </c:if> value="2">
                                <spring:message code="searchPage.search.district2"/>
                            </option>
                            <option
                                    <c:if test="${param.district == '3'}">
                                        selected="selected"
                                    </c:if> value="3">
                                <spring:message code="searchPage.search.district3"/>
                            </option>
                            <option
                                    <c:if test="${param.district == '4'}">
                                        selected="selected"
                                    </c:if> value="4" >
                                <spring:message code="searchPage.search.district4"/>
                            </option>
                            <option
                                    <c:if test="${param.district == '5'}">
                                        selected="selected"
                                    </c:if> value="5" >
                                <spring:message code="searchPage.search.district5"/>
                            </option>
                            <option
                                    <c:if test="${param.district == '6'}">
                                        selected="selected"
                                    </c:if> value="6" >
                                <spring:message code="searchPage.search.district6"/>
                            </option>
                            <option
                                    <c:if test="${param.district == '0'}">
                                        selected="selected"
                                    </c:if> value="0" >
                                <spring:message code="searchPage.search.district0"/>
                            </option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="dateIn"><span class="glyphicon glyphicon-log-in"></span> <spring:message code="searchPage.search.dateIn"/></label>
                        <input type="date" class="form-control" id="dateIn" name="dateIn"  value="${param.dateIn}"/>
                    </div>
                    <div class="form-group">
                        <label for="dateOut"><span class="glyphicon glyphicon-log-out"></span> <spring:message code="searchPage.search.dateOut"/></label>
                        <input type="date" class="form-control" id="dateOut" name="dateOut"  value="${param.dateOut}"/>
                    </div>
                    <div class="form-group">
                        <label for="priceMax"><span class="glyphicon glyphicon-euro"></span> <spring:message code="searchPage.info.maxPrice"/></label>
                        <input type="text" class="form-control" id="priceMax" value="" autocomplete="off" placeholder="Price (default 100)" name="priceMax">
                    </div>
                    <button type="submit" class="btn btn-primary btnmy" id="sendToServer"><spring:message code="searchPage.search.submit"/></button>
                    <br>
                </div>
            </form>
        </div>
        <script>
            function onsub() {
                var dateIn = document.forms["searchApartment"]["dateIn"].value;
                var dateOut = document.forms["searchApartment"]["dateOut"].value;
                var maxPrice = document.forms["searchApartment"]["priceMax"].value;
                if (isNaN(maxPrice)){
                    alert("Цена указана не корректно");
                    return false;
                }
                if (dateIn === null || dateIn === '' || dateOut === null || dateOut === ''){
                    alert("<spring:message code="searchPage.alert.invalidDate"/>");
                    return false;
                }
                if (new Date() >= new Date(dateIn)){
                    alert("<spring:message code="searchPage.alert.invalidDate1"/>");
                    return false;
                }
                console.log(new Date());
                if (new Date(dateIn) >= new Date(dateOut)){
                    alert("<spring:message code="searchPage.alert.invalidDate2"/>");
                    return false;
                }
                var preloader = $('#p_prldr'), svg_anm   = preloader.find('.svg_anm');
                svg_anm.fadeIn();
                preloader.fadeIn('slow');
                console.log("123");
                for (var i = 0; i < 1000000; i++) {

                }
                return true;
            }
        </script>
        <div class="col-sm-9">


            <div class="row">
                <c:if test="${invalidDate == true}">
                    <div class="alert alert-warning" style="height: auto;">
                        <spring:message code="searchPage.info.errorServerDate"/>
                    </div>
                </c:if>
                <c:if test="${invalidNumber == true}">
                    <div class="alert alert-warning" style="height: auto;">
                        <spring:message code="searchPage.info.errorServerNumber"/>
                    </div>
                </c:if>
                <c:if test="${view == true && apartmentList.size() > 0}">
                    <%--<div class="info" style="margin-left: 17px; margin-right: 20px">
                        <h5>
                            <strong>Количество дней:</strong> ${countDay}.
                            <strong>Максимальная цена:</strong> ${price}
                        </h5>
                    </div>--%>
                    <c:forEach items="${apartmentList}" var="apartment">

                        <div class="coll-search">
                            <div class="wellMy">
                                <div class="thumbnail thumbnailmy">
                                        <div class="carousel-inner" style="max-height: 200px">
                                            <c:forEach items="${apartment.getPictures()}" var="pic" varStatus="loop">
                                                <c:if test="${loop.index <= 1}">
                                                <div class="item active">
                                                    <img src="${pageContext.request.contextPath}/secure/getImage/${pic.getId()}" alt="<spring:message code="searchPage.iconApartment.photo"/>" style="width:100%;">
                                                    <div class="carousel-caption">
                                                    </div>
                                                </div>
                                                </c:if>
                                            </c:forEach>

                                           <%-- <div class="item">
                                                <img src="${pageContext.request.contextPath}/secure/getImage/${apartment.getId()}/room2" alt="<spring:message code="searchPage.iconApartment.photo"/>" style="width:100%;">
                                                <div class="carousel-caption">
                                                </div>
                                            </div>--%>

                                        </div>


                                    <div class="caption row">
                                        <p class="col-md-6"><spring:message code="searchPage.iconApartment.room"/>: ${apartment.getBedroom()}</p>
                                        <p class="col-md-6"><spring:message code="searchPage.iconApartment.people"/>: ${apartment.getPeople()}</p>
                                        <p class="col-md-6"><spring:message code="searchPage.iconApartment.child"/>: ${apartment.getChildren()}</p>
                                        <p class="col-md-6"><spring:message code="searchPage.iconApartment.pricetheday"/>: ${apartment.getPrice()} </p>
                                        <p class="col-md-8 price_finish"><spring:message code="searchPage.iconApartment.price"/>: ${apartment.getPrice() * countDay} $</p>
                                    </div>

                                    <%--<div class="aboutapartment">
                                        <button type="button" class="btn btn-info aboutapartmentbutton" data-toggle="collapse" data-target="#demo${apartment.getId()}">
                                            <spring:message code="searchPage.iconApartment.button.about"/></button>
                                        <div id="demo${apartment.getId()}" class="collapse">
                                                ${apartment.getAbout()}
                                        </div>
                                    </div>--%>
                                    <div class="aboutapartment">
                                            <%--<a type="button" href="<c:url value="/secure/product/apartment/${apartment.getId()}"/>"
                                               class="btn btn-info aboutapartmentbutton">
                                                <spring:message code="searchPage.iconApartment.button.about"/></a>--%>
                                        <form action="/secure/product/apartment/${apartment.getId()}" method="post">
                                            <input name="dateIn" type="hidden" value="${param.dateIn}">
                                            <input name="dateOut" type="hidden" value="${param.dateOut}">
                                            <button class="btn btn-info aboutapartmentbutton" type="submit"><spring:message code="searchPage.iconApartment.button.about"/></button>
                                        </form>

                                    </div>
                                    <form action="${pageContext.request.contextPath}/secure/quickOrder" method="post">
                                        <input name="dateIn" type="hidden" value="${param.dateIn}">
                                        <input name="dateOut" type="hidden" value="${param.dateOut}">
                                        <input name="idApartment" value="${apartment.getId()}" type="hidden">
                                        <button class="btn btn-success" type="submit" style="width: 100%"><spring:message code="searchPage.iconApartment.button.bye"/> </button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <c:if test="${view == false}">
                    <h1 class="hsearch"><spring:message code="searchPage.body.empty"/></h1>
                </c:if>
                <c:if test="${view == true && apartmentList.size() == 0}">
                    <h1 class="hsearch"><spring:message code="searchPage.body.emptysearch"/><br>
                        <spring:message code="searchPage.body.emptysearchsupport"/></h1>
                </c:if>
            </div>
        </div>
    </div>
</div>

<br>
<jsp:include page="_footer.jsp"/>
</body>
</html>
