<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 4/21/2015
  Time: 10:19 PM
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
  <title>Error</title>
</head>
<body>

<h3>Error</h3>
<hr />
<jsp:expression>
  (request.getAttribute("errorMessage") != null)
  ? (String) request.getAttribute("errorMessage")
  : "unknown error"</jsp:expression>
<hr />

<%--<a href="menuCards.jsp">Main</a><br>--%>
</body>
</html>