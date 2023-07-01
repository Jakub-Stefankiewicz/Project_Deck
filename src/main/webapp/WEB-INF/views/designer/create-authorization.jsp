<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 30.06.2023
  Time: 20:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create authorization</title>
</head>
<body>
<c:if test="${null==authorization.offer}"> Uwaga: <a href="/designer/customer/add_offer/${authorization.customer.id}">przypisz
    klientowi rodzaj projektu! Klinkij w link</a></c:if>
<form:form method="post" modelAttribute="authorization">
    Pełnomocnictwo sporządzone dnia <form:input path="created"/> <br>
    Imię i naziwsko: <form:input path="customer.firstName"/> <form:input path="customer.lastName"/><br>
    Adres zamieszkania: <form:input path="customer.postalCode"/>, <form:input path="customer.city"/><br>
    ulica <form:input path="customer.street"/> numer <form:input path="customer.houseNumber"/><br>
    Upoważnia: <br>
    Imię i naziwsko: <form:input path="designer.firstName"/> <form:input path="designer.lastName"/> <br>
    Adres zamieszkania: <form:input path="designer.postalCode"/>, <form:input path="designer.city"/><br>
    ulica <form:input path="designer.street"/> numer <form:input path="designer.houseNumber"/><br>
    reprezentującym firmę: <form:input path="designer.companyName"/><br>
    do wszystkich spraw związnych z wykonaniem projektu: <br>
    <form:input path="offer.projectType"/> <br>
    za kwotę <form:input path="offer.price"/> zł<br>
    <form:hidden path="customer"/>
    <form:hidden path="designer"/>
    <form:hidden path="offer.id"/>
    <input type="submit" value="Prześlij do klienta">
</form:form>

<br>
</body>
</html>
