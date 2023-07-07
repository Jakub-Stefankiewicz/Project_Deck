<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 04.07.2023
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show tree</title>
</head>
<body>
<c:if test="${empty event}">Drzewo Twojego projektu jest jeszcze puste</c:if>
${event.eventName}
<myTags:events list="${event.events}"/>
<a href="/customer/home">Wróć na stronę główną</a>
</body>
</html>
