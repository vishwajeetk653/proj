<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 4/30/2015
  Time: 1:21 PM
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
    <title>Menu Cards</title>
</head>
<body>


<table align="right">
    <tr>
        <td colspan="2">

        </td>
    </tr>
    <form method="post" action="/clientController">
        <input type="hidden" name="command" value="exit">

        <tr>
            <td><%--@declare id="login"--%>
                <label for="login"><fmt:message key="login.label.login"/></label> : ${client.login}</td>
            <td>
                <fmt:message key="menuCards.button.exit" var="Exit"/>
                <input type="submit" value="${Exit}">
            </td>
        </tr>
    </form>
</table>
<br>


<p align="center">
    <lable for="menu"><fmt:message key="menuCards.button.menu.cards"/></lable>
</p>

<table align="center">
    <form method="post" action="/clientController">
        <input type="hidden" name="command" value="use card">
        <tr>
            <td>
                <%--@declare id="account"--%>
                <label for="account"><fmt:message key="table.title.account"/></label>
                <select type="text" name="account" value="">
                    <c:forEach items="${cards}" var="card">
                        <option> ${card.account}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <fmt:message key="menuCards.button.use_card" var="Use_card"/>
                <input type="submit" name="command" value="${Use_card}">
            </td>
        </tr>
    </form>
</table>

</body>
</html>
