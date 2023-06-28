<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 28.06.2023
  Time: 18:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>
<div>Pełnomocnictwo</div>
Pełnomocnictwo sporządzone dnia ${authorization.created} <br>
Imię i naziwsko: ${authorization.customer.firstName} ${authorization.customer.lastName} <br>
Adres zamieszkania: ${authorization.customer.postalCode} ${authorization.customer.city},
ulica ${authorization.customer.street} numer ${authorization.customer.houseNumber}<br>
Upoważnia: <br>
Imię i naziwsko: ${authorization.designer.firstName} ${authorization.designer.lastName} <br>
Adres zamieszkania: ${authorization.designer.postalCode} ${authorization.designer.city},
ulica ${authorization.designer.street} numer ${authorization.designer.houseNumber}<br>
reprezentującym firmę: ${authorization.designer.companyName}<br>
do wszystkich spraw związnych z wykonaniem projektu: <br>

</body>
</html>
