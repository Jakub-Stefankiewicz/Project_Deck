<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 20.06.2023
  Time: 17:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Register designer</title>
    <style>
        .error {
            color: #ff0000;
            font-style: italic;
            font-weight: bold;
        }
    </style>
</head>
<body>
Zarejestruj nowego użytkownika<br>
<form:form method="post" modelAttribute="userToRegister">
    Login <form:input path="username"/><form:errors path="username" cssClass="error"/><c:if test="${not empty exists}">
    <p class="error">${exists}</p></c:if><br>
    Hasło <form:input type="password" path="password"/><form:errors path="password" cssClass="error"/><br>
    <input type="submit" value="Zarejestruj">
</form:form>
</body>
</html>
