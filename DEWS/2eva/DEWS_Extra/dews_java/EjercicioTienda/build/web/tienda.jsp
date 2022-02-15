<%-- 
    Document   : tienda
    Created on : 20-ene-2021, 19:42:45
    Author     : LemonDrops
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.HashMap"%>
<%@page import="beans.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LÁMINAS</h1>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Añadir</th>
                </tr>
                <c:forEach var="item" items="${sessionScope.itemsDisponibles}">
                    <form method="POST" action="ServletAgregarLineaPedido">
                        <tr>
                            <td>${item.value.getId()}</td>
                            <input type="hidden" name="iditem" value="${item.value.getId()}">
                            <td>${item.value.getNombre()}</td>
                            <td>${item.value.getPrecio()}</td>
                            <td><input type="text" name="cantidad" value="0" /></td>
                            <td><input type="submit" name="submitAniadir" value="Añadir al carro" /></td>
                        </tr>
                    </form>
                </c:forEach>
            </table>
        <a href="listar_cesta.jsp"><button type="button">Ver Cesta</button></a>  
        <a href="pedir.jsp"><button type="button">Hacer pedido</button></a>   
        <a href="ServletPedidosCliente"><button type="button">Mis pedidos</button></a>     
    </body>
</html>
