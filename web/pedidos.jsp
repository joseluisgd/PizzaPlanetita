<%@page import="ulima.edu.pe.beans.pedido.Pedido"%>
<%@page import="ulima.edu.pe.beans.pedido.ProductoPedido"%>
<%@page import="java.util.List"%>
<%@ page session="true" %>
<%-- 
    Document   : pedidos
    Created on : 15-jun-2016, 2:47:12
    Author     : Jose Luis
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de pedidos</title>
    </head>
    <body>
        <%List<Pedido> pedidos = (List<Pedido>) session.getAttribute("pedidos");%>
        <%for(Pedido pedido : pedidos) {%>
            <br>Id: <%=String.valueOf(pedido.getId())%>
            <br>Precio: <%=String.valueOf(pedido.getPrecioPedido())%>
            <br>Estado: <%=pedido.getEstado().getNombre()%>
            <br>Fecha y hora del estado: <%=pedido.getEstado().getFechaHora()%>
            <br>Detalle de productos:
            <%for(ProductoPedido producto : pedido.getProductos()) {%>
                <br>Nombre: <%=producto.getProducto().getNombre()%>
                <br>Cantidad: <%=String.valueOf(producto.getCantidad())%>
                <br>Precio unitario: <%=String.valueOf(producto.getProducto().getPrecio())%>
                <br>Precio total: <%=String.valueOf(producto.getPrecioTotal())%>
            <%}%>
            <br>
        <%}%>
        <%--ChF: Debería haber un botón para regresar a la pantalla anterior --%>
    </body>
</html>
