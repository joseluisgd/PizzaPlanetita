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
        <c:set var="i" value="${0}"/>
        <form action="sPedidoPredeterminadoIngresado" method="post">
            <c:forEach var="x" items="${sessionScope.pizzasOrdenadas}">
                <c:out value="${x.nombre}"/><br>
                <c:out value="${sessionScope.tamanoEscogico[i].nombre}"/><br>
                <c:out value="${sessionScope.tamanoEscogico[i].precio}"/><br>
                <c:out value="${sessionScope.tamanoEscogico[i].slices}"/><br>
                <c:set var="i" value="${i+1}"/>
                <br>
            </c:forEach>
            Direccion : <input type="text" name="direccion"/>
            <button type="submit"> Ok? </button>
        </form>

    </body>
</html>
