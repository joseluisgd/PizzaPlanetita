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
                <c:out value="${x.nombrePizza}"/><br>

            </c:forEach>
            <button type="submit"> Aceptar </button>
        </form>
    </body>
</html>