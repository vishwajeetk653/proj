<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 4/27/2015
  Time: 10:50 AM
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
    <title>History card</title>
</head>
<body>
<table align="right">
    <tr>
        <td><%--@declare id="login"--%>
            <label for="login"><fmt:message key="login.label.login"/></label> : ${client.login}</td>
        <td>
            <form method="post" action="/clientController">
                <input type="hidden" name="command" value="exit">
                <fmt:message key="menuCards.button.exit" var="Exit"/>
                <input on class="link" type="submit" name="command" value="${Exit}">
            </form>
        </td>
    </tr>
    <tr>
        <td colspan="2">
            <form method="post" action="/clientController">
                <input type="hidden" name="command" value="menuCardsPage">
                <fmt:message key="menuCards.button.menu.cards" var="MenuCards"/>
                <input type="submit" value="${MenuCards}">
            </form>
        </td>
    </tr>
</table>
<br>

<h2 align="center">History card " ${account} "</h2>
<table border="1" align="center">
    <tr>
        <th>
            <%--@declare id="date"--%><label for="date"><fmt:message key="table.title.date"/></label>
        </th>
        <th>
            <%--@declare id="operation"--%><label for="operation"><fmt:message key="table.title.operation"/></label>
        </th>
        <th>
            <%--@declare id="accountRetelling"--%><label for="accountRetelling"><fmt:message
                key="table.title.account.recipient"/></label>
        </th>
        <th>
            <%--@declare id="amountRecipient"--%><label for="amountRecipient"><fmt:message key="table.title.amountRecipient"/></label>
        </th>
    </tr>

    <c:forEach items="${histories}" var="history">
        <tr>
            <td>${history.date}</td>
            <td>${history.operations}</td>
            <td>${history.accountRetelling}</td>
            <td>${history.amountRetelling}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
