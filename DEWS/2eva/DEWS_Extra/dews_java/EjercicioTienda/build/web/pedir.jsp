<%-- 
    Document   : pedir
    Created on : 20-ene-2021, 20:02:40
    Author     : LemonDrops
--%>

<%@page import="java.util.Collection"%>
<%@page import="beans.LineaPedido"%>
<%@page import="beans.LineaPedido"%>
<%@page import="beans.CarroCompra"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Pedido</h1>
        <h2>TUS DATOS</h2>
        <form method="POST" action="ServletGrabarCompra">
        <table>
            <tr>
                <th>Nombre</th>
                <td><input type="text" name="nombre" value="<c:out value="${sessionScope.usuario.getNombre()}" />" disabled></td>
            </tr>
            <tr>
                <th>C.P.</th>
                <td><input type="text" name="codigopostal" value="<c:out value="${sessionScope.usuario.getCodigopostal()}" />"></td>
            </tr>
            <tr>
                <th>Domicilio</th>
                <td><input type="text" name="domicilio" value="<c:out value="${sessionScope.usuario.getDomicilio()}" />"></td>
            </tr>
            <tr>
                <th>Telefono</th>
                <td><input type="text" name="telefono" value="<c:out value="${sessionScope.usuario.getTelefono()}" />"></td>
            </tr>
            <tr>
                <th>E-mail</th>
                <td><input type="text" name="email" value="<c:out value="${sessionScope.usuario.getEmail()}" />"></td>
            </tr>
        </table>
        <h2>TU CARRO <a href="listar_cesta.jsp">(EDITAR CARRO)</a></h2>
            <table>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                </tr>
                <%
                    double total=0;
                    CarroCompra c=(CarroCompra)session.getAttribute("carroCompra");
                    Collection<LineaPedido> col=c.getLineasPedido();
                    for(LineaPedido lp : col){
                        out.print("<tr>");
                        out.print("<td>"+lp.getItempedido().getId()+"</td>");
                        out.print("<td>"+lp.getItempedido().getNombre()+"</td>");
                        out.print("<td>"+lp.getItempedido().getPrecio()+"</td>");
                        out.print("<td>"+lp.getCantidad()+"</td>");
                        out.print("</tr>");
                        total+=(lp.getItempedido().getPrecio()*lp.getCantidad());
                    }
                    out.println("<tr>");
                    out.println("<td></td>");
                    out.println("<td></td>");
                    out.println("<th>TOTAL:</th>");
                    out.println("<td>"+total+"</td>");
                    out.println("<td><input type='hidden' name='totalcompra' value='"+total+"'></td>");
                    out.println("</tr>");
                %>
            </table>
            <input type="submit" name="submitCompra" value="COMPRAR">
            <input type="reset" value="RESET">
            <a href="tienda.jsp"><button type="button">MODIFICAR COMPRA</button></a>
        </form>
    </body>
</html>
