<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 26.06.2023
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add tree</title>
</head>
<body>
<form:form method="post" modelAttribute="tree">
    Tree name:<form:input path="treeName"/><br>
    <input type="submit"/>
</form:form>
</body>
</html>
