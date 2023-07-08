<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 28.06.2023
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Edit offer</title>
</head>
<body>
<form:form method="post" modelAttribute="offerToEdit">
  Typ projektu: <form:input path="projectType"/>
  Cena projektu: <form:input path="price"/>
  <form:hidden path="id" value="${offerToEdit.id}"/>
  <form:hidden path="designer" value="${offerToEdit.designer.id}"/>
<%--  <form:hidden path="customer"/>--%>
  <form:hidden path="template"/>
  <form:hidden path="events"/>
  <input type="submit" value="Edit offer"/>
</form:form>
</body>
</html>
