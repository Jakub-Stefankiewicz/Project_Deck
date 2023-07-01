<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 01.07.2023
  Time: 13:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Archiwum</title>
</head>
<body>
Archiwum klientów i projektów
<table>
  <tr>
    <td>Imię</td>
    <td>Nazwisko</td>
    <td>Akcje</td>
  </tr>
  <c:forEach items="${designerCustomers}" var="customer">
    <c:if test="${false==customer.active}">
      <tr>
        <td>${customer.firstName}</td>
        <td>${customer.lastName}</td>
        <td><a href="/designer/customer_details/${customer.id}">Szczegóły klienta</a>
      </tr>
    </c:if>
  </c:forEach><br>
  <a href="/designer/home">Wróć na stronę główną</a>
</table>
</body>
</html>
