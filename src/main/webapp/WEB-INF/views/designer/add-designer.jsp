<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 05.07.2023
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add designer</title>
  <style>
    .error {
      color: #ff0000;
      font-style: italic;
      font-weight: bold;
    }
  </style>
</head>
<body>
<form:form method="post" modelAttribute="designer">

  <div> Email <form:input path="email"/></div><form:errors path="email" cssClass="error"/><br>
  <div> First Name <form:input path="firstName"/></div></div><form:errors path="firstName" cssClass="error"/><br>
  <div> Last Name <form:input path="lastName"/></div><form:errors path="lastName" cssClass="error"/><br>
  <div> Company <form:input path="companyName"/></div><form:errors path="companyName" cssClass="error"/><br>
  <div> Phone Number <form:input path="phone"/></div><form:errors path="phone" cssClass="error"/><br>
  <div> City <form:input path="city"/></div><form:errors path="city" cssClass="error"/><br>
  <div> Street <form:input path="street"/></div><form:errors path="street" cssClass="error"/><br>
  <div> House Number / Appartment Number <form:input path="houseNumber"/></div><form:errors path="houseNumber" cssClass="error"/><br>
  <div> Postal Code <form:input path="postalCode"/></div><form:errors path="postalCode" cssClass="error"/><br>
  <div><form:hidden path="user"/></div>

  <input type="submit" value="Add Designer">

</form:form>
</body>
</html>
