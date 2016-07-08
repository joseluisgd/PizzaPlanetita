<%--@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>
<%@page import="ulima.edu.pe.beans.producto.pizza.PizzaPedido"%>
<%@page import="ulima.edu.pe.beans.pedido.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="ulima.edu.pe.beans.pedido.ProductoPedido"%>
<%-- 
    Document   : pedidoPredeterminadoIngresado
    Created on : 01-jun-2016, 23:26:24
    Author     : Jose Luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmación de Pedido y dirección de entrega</title>
    </head>
    <body>
        <form action="sPedidoPredeterminadoIngresado" method="post">
            <h2> Detalle del pedido </h2>
            <h3> Productos </h3>
            <%Pedido pedido = (Pedido) session.getAttribute("pedido");%>
            <%for (ProductoPedido productoPedido : pedido.getProductos()) {%>
                <br>- Nombre: <%=productoPedido.getProducto().getNombre()%>
                <% if (productoPedido.getProducto().getClass().getName().equals(PizzaPedido.class.getName())) {%>
                    <br>- 
                <%}%> 
                <br>- Precio unitario: S/ <%= productoPedido.getPrecioUnitario()%>
                <br>- Cantidad: <%=productoPedido.getCantidad()%>
                <br>- Precio total: S/ <%=productoPedido.getPrecioTotal()%>
            <%}%>
            <br>Precio del pedido: S/ <%=pedido.getPrecioPedido()%>
            Dirección : 
            - Calle: <input type="text" name="calleDireccionPedido"/>
            - Distrito: 
            <select name="distritosDireccionPedido">
                <option value="San Borja">San Borja</option>
                <option value="San Isidro">San Isidro</option>
                <option value="Miraflores">Miraflores</option>
                <option value="Surco">Surco</option>
                <option value="La Molina">La Molina</option>
            </select>
            
            <input type="text" name="distritoDireccionPedido"/>
            <button type="submit"> Confirmar pedido </button>
        </form>
    </body>
</html>
