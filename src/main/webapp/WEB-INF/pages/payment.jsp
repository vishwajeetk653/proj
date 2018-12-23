<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 4/30/2015
  Time: 11:12 AM
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
    <title>Menu payment</title>
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
                <input type="submit" name="command" value="${Exit}">
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
<br>
<br>

<h2 align="center">
    <%--@declare id="menupayment"--%>
    <label for="menuPayment"><fmt:message key="payment.label.title"/></label>
</h2>
<table border="1" align="center">
    <tr>
        <th>
            <%--@declare id="account"--%>
            <label for="account"><fmt:message key="table.title.account"/></label>
        </th>
        <th>
            <%--@declare id="amountRecipient"--%>
            <label for="amountRecipient"><fmt:message key="table.title.amountRecipient"/></label></th>
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
        <th>
            <p><label for="account"><fmt:message key="table.title.account"/></label></p>
        </th>
        <th>
            <p><label for="amountRecipient"><fmt:message key="table.title.amountRecipient"/></label></p>
        </th>
    </tr>
    <tr>
        <form method="post" action="/clientController">
            <input type="hidden" name="command" value="payment">
            <td><input type="text" name="accountRecipient"></td>
            <td><input type="text" name="amountSent"></td>
            <fmt:message key="menuCard.button.payment" var="Payment"/>
            <td><input type="submit" value="${Payment}"></td>
        </form>
    </tr>
</table>

</body>
</html>
