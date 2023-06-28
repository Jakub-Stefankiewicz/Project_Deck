<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 28.06.2023
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Add offer</title>
</head>
<body>

<c:if test="${not empty offersList}">
    <table>
        <tr>
            <td>Nazwa oferty</td>
            <td>Kwota oferty</td>
            <td>Akcja</td>
        </tr>
        <c:forEach var="project" items="${offersList}">
            <tr>
                <td>${project.projectType}</td>
                <td>${project.price}</td>
                <td><a href="/designer/offer/delete/${project.id}">Usuń</a>
                    <a href="/designer/offer/edit/${project.id}">Edytuj</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>

Dopisz typ projektów
<form:form method="post" modelAttribute="emptyOffer">
    Typ projektu <form:input path="projectType"/><br>
    Cena projektu <form:input path="price"/><br>
    <input type="submit" value="Add Offer">
</form:form>
<a href="/designer/home">Wróć na stronę główną</a>
</body>
</html>
