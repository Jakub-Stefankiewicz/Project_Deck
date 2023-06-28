<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 28.06.2023
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Customers</title>
</head>
<body>
Lista aktywnych klientów
<table>
    <tr>
        <td>Imię</td>
        <td>Nazwisko</td>
        <td>Akcje</td>
    </tr>
    <c:forEach items="${designerCustomers}" var="customer">
        <c:if test="${true==customer.active}">
            <tr>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td><a href="/designer/customer_details/${customer.id}">Szczegóły klienta</a>
                    <a href="/designer/customer/add_project/${customer.id}">Przypisz klientowi rodzaj projektu</a>
                    <a href="/designer/createdeal/${customer.id}">Sporządź umowę</a>
                    <a href="/designer/create_authorization/${customer.id}">Sporządź pełnomocnictwo</a>
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
