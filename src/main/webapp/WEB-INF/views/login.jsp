<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 21.06.2023
  Time: 18:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="login" method="post">
    <div><label> User Name : <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <sec:csrfInput/>
    <div><input type="submit" value="Sign In"/></div>
</form>
</body>
</html>
