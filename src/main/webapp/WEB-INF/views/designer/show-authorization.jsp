<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 07.07.2023
  Time: 18:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show authorization</title>
</head>
<body>
Pełnomocnictwo sporządzone dnia ${authorization.created} <br>
Imię i naziwsko: ${authorization.customer.firstName} ${authorization.customer.lastName}<br>
Adres zamieszkania: ${authorization.customer.postalCode} ${authorization.customer.city}<br>
ulica ${authorization.customer.street} numer ${authorization.customer.houseNumber}<br>
Upoważnia: <br>
Imię i naziwsko: ${authorization.designer.firstName} ${authorization.designer.lastName}<br>
Adres zamieszkania: ${authorization.designer.postalCode} ${authorization.designer.city}<br>
ulica ${authorization.designer.street} numer ${authorization.designer.houseNumber}<br>
reprezentującym firmę: ${authorization.designer.companyName}<br>
do wszystkich spraw związnych z wykonaniem projektu: <br>
${authorization.offer.projectType} <br>
<c:if test="${true==authorization.accepted}"><p style="color: blue">Pełnomocnictwo zaakceptowane</p> </c:if>
<c:if test="${false==authorization.accepted}"><p style="color: red">Pełnomocnictwo niezaakceptowane</p> </c:if><br>
<a href="/designer/customers">Wróć do listy klientów</a>
</body>
</html>
