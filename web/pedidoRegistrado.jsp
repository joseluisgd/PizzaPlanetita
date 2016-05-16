<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Pedido realizado correctamente</h1>
        -------------------------------------------------
        <br>
        <c:forEach var="y" items="${sessionScope.productosIngresados}">
            <c:out value="${y.nombre}"/><br>
        </c:forEach><br>
        -------------------------------------------------
        <c:forEach var="x" items="${sessionScope.ingredientesIngresados}">
            <c:out value="${x.nombre}"/><br>
        </c:forEach><br>
        <c:out value="${sessionScope.precio}" /><br>
        -------------------------------------------------
        <c:out value="${sessionScope.estado.estado}" /><br>
        -------------------------------------------------
        <c:out value="${sessionScope.estado.hora}" /><br>
        -------------------------------------------------
        <c:out value="${sessionScope.direccion}" /><br>
        
        
        <h2> Gracias por la compra</h2>
        
            
    </body>
</html>
