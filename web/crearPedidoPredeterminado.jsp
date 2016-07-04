<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear pedido</title>
    </head>
    <body>
        <h1>Selecciona tu pedido</h1>
        <form action="sPedidoPredeterminado" method="post">
            <c:forEach var="x" items="${sessionScope.pizzas}">
                -----------------PIZZA GENERAL-----------------<br>
                <input type="checkbox" value="<c:out value="${x.id}"/>" name="pizzaId">
<<<<<<< HEAD
                <c:out value="${x.nombrePizza}"/><br>

=======
                <c:out value="${x.nombre}"/><br>
                -----------------PIZZA INGREDIENTES------------<br>
                <c:forEach var="a" items="${x.ing}">
                    <c:out value="${a.id}"/><br>
                    <c:out value="${a.nombre}"/><br>
                </c:forEach>
                -------------PIZZA PRECIO POR TAMANOS------<br>
                <c:forEach var="b" items="${x.tam}">
                    <input type="radio" value="<c:out value="${b.id}"/>" name="tamanoId<c:out value="${x.id}"/>">
                    Tamano: <c:out value="${b.nombre}"/><br>
                    Precio: <c:out value="${b.precio}"/><br>
                    Slices: <c:out value="${b.slices}"/><br>
                </c:forEach>
>>>>>>> d90f3d7129fba1037c9d69134d6762c42d016b9a
            </c:forEach>
            <button type="submit"> Aceptar </button>
        </form>
    </body>
</html>