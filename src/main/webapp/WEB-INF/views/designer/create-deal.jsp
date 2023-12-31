<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 23.06.2023
  Time: 20:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create Deal</title>
</head>
<body>

<form:form method="post" modelAttribute="deal">
    <div>Umowa</div>
    Umowa sporządzona dnia <form:input path="created"/> pomiędzy:<br>
    Imię i naziwsko: ${deal.customer.firstName} ${deal.customer.lastName} <br>
    Adres zamieszkania: ${deal.customer.postalCode} ${deal.customer.city},
        ulica ${deal.customer.street} numer ${deal.customer.houseNumber}<br>
    zwanym dalej Zamawiającym, a: <br>
    Imię i naziwsko: ${deal.customer.firstName} ${deal.customer.lastName} <br>
    Adres zamieszkania: ${deal.customer.postalCode} ${deal.customer.city},
    ulica ${deal.customer.street} numer ${deal.customer.houseNumber}<br>
    reprezentującym firmę: ${deal.designer.companyName}<br>
    zwanym dalej Wykonawcą. <br>
    Zamawiający zleca a Wykonawca przyjmuje do realizacji projekt XXXXXX za kwotę <form:input path="value"/><br>
    Termin wykonania wynosi 3 miesiące od dnia podpinaia umowy.<br>
    Dodatkowe ustalenia: <form:input path="notes"/><br>
    <form:hidden path="customer"/>
    <form:hidden path="designer"/>
    <input type="submit" name="Prześlij do klienta">
</form:form>

</body>
</html>
