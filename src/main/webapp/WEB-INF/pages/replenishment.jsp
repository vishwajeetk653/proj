<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 4/30/2015
  Time: 11:14 AM
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
    <title>Replenishment amount</title>
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
</table>
<br>

<h2 align="center">
    <%--@declare id="menudeposit"--%>
    <label for="menuDeposit"><fmt:message key="deposit.label.title"/>
</h2>

<table align="center" BORDER="1">
    <tr>
        <th>
            <%--@declare id="account"--%>
            <label for="account"><fmt:message key="table.title.account"/></label>
        </th>
        <th>
            <%--@declare id="amountRecipient"--%>
            <label for="amountRecipient"><fmt:message key="table.title.amountRecipient"/></label></th>
        </th>
    </tr>
    <tr>
        <td>
            ${card.account}
        </td>
        <td>
            ${card.amount}
        </td>
    </tr>
</table>
<br>

<table align="center">
    <tr>
        <th colspan="3">
            <h2 align="center">
                <%--@declare id="deposit"--%>
                <label for="deposit"><fmt:message key="menuCard.button.deposit"/></label>
            </h2>
        </th>
    </tr>
    <form method="post" action="/clientController">
        <input type="hidden" name="command" value="ReplenishmentAmounts">

        <tr>
            <td>
                <%--@declare id="enter"--%>
                <label for="enter"><fmt:message key="table.title.enter"/></label>
            </td>
            <td>
                <input type="text" name="amountSent" value="">
            </td>
            <input type="hidden" name="accountRecipient" value="${card.account}">
            <fmt:message key="menuCard.button.deposit" var="ReplenishmentAmounts"/>
            <td><input type="submit" value=${ReplenishmentAmounts}></td>
        </tr>
    </form>
</table>
</body>
</html>
