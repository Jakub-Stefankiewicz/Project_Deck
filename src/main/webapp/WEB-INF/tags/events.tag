<%@ attribute name="list" type="org.hibernate.collection.spi.PersistentBag" required="true" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${!empty list}">
    <%--    <ul>--%>
    <table style="text-align: center; border:1px solid black">
        <tr>
            <c:forEach var="event" items="${list}">
                <td>
                    <c:out value="${event.eventName}"/>
                    <myTags:events list="${event.events}"/>
                </td>
            </c:forEach>
        </tr>
    </table>
</c:if>