<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 4/27/2015
  Time: 10:42 PM
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
    <title>Admin page</title>
</head>
<body>
<table align="right">
    <tr>
        <td> <%--@declare id="login"--%>
            <label for="login"><fmt:message key="login.label.login"/></label> : ${client.login}</td>
        <td>
            <form method="post" action="/clientController">
                <input type="hidden" name="command" value="exit">
                <fmt:message key="menuCards.button.exit" var="Exit"/>
                <input on class="link" type="submit" name="command" value="${Exit}">
            </form>
        </td>
    </tr>
</table>
<br>

<table border="1">
    <tr>
        <th>
            <%--@declare id="account"--%>
            <label for="account"><fmt:message key="table.title.account"/></label>
        </th>
        <th>
            <%--@declare id="amountRecipient"--%>
            <label for="account"><fmt:message key="table.title.amountRecipient"/></label>
        </th>
        <th>
            <%--@declare id="unloadcard"--%><label for="unloadCard"><fmt:message key="table.title.unlock.card"/></label>
        </th>
    </tr>
    <c:forEach items="${cards}" var="card">
        <form method="post" action="/adminController">
            <tr>
                <td><input tupe="text" name="account" value="${card.account}"></td>
                <td><input tupe="text" name="amount" value="${card.amount}"></td>
                <td><input tupe="text" name="unlockCard" value="${card.unlockCard}"></td>
                <td><input type="submit" name="command" value="Unlock card"></td>
                <td>
                    <input type="hidden" name="command" value="ReplenishmentPage">
                    <fmt:message key="menuCard.button.deposit" var="ReplenishmentPage"/>
                    <input type="submit" name="command" value="${ReplenishmentPage}" size="25">
                </td>
            </tr>
        </form>
    </c:forEach>
</table>


</body>
</html>
