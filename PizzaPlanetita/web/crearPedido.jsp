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
            <button type="submit"> Eso es todo </button>
        </form>
    </body>
</html>
