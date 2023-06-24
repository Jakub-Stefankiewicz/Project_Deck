<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 24.06.2023
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create authorization</title>
</head>
<body>
<form:form method="post" modelAttribute="authorization">
  <div>Pełnomocnictwo</div>
  Pełnomocnictwo sporządzone dnia <form:input path="created"/> pomiędzy:<br>
  Imię i naziwsko: ${authorization.customer.firstName} ${authorization.customer.lastName} <br>
  Adres zamieszkania: ${authorization.customer.postalCode} ${authorization.customer.city},
  ulica ${authorization.customer.street} numer ${authorization.customer.houseNumber}<br>
  upoważnia <br>
  Imię i naziwsko: ${authorization.customer.firstName} ${authorization.customer.lastName} <br>
  Adres zamieszkania: ${authorization.customer.postalCode} ${authorization.customer.city},
  ulica ${authorization.customer.street} numer ${authorization.customer.houseNumber}<br>
  reprezentującego firmę: ${authorization.designer.companyName}<br>
  do wszystkich czynności związanych z projektem XXX <br>
  <form:hidden path="customer"/>
  <form:hidden path="designer"/>
  <input type="submit" name="Prześlij do klienta">
</form:form>
</body>
</html>
