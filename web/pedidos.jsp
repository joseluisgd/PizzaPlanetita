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
        <c:forEach var="x" items="${sessionScope.pedidos}">
            <c:out value="${x.id}"/><br>
            <c:out value="${x.montoTotal}"/><br>
            <c:out value="${x.Estado.estado}"/><br>
        </c:forEach>
    </body>
</html>
