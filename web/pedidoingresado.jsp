<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 
    Document   : pedidoingresado
    Created on : 15/05/2016, 04:33:27 PM
    Author     : Christopher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="sPedidoIngresado" method="post">
            <c:forEach var="x" items="${sessionScope.ingredientesIngresados}">
                <c:out value="${x.nombre}"/><br>
            </c:forEach><br>
            <c:forEach var="y" items="${sessionScope.productosIngresados}">
                <c:out value="${y.nombre}"/><br>
            </c:forEach>
                Direccion : <input type="text" name="direccion"/>
            <button type="submit"> Ok? </button>
        </form>
    </body>
</html>
