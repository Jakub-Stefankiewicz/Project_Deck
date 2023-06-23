<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 23.06.2023
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customer successfully added!</title>
</head>
<body>
Customer succsessfully added! <br>
<div>Hi, %${customer.firstName}! </div>
<a href="/login">Click here to log in</a> <br>
<a href="/">Return to homepage</a>
</body>
</html>
