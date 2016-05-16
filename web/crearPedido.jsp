<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear pedido</title>
    </head>
    <body>
        <h1>Pedidos</h1>
        <form action="sPedido" method="post">
            <c:forEach var="x" items="${sessionScope.ingredientes}">
                <input type="checkbox" value="<c:out value="${x.id}"/>" name="ingredientes">
                <c:out value="${x.nombre}"/><br>
            </c:forEach>
                <br>
            <c:forEach var="y" items="${sessionScope.productos}">
                <input type="checkbox" value="<c:out value="${y.id}"/>" name="productos">
                <c:out value="${y.nombre}"/>
                <c:out value="${y.precio}"/><br>
            </c:forEach>
                
            <button type="submit"> Aceptar </button>
        </form>
    </body>
</html>
