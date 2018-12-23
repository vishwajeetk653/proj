<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 4/21/2015
  Time: 11:54 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.text"/>
<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Register</title>
</head>
<body>
<div align="center">
    <form method="post" action="/clientController">
        <input type="hidden" name="command" value="register">

        <%--@declare id="login"--%>
        <%--@declare id="password"--%>
        <%--@declare id="name"--%>
        <%--@declare id="passpord"--%>
        <%--@declare id="lastname"--%>
        <%--@declare id="phone"--%>
        <%--@declare id="menu"--%>
        <label for="menu"><fmt:message key="register.label.menu"/></label>
        <br>
        <br>
        <label for="login"><fmt:message key="register.label.login"/></label><br>
        <input type="text" name="login" value=""><br>
        <label for="password"><fmt:message key="register.label.password"/></label><br>
        <input type="text" name="password" value=""><br>
        <label for="name"><fmt:message key="register.label.name"/></label><br>
        <input type="text" name="name" value=""><br>
        <label for="lastName"><fmt:message key="register.label.last.name"/></label><br>
        <input type="text" name="lastName" value=""><br>

        <label for="passpord"><fmt:message key="register.label.passport"/></label><br>
        <input type="text" name="passport" value=""><br>
        <label for="phone"><fmt:message key="register.label.phone"/></label><br>
        <input type="text" name="phone" value=""><br>
        <fmt:message key="register.button.register" var="Register"/>
        <input type="submit" name="command" value="${Register}">
    </form>
    <br>
    <fmt:message key="login.button.enter" var="Login"/>
    <a href="http://localhost:8080/">${Login}</a>
</div>
</body>
</html>


