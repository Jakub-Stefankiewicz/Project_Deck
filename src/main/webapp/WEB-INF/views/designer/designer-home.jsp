<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 23.06.2023
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Designer Home</title>
</head>
<body>
<c:forEach var="customer" items="${designerCustomers}">
${customer.id}<br>
</c:forEach>
designer homepage
</body>
</html>
