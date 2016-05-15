<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%-- 
    Document   : pedidoingresado
    Created on : 15/05/2016, 04:33:27 PM
    Author     : Christopher
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="sPedido" method="post">
            <c:forEach var="x" items="${sessionScope.ingredientes}">
                <c:out value="${x.nombre}"/><br>
            </c:forEach>
            <button type="submit"> Ok? </button>
        </form>
    </body>
</html>
