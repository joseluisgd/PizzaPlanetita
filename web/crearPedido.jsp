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
        <form>
            <c:forEach var="x" items="${sessionScope.carta}">
                -----------------PIZZA GENERAL-----------------<br>
                <c:out value="${x.id}"/><br>
                <c:out value="${x.nombrePizza}"/><br>
                -----------------PIZZA INGREDIENTES------------<br>
                <c:forEach var="a" items="${x.ing}">
                    <c:out value="${a.id}"/><br>
                    <c:out value="${a.nombre}"/><br>
                </c:forEach>
                    -------------PIZZA PRECIO POR TAMANOS------<br>
                <c:forEach var="b" items="${x.tam}">
                    Id: <c:out value="${b.id}"/><br>
                    Tamano: <c:out value="${b.NombreTamano}"/><br>
                    Precio: <c:out value="${b.Precio}"/><br>
                    Slices: <c:out value="${b.Slices}"/><br>
                </c:forEach>

            </c:forEach>

        </form>
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
            <c:forEach var="z" items="${sessionScope.tamanos}">
                <input type="radio" value="<c:out value="${z.id}"/>" name="idTamano">
                <c:out value="${z.nombre}"/><br>
                <c:out value="${z.slices}"/><br> Slices
            </c:forEach>
            <button type="submit"> Aceptar </button>
        </form>
    </body>
</html>
