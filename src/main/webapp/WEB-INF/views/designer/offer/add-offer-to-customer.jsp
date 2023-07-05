<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 28.06.2023
  Time: 22:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add offer to customer</title>
</head>
<body>
Klient: <c:out value="${customer.firstName}"/> <c:out value="${customer.lastName}"/> <br>
<form:form modelAttribute="customer" method="post">
    <form:select path="offer" items="${projects}" itemLabel="projectType" itemValue="id"/>
    <form:hidden path="id" value="${customer.id}"/>
    <form:hidden path="designer"/>
    <form:hidden path="active"/>
    <form:hidden path="houseNumber"/>
    <form:hidden path="street"/>
    <form:hidden path="city"/>
    <form:hidden path="postalCode"/>
    <form:hidden path="firstName"/>
    <form:hidden path="lastName"/>
    <form:hidden path="email"/>
    <form:hidden path="added"/>
    <form:hidden path="authorization"/>
    <form:hidden path="deal"/>
    <form:hidden path="phone"/>
    <input type="submit" value="Dodaj"/>
</form:form>
</body>
</html>
