<%--@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>
<%@page import="ulima.edu.pe.beans.producto.pizza.Ingrediente"%>
<%@page import="java.util.List"%>
<%@page import="ulima.edu.pe.beans.producto.pizza.PizzaCarta"%>
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
            <%List<PizzaCarta> pizzas = (List<PizzaCarta>) session.getAttribute("pizzasCarta");%>
            <%for(PizzaCarta pizza : pizzas) {%>
                <input type="checkbox" value="<%=pizza.getId()%>" name="pizzaId"> <%--ChF: Probar --%>
                <br>Nombre: <%=pizza.getNombre()%>
                <br>Ingredientes:
                <%for(Ingrediente ingrediente : pizza.getIngredientes()) {%>
                    <br>    - <%=ingrediente.getNombre()%>
                <%}%>
                <br>PizzaCarta.
                <br>Precio: <%=%>
                <br>Estado: <%=%>
                <br>Fecha y hora del estado: <%=%>
                

            <%}%>
            
            
            <c:forEach var="x" items="${sessionScope.pizzasCarta}">
                -----------------PIZZA GENERAL-----------------<br>
                <input type="checkbox" value="<c:out value="${x.id}/>" name="pizzaId">
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
            </c:forEach>
            <button type="submit"> Aceptar </button>
        </form>
    </body>
</html>