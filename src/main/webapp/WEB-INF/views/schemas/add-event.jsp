<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 24.06.2023
  Time: 17:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add schema</title>
</head>
<body>
<c:if test="${not empty eventList}">
    <table>
        <tr>
            <td>Event name</td>
            <td>Expiration date</td>
        </tr>
        <c:forEach items="${eventList}" var="event">
            <tr>
                <td>${event.eventName}</td>
                <td>${event.expiration}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<form:form method="post" modelAttribute="event">
    Nazwa eventu: <form:input path="eventName"/><br>
    Spodziewana data ukończenia eventu: <form:input type="date" path="expiration"/><br>
    Nazwa drzewa: <form:checkboxes path="tree" items="${treeList}" itemLabel="treeName"/><br>
    Czy to event ostateczny: <form:checkbox path="finalEvent"/>
    <input type="submit"/>
</form:form>
</form>
</body>
</html>
