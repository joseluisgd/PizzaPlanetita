<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- 
    Document   : pedidoPredeterminadoIngresado
    Created on : 01-jun-2016, 23:26:24
    Author     : Jose Luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form>
            <c:forEach var="x" items="${sessionScope.pizzasOrdenadas}">
                <c:out value="${x.nombrePizza}"/><br>
                <c:out value="${sessionScope.tamanoEscogico.NombreTamano}"/><br>
                <c:out value="${sessionScope.tamanoEscogico.Precio}"/><br>
            </c:forEach><br>

        </form>

    </body>
</html>
