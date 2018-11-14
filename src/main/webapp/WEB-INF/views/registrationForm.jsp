<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Andrew
  Date: 04.11.2018
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"
          rel="stylesheet">
    <link href="${pageContext.request.contextPath}/static/style-regist.css" rel="stylesheet">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Title</title>
</head>
<body>
<div class="container">
        <div id="logbox">
            <form id="signup" method="post" action="${pageContext.request.contextPath}/registration">
                <h1>create an account</h1>
                <input name="username" type="text" placeholder="What's your username or login?" pattern="^[\w]{3,16}$" autofocus="autofocus" required="required" class="input pass"/>
                <input name="password1" type="password" placeholder="Choose a password" required="required" class="input pass"/>
                <input name="password2" type="password" placeholder="Confirm password" required="required" class="input pass"/>
                <%--<input name="email" type="email" placeholder="Email address" class="input pass"/>--%>
                <input type="submit" value="Sign me up!" class="inputButton"/>
                <div class="text-center">
                    Already have an account? <a href="<c:url value="/login"/>" id="login_id">Login</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
