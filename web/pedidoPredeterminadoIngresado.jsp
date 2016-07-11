<%--@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" --%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="ulima.edu.pe.beans.producto.pizza.PizzaPedido"%>
<%@page import="ulima.edu.pe.beans.pedido.Pedido"%>
<%@page import="java.util.List"%>
<%@page import="ulima.edu.pe.beans.pedido.ProductoPedido"%>

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
                <%--ChF: Para que no salgan los precios con un culo de decimales:--%>
            <%DecimalFormat formatter = new DecimalFormat();%>
            <%for (ProductoPedido productoPedido : pedido.getProductos()) {%>
                <br>- Nombre: <%=productoPedido.getProducto().getNombre()%>
                <% if (productoPedido.getProducto().esPizza()) {%>
                    <br>- Tamaño: <%=((PizzaPedido) productoPedido.getProducto()).getTamano().getNombre()%>
                <%}%> 
                <br>- Precio unitario: S/ <%= productoPedido.getPrecioUnitario()%>
                <br>- Cantidad: <%=productoPedido.getCantidad()%>
                <br>- Precio total: S/ <%=formatter.format(productoPedido.getPrecioTotal())%>
                <br>
            <%}%>
            <br>Precio del pedido: S/ <%=formatter.format(pedido.getPrecioPedido())%>
            <br>
            <br>Dirección : 
            <br>- Calle: <input type="text" name="calleDireccionPedido"/>
            <br>- Distrito: 
            <select name="distritoDireccionPedido">
                <option value="Ate">Ate</option>
                <option value="Barranco">Barranco</option>
                <option value="Callao">Callao</option>
                <option value="Independencia">Independencia</option>
                <option value="Jesús María">Jesús María</option>
                <option value="La Molina">La Molina</option>
                <option value="La Perla">La Perla</option>
                <option value="La Victoria">La Victoria</option>
                <option value="Lima">Lima</option>
                <option value="Lince">Lince</option>
                <option value="Los Olivos">Los Olivos</option>
                <option value="Magdalena del Mar">Magdalena del Mar</option>
                <option value="Miraflores">Miraflores</option>
                <option value="Pueblo Libre">Pueblo Libre</option>
                <option value="Rimac">Rimac</option>
                <option value="San Borja">San Borja</option>
                <option value="San Isidro">San Isidro</option>
                <option value="San Martín de Porres">San Martín de Porres</option>
                <option value="San Miguel">San Miguel</option>
                <option value="Santiago de Surco">Santiago de Surco</option>
                <option value="Surquillo">Surquillo</option>
            </select>
            <br><button type="submit"> Confirmar pedido </button>
        </form>
    </body>
</html>
