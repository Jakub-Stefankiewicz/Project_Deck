<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 23.06.2023
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Register customer</title>
</head>
<body>
<form:form method="post" modelAttribute="emptyCustomer">


    <div> Login <form:input path="username"/></div>
    <div> Password <form:input path="password"/></div>
    <div> Email <form:input path="email"/></div>
    <div> First Name <form:input path="firstName"/></div>
    <div> Last Name <form:input path="lastName"/></div>
    <div> Phone Number <form:input path="phone"/></div>
    <div> City <form:input path="city"/></div>
    <div> Street <form:input path="street"/></div>
    <div> House Number / Appartment Number <form:input path="houseNumber"/></div>
    <div> Postal Code <form:input path="postalCode"/></div>

    <input type="submit" value="Add Customer">

</form:form>
</body>
</html>
