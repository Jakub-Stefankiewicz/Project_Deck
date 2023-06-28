<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 28.06.2023
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer details</title>
</head>
<body>
Dane klienta: <br>
ID <c:out value="${customer.id}"/><br>
Imię: <c:out value="${customer.firstName}"/><br>
Nazwisko: <c:out value="${customer.lastName}"/><br>
Login: <c:out value="${customer.username}"/><br>
Telefon: <c:out value="${customer.phone}"/><br>
Miasto: <c:out value="${customer.city}"/>, kod pocztowy: <c:out value="${customer.postalCode}"/><br>
Ulica: <c:out value="${customer.street}"/>, numer domu/mieszkania<c:out value="${customer.houseNumber}"/><br>
<a href="/designer/home">Wróć na stronę główną</a>
</body>
</html>
