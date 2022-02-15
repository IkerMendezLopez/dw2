<%-- 
    Document   : gracias
    Created on : 21 ene. 2021, 13:35:51
    Author     : dw2
--%>

<%@page import="beans.Cliente"%>
<%@page import="java.util.Collection"%>
<%@page import="beans.LineaPedido"%>
<%@page import="beans.CarroCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Gracias por realizar su pedido</h1>
        <h2>Estos son los datos de su pedido</h2>
        <table>
            <tr>
                <th>Nombre</th>
                <td>${sessionScope.usuario.getNombre()}</td>
            </tr>
            <tr>
                <th>C.P.</th>
                <td>${sessionScope.usuario.getCodigopostal()}</td>
            </tr>
            <tr>
                <th>Domicilio</th>
                <td>${sessionScope.usuario.getDomicilio()}</td>
            </tr>
            <tr>
                <th>Telefono</th>
                <td>${sessionScope.usuario.getTelefono()}</td>
            </tr>
            <tr>
                <th>E-mail</th>
                <td>${sessionScope.usuario.getEmail()}</td>
            </tr>
        </table>
        <h2>Se enviará con la mayor brevedad posible el siguiente pedido:</h2>
        <table>
                <tr>
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
                        out.print("<td>"+lp.getItempedido().getNombre()+"</td>");
                        out.print("<td>"+lp.getItempedido().getPrecio()+"</td>");
                        out.print("<td>"+lp.getCantidad()+"</td>");
                        out.print("</tr>");
                        total+=(lp.getItempedido().getPrecio()*lp.getCantidad());
                    }
                    out.println("<tr>");
                    out.println("<td colspan=3>"+total+"</td>");
                    out.println("</tr>");
                %>
            </table>
            
            <a href="ServletFin?salir">SALIR</a> <a href="ServletFin?tienda">VOLVER A LA TIENDA</a>
    </body>
</html>
