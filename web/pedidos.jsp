<%@page import="ulima.edu.pe.beans.pedido.Pedido"%>
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
        <%List<Pedido> pedidos = (List<Pedido>) session.getAttribute("pedidos"); %>
        <%for(Pedido pedido : pedidos) {%>
        <br>Id: <%out.print(String.valueOf(pedido.getId()));%>
        <br>Precio: <%out.print(String.valueOf(pedido.getPrecioPedido()));%>
        <br>Estado: <%out.print(pedido.getEstado().getNombre());%>
        <br>Fecha y hora del estado: <%out.print(pedido.getEstado().getFechaHora());%>
        <br>
        <%}%>
        <%--ChF: Debería haber un botón para regresar a la pantalla anterior --%>
    </body>
</html>
