<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 01.07.2023
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit tree</title>
</head>
<body>
<form:form method="post" modelAttribute="treeToEdit">
    <form:input path="treeName"/><br>
    <form:hidden path="events"/><br>
    <form:hidden path="id"/><br>
    <input type="submit" value="Zatwierdź">
</form:form>
</body>
</html>
