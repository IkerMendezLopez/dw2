<%-- 
    Document   : listar_cesta
    Created on : 20-ene-2021, 20:21:50
    Author     : LemonDrops
--%>

<%@page import="java.util.Collection"%>
<%@page import="beans.CarroCompra"%>
<%@page import="java.util.HashMap"%>
<%@page import="beans.LineaPedido"%>
<%@page import="beans.LineaPedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LISTADO CARRO</h1>
        <%
        if(request.getParameter("accion")!=null && request.getParameter("accion").equals("borrar")){
            out.print("<p style='color: green;'>Item borrado</p>"); 
        }
        %>
        <table>
                <tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Precio</th>
                    <th>Cantidad</th>
                    <th>Cambiar</th>
                </tr>
                <%
                  double total=0;
                  CarroCompra c=(CarroCompra)session.getAttribute("carroCompra");
                  Collection<LineaPedido> col=c.getLineasPedido();
                  for(LineaPedido lp : col){
                      out.print("<form method='POST' action='ServletUpdateLineaPedido'>");
                      out.print("<tr>");
                      out.print("<td>"+lp.getItempedido().getId()+"</td>");
                      out.print("<input type='hidden' name='iditem' value='"+lp.getItempedido().getId()+"'>");
                      out.print("<td>"+lp.getItempedido().getNombre()+"</td>");
                      out.print("<td>"+lp.getItempedido().getPrecio()+"</td>");
                      out.print("<td><input type='text' name='cantidad' value='"+lp.getCantidad()+"' /></td>");
                      out.print("<td><input type='submit' name='submitBorrar' value='BORRAR ITEM' /> "
                              + "<input type='submit' name='submitCambiar' value='CAMBIAR ITEM' />");
                      if(request.getParameter("accion")!=null){
                          if(request.getParameter("accion").equals("cambiar") && Integer.parseInt(request.getParameter("iditem"))==lp.getItempedido().getId()){
                            out.print(" Item cambiado</td>");
                          }
                      }
                      total+=(lp.getItempedido().getPrecio()*lp.getCantidad());
                      out.print("</form>");
                  }
                  out.println("</tr>");
                  out.println("<tr>");
                  out.println("<td></td>");
                  out.println("<td></td>");
                  out.println("<th>TOTAL:</th>");
                  out.println("<td>"+total+"</td>");
                  out.println("</tr>");
                %>
        </table>
        <a href="ServletVaciarCesta"><button type="button">VACIAR CESTA</button></a>
        <a href="tienda.jsp"><button type="button">CONTINUAR COMPRA</button></a>
        <a href="pedir.jsp"><button type="button">HACER PEDIDO</button></a>
    </body>
</html>
