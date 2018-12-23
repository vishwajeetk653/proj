<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 4/21/2015
  Time: 12:00 PM
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
    <title>Menu card</title>
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
<br>
<br>
<%--<label for="password"><fmt:message key="login.label.password"/></label><br>--%>
<%--<input type="text" name="password" value=""><br>--%>
<table border="1" align="center">
    <tr>
        <th>
            <%--@declare id="account"--%>
            <label for="account"><fmt:message key="table.title.account"/></label>
        </th>
        <th>
            <%--@declare id="amountRecipient"--%>
            <label for="amountRecipient"><fmt:message key="table.title.amountRecipient"/></label>
        </th>
        <c:if test="${card.blocked == true}">
            <th>
                <%--@declare id="blockad"--%>
                <label for="blockad"><fmt:message key="table.title.blocked"/></label>
            </th>
        </c:if>
        <c:if test="${card.unlockCard == true}">
            <th>
                <%--@declare id="unload"--%>
                <label for="unload"><fmt:message key="table.title.unlock.card"/></label>
            </th>
        </c:if>
    </tr>
    <tr>
        <td>
            ${card.account}
        </td>
        <td>
            ${card.amount}
        </td>
        <c:if test="${card.blocked == true}">
            <td>
                    ${card.blocked}
            </td>
        </c:if>
        <c:if test="${card.unlockCard == true}">
            <td>
                    ${card.unlockCard}
            </td>
        </c:if>
    </tr>
</table>
<br>

<table align="center">
    <tr>
        <td>
            <form method="post" action="/clientController">
                <input type="hidden" name="command" value="history">
                <fmt:message key="menuCards.button.history" var="History"/>
                <input class="link" type="submit" name="command" value="${History}">
            </form>
        </td>
    </tr>
    <tr>
        <c:if test="${card.blocked == true}">
            <c:if test="${card.unlockCard == false}">
                <td>
                    <form method="post" action="/clientController">
                        <input type="hidden" name="command" value="unblock card">
                        <fmt:message key="menuCard.button.unlock_card" var="Unlock_card"/>
                        <input type="submit" name="command" value="${Unlock_card}">
                    </form>
                </td>
            </c:if>
        </c:if>

        <c:if test="${card.blocked == false}">
            <form method="post" action="/clientController">
                <tr>
                    <td>
                        <form method="post" action="/clientController">
                            <input type="hidden" name="command" value="block card">
                            <fmt:message key="menuCard.button.block_card" var="Block_card"/>
                            <input type="submit" name="command" value="${Block_card}">
                        </form>
                    </td>
                </tr>
                <tr>
                    <td>
                        <form method="post" action="/clientController">
                            <input type="hidden" name="command" value="paymentPage">
                            <fmt:message key="menuCard.button.payment" var="Payment"/>
                            <input type="submit" value="${Payment}" >
                        </form>
                    </td>
                </tr>

            </form>
        </c:if>
    </tr>
</table>

<c:if test="${card.blocked == true}">
    <c:if test="${card.unlockCard == false}">
        <h3 align="center">The card is blocked. You will be able to use the card when it unlocks administrator.</h3>
    </c:if>
</c:if>
<c:if test="${card.unlockCard == true}">
    <h3 align="center">Request for unblocking sent to the administrator.<br>
        You will receive a message</h3>
</c:if>
<hr/>
<jsp:expression>
    (request.getAttribute("message") != null)
    ? (String) request.getAttribute("message")
    : ""</jsp:expression>
<hr/>
</body>
</html>
