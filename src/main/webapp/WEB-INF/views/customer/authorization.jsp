<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 24.06.2023
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>

<div>Pełnomocnictwo</div>
Pełnomocnictwo sporządzone dnia ${authorization.created} pomiędzy:<br>
Imię i naziwsko: ${authorization.customer.firstName} ${authorization.customer.lastName} <br>
Adres zamieszkania: ${authorization.customer.postalCode} ${authorization.customer.city},
ulica ${authorization.customer.street} numer ${authorization.customer.houseNumber}<br>
upoważnia <br>
Imię i naziwsko: ${authorization.customer.firstName} ${authorization.customer.lastName} <br>
Adres zamieszkania: ${authorization.customer.postalCode} ${authorization.customer.city},
ulica ${authorization.customer.street} numer ${authorization.customer.houseNumber}<br>
reprezentującego firmę: ${authorization.designer.companyName}<br>
do wszystkich czynności związanych z projektem XXX <br>
<form:form method="post" modelAttribute="authorization">
    <form:hidden path="id"/>
    <form:hidden path="customer"/>
    <form:hidden path="designer"/>
    <form:hidden path="accepted"/>
    <input type="submit" name="Zaakceptuj">
</form:form>

</body>
</html>
