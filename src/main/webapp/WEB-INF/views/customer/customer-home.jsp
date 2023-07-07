<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 23.06.2023
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer home</title>
</head>
<body>
<a href="/customer/deal"> Podgląd umowy</a><br>
<a href="/customer/authorization">Pełnomocnictwo</a><br>
<a href="/customer/tree">Drzewo Twojego projektu</a><br>
<%--Kontakt z projektantem<br>--%>
<%--Edytuj swoje dane<br>--%>
<form action="<c:url value="/logout"/>" method="post">
    <input class="fa fa-id-badge" type="submit" value="Wyloguj">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
