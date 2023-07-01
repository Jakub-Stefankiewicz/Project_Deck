<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 23.06.2023
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Deal</title>
</head>
<body>
<c:if test="${null==deal.offer}"> Uwaga: <a href="/designer/customer/add_offer/${deal.customer.id}" >przypisz klientowi rodzaj projektu! Klinkij w link</a></c:if>

<form:form method="post" modelAttribute="deal">
    <div>Umowa</div>
    Umowa sporządzona dnia <form:input path="created"/> pomiędzy:<br>
    Imię i naziwsko: <form:input path="customer.firstName"/> <form:input path="customer.lastName"/><br>
    Adres zamieszkania: <form:input path="customer.postalCode"/>, <form:input path="customer.city"/><br>
    ulica <form:input path="customer.street"/> numer <form:input path="customer.houseNumber"/><br>
    zwanym dalej Zamawiającym, a: <br>
    Imię i naziwsko: <form:input path="designer.firstName"/> <form:input path="designer.lastName"/> <br>
    Adres zamieszkania: <form:input path="designer.postalCode"/>, <form:input path="designer.city"/><br>
    ulica <form:input path="designer.street"/> numer <form:input path="designer.houseNumber"/><br>
    reprezentującym firmę: <form:input path="designer.companyName"/><br>
    zwanym dalej Wykonawcą. <br>
    Zamawiający zleca a Wykonawca przyjmuje do realizacji projekt:<br>
    ${deal.offer.projectType}<br>
    za kwotę: ${deal.offer.price} zł<br>
    Termin wykonania wynosi 3 miesiące od dnia podpinaia umowy.<br>
    Dodatkowe ustalenia: <form:input path="notes"/><br>
    <form:hidden path="customer"/>
    <form:hidden path="designer"/>
    <input type="submit" name="Prześlij do klienta">
</form:form>


</body>
</html>
