<%--
  Created by IntelliJ IDEA.
  User: Vladislav
  Date: 4/24/2015
  Time: 8:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New card</title>
</head>
<body>
<form method="post" action="/clientController">
    Fill in all the fields<br>
    Account<br>
    <input type="text" name="account" value=""><br>
    Ammount<br>
    <input type="text" name="amountRecipient" value=""><br>
    Block<br>
    <input type="text" name="block" value=""><br>
    <input type="submit" name="command" value="New card">
</form>
</body>
</html>
