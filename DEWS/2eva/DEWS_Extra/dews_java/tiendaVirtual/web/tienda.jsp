<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tienda</title>
    </head>
    <body>
        
        <h2>LAMINAS</h2>
        
        <table>
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th>Añadir</th>
            </tr>
            
            <tr>
                <% out.print(request.getSession().getAttribute("mapaItems"));%>
                <c:forEach items="${sessionScope.mapaItems}" var="item">
                    <form method="post" action="ServletAgregarLineaPedido"> 
                        <td><p>${item.id}</p></td>
                        <td>${item.nombre}</td>
                        <td>${item.precio}</td>
                        <td>${item.cantidad}0</td>
                        <td><input type="submit" name="submitAniadir" value="Añadir al carro"></td>
                    </form>
                </c:forEach>
            </tr>
        </table>
        
        <input type="submit" name="submitVerCesta" value="Ver cesta">
        <input type="submit" name="submitHacerPedido" value="Hacer pedido">
        <input type="submit" name="submitMisPedidos" value="Mis pedidos">
        
    </body>
</html>
