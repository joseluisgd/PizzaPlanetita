<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="i" value="${0}"/>
        <h1>Pedido realizado correctamente</h1>

        -------------------------------------------------<br>
        <c:forEach var="x" items="${sessionScope.pizza}">
            <c:out value="${x.nombre}"/><br>
            <c:out value="${sessionScope.tamanoIngresado[i]}"/><br>
            <c:set var="i" value="${i+1}"/><br>
        </c:forEach><br>
        -------------------------------------------------<br>
        <c:out value="${sessionScope.precio}" /><br>
        -------------------------------------------------<br>
        <c:out value="${sessionScope.estado.estado}" /><br>
        -------------------------------------------------<br>
        <c:out value="${sessionScope.estado.fechaHora}" /><br>
        -------------------------------------------------<br>
        <c:out value="${sessionScope.direccion}" /><br>


        <h2> Gracias por la compra</h2>


    </body>
</html>
