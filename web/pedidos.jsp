<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach var="pedido" items="${sessionScope.pedidos}">
            <%-- ChF: Continuar aquí --%>
            <c:out value="${pedido.id}"/><br>
            <c:out value="${pedido.montoTotal}"/><br>
            <c:out value="${pedido.Estado.estado}"/><br>
        </c:forEach>
    </body>
</html>
