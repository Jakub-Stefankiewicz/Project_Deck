<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 07.07.2023
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Show deal</title>
</head>
<body>
Umowa sporządzona dnia ${deal.created} <br>
Imię i naziwsko: ${deal.customer.firstName} ${deal.customer.lastName}<br>
Adres zamieszkania: ${deal.customer.postalCode} ${deal.customer.city}<br>
ulica ${deal.customer.street} numer ${deal.customer.houseNumber}<br>
zwanym dalej Zamawiającym, a: <br>
Imię i naziwsko: ${deal.designer.firstName} ${deal.designer.lastName}<br>
Adres zamieszkania: ${deal.designer.postalCode} ${deal.designer.city}<br>
ulica ${deal.designer.street} numer ${deal.designer.houseNumber}<br>
reprezentującym firmę: ${deal.designer.companyName}<br>
zwanym dalej Wykonawcą. <br>
Zamawiający zleca a Wykonawca przyjmuje do realizacji projekt:<br>
${deal.offer.projectType}<br>
za kwotę ${deal.offer.price} zł<br>
Termin wykonania wynosi 3 miesiące od dnia podpinaia umowy.<br>
Dodatkowe ustalenia: ${deal.notes}<br>
<c:if test="${true==deal.accepted}"><p style="color: blue">Umowa zaakceptowana</p> </c:if>
<c:if test="${false==deal.accepted}"><p style="color: red">Umowa niezaakceptowana</p> </c:if><br>
<a href="/designer/customers">Wróć do listy klientów</a>
</body>
</html>
