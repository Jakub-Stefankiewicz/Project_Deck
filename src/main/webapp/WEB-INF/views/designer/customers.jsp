<%--
  Created by IntelliJ IDEA.
  User: pannorris
  Date: 28.06.2023
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>Customers</title>
</head>
<body>
Lista aktywnych klientów
<table>
    <tr>
        <td>Imię</td>
        <td>Nazwisko</td>
        <td>Akcje</td>
    </tr>
    <c:forEach items="${designerCustomers}" var="customer">
        <c:if test="${true==customer.active}">
            <tr>
                <td>${customer.firstName}</td>
                <td>${customer.lastName}</td>
                <td><a href="/designer/customer_details/${customer.id}">Szczegóły klienta</a>
                    <c:if test="${null==customer.offer}"> <a href="/designer/customer/add_offer/${customer.id}">Przypisz
                        klientowi rodzaj projektu</a> </c:if>
                    <c:if test="${null!=customer.offer}"> Przypisany projekt: ${customer.offer.projectType} </c:if>
                    <c:if test="${null==customer.deal}"><a href="/designer/createdeal/${customer.id}">Sporządź
                        umowę</a></c:if>
                    <c:if test="${null!=customer.deal}">Podgląd umowy</c:if>
                    <c:if test="${null==customer.authorization}"><a
                            href="/designer/create_authorization/${customer.id}">Sporządź pełnomocnictwo</a></c:if>
                    <c:if test="${null!=customer.authorization}">Podgląd pełnomocnictwa</c:if>


            </tr>
        </c:if>
    </c:forEach>
</table>
<a href="/designer/home">Wróć na stronę główną</a><br>

<%--<form action="/designer/customers" method="post">--%>
<%--    <label for="email">Email:</label>--%>
<%--    Wpisz email klienta do wysłania zaproszenia<input type="text" id="email" name="email"/><br>--%>
<%--    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--    <input type="submit" value="Wyślij"/>--%>
<%--</form>--%>

</body>
</html>
