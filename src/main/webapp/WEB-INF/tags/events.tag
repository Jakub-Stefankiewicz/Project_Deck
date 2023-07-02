<%@ attribute name="list" type="org.hibernate.collection.spi.PersistentBag" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty list}">
    <ul>
        <c:forEach var="event" items="${list}">
            <li><c:out value="${event.eventName}"/></li>
            <myTags:events list="${event.events}"/>
        </c:forEach>
<%--        <br>--%>
<%--        <br>--%>
<%--        <table>--%>
<%--        <c:forEach var="event" items="${list}">--%>
<%--            <li><c:out value="${event.eventName}"/></li>--%>
<%--            <myTags:events list="${event.events}"/>--%>
<%--        </c:forEach>--%>
<%--        </table>--%>
    </ul>
</c:if>