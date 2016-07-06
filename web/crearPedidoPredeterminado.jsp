<%--@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>
<%@page import="ulima.edu.pe.beans.producto.promocion.PizzaPromocion"%>
<%@page import="ulima.edu.pe.beans.producto.promocion.AdicionalPromocion"%>
<%@page import="ulima.edu.pe.beans.producto.promocion.Promocion"%>
<%@page import="ulima.edu.pe.beans.producto.Adicional"%>
<%@page import="ulima.edu.pe.beans.producto.pizza.Tamano"%>
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
            <%--ChF: Se podría desmembrar cada tipo de producto en su propio .jsp para así hacer la carga de la página más ligera (y ordenada) --%>
            <%--ChF: Se genera correctamente el html pero no he probado si se pueden gettear bien los id de los input o esas cosas para obtener los valores ingresados --%>
            <%--ChF: Se debe añadir un botón que diga "Añadir PIZZA personalizada", y que mande una nueva ventanita (o como sea) con inputs para Ingredientes y Tamaño, y que al dar aceptar se añada a la lista de pizzas del pedido--%>
            <h3>***PIZZAS***</h3>
            <%List<PizzaCarta> pizzas = (List<PizzaCarta>) session.getAttribute("pizzasCarta");%>
            <%for(PizzaCarta pizza : pizzas) {%>
                <input type="checkbox" value="<%=pizza.getId()%>" name="pizzasId">
                <br>Nombre: <%=pizza.getNombre()%>
                <br>Ingredientes:
                <%for(Ingrediente ingrediente : pizza.getIngredientes()) {%>
                    <br>- <%=ingrediente.getNombre()%>
                <%}%>
                <br>Tamaños:
                <%for(Tamano tamano : pizza.getTamanos()) {%>
                    <br><input type="radio" name="pizza<%=pizza.getId()%>tamano" value="pizza<%=pizza.getId()%>tamano<%=tamano.getId()%>"> <%=tamano.getNombre()%> (S/ <%=tamano.getPrecio()%>)<br> <%--ChF: Probar --%>
                <%}%>
                <br>Cantidad: <input type="number" name="pizza<%=pizza.getId()%>cantidad" min="1">
            <%}%>
            <br>    
            <h3>***ADICIONALES***</h3>
            <%List<Adicional> adicionales = (List<Adicional>) session.getAttribute("adicionalesCarta");%>
            <%for(Adicional adicional : adicionales) {%>
                <input type="checkbox" value="<%=adicional.getId()%>" name="adicionalesId">
                <br>Nombre: <%=adicional.getNombre()%>
                <br>Precio: <%=adicional.getPrecio()%>
                <br>Cantidad: <input type="number" name="adicional<%=adicional.getId()%>cantidad" min="1">
                <br>
            <%}%>       
            <br>
            <h3>***PROMOCIONES***</h3>
            <%List<Promocion> promomciones = (List<Promocion>) session.getAttribute("promocionesCarta");%>
            <%for(Promocion promocion : promomciones) {%>
                <input type="checkbox" value="<%=promocion.getId()%>" name="promocionesId">
                <br>Nombre: <%=promocion.getNombre()%>
                <br>Descripcion: <%=promocion.getDescripcion()%>
                <br>Productos:
                <%for(PizzaPromocion pizza : promocion.getPizzas()) {%>
                    <br>- Nombre: <%=pizza.getNombre()%>
                    <br>- Tamaño: <%=pizza.getTamanoNombre()%>
                    <br>- Cantidad: <%=pizza.getCantidad()%>
                <%}%>
                <br>
                <%for(AdicionalPromocion adicional : promocion.getAdicionales()) {%>
                    <br>- Nombre:<%=adicional.getNombre()%>
                    <br>- Cantidad:<%=adicional.getCantidad()%>
                <%}%>
                <br>Cantidad: <input type="number" name="pizza<%=promocion.getId()%>cantidad" min="1">
                <br>
            <%}%>
            <%--
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
            --%>
            <button type="submit"> Aceptar </button>
        </form>
    </body>
</html>