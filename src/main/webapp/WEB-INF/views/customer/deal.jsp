<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 24.06.2023
  Time: 09:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Deal</title>
</head>
<body>
<div>Umowa</div>
Umowa sporządzona dnia ${deal.created} pomiędzy:<br>
Imię i naziwsko: ${deal.customer.firstName} ${deal.customer.lastName} <br>
Adres zamieszkania: ${deal.customer.postalCode} ${deal.customer.city},
ulica ${deal.customer.street} numer ${deal.customer.houseNumber}<br>
zwanym dalej Zamawiającym, a: <br>
Imię i naziwsko: ${deal.customer.firstName} ${deal.customer.lastName} <br>
Adres zamieszkania: ${deal.customer.postalCode} ${deal.customer.city},
ulica ${deal.customer.street} numer ${deal.customer.houseNumber}<br>
reprezentującym firmę: ${deal.designer.companyName}<br>
zwanym dalej Wykonawcą. <br>
Zamawiający zleca a Wykonawca przyjmuje do realizacji projekt XXXXXX za kwotę ${deal.value}<br>
Termin wykonania wynosi 3 miesiące od dnia podpinaia umowy.<br>
Dodatkowe ustalenia: ${deal.notes}<br>
<form:form method="post" modelAttribute="deal">
    <form:hidden path="id"/>
    <form:hidden path="customer"/>
    <form:hidden path="designer"/>
    <form:hidden path="accepted"/>
    <form:hidden path="notes"/>
    <form:hidden path="created"/>
    <form:hidden path="value"/>
    <input type="submit" name="Zaakceptuj">
</form:form>
</body>
</html>
