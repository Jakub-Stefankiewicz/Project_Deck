<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 23.06.2023
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Designer Home</title>
</head>
<body>
Menu:<br>
<a href="/designer/offers/list">Rodzaje oferowanych projektów</a><br>
<a href="/schema/list">Schematy projektów</a><br>
<a href="/designer/customers">Aktywni klienci i projekty</a><br>
<%--<a href="/designer/archives">Archiwum klientów i projektów</a><br>--%>
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
