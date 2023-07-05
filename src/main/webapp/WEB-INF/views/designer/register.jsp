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
</head>
<body>
Zarejestruj nowego użytkownika<br>
<form:form method="post" modelAttribute="user">
    Login <form:input path="login"/> <br>
    Hasło <form:input path="password"/><br>
    <input type="submit" value="Zarejestruj">
</form:form><br>
</body>
</html>
