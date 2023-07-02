<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 02.07.2023
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="myTags" %>
<html>
<head>
    <title>Tree</title>
</head>
<body>
${event.eventName}
<myTags:events list="${event.events}"/>
</body>
</html>
