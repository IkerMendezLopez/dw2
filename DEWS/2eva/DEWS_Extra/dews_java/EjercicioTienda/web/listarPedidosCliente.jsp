<%-- 
    Document   : listarPedidosCliente
    Created on : 21-ene-2021, 17:00:46
    Author     : LemonDrops
--%>

<%@page import="java.util.Map"%>
<%@page import="beans.Pedido"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.HashSet"%>
<%@page import="beans.LineaPedido"%>
<%@page import="dao.PedidoDAO"%>
<%@page import="beans.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>TUS PEDIDOS</h1>
        <table>
            <tr>
                <th>Id</th>
                <th>Costo</th>
                <th>Fecha</th>
                <th>DETALLES</th>
                <th>Anular pedido</th>
            </tr>
            <form method="POST" action="ServletPedidosCliente">
            <%
                Cliente c=(Cliente)session.getAttribute("usuario");
                Map<Integer, Pedido> mapaPedidos=c.getMapaPedidos();
                for(Pedido p: mapaPedidos.values()){
                    out.println("<tr>");
                    out.println("<td>"+p.getId()+"</td>");
                    out.println("<td>"+p.getTotal()+"</td>");
                    out.println("<td>"+p.getFecha()+"</td>");
                    out.println("<td><a href='listarPedidosCliente.jsp?id="+p.getId()+"'>Detalles del pedido</a>");
                    if(request.getParameter("id")!=null && Integer.parseInt(request.getParameter("id"))==p.getId()){
                        HashSet<LineaPedido> lineas=p.getLineas();
                        for(LineaPedido lp : lineas){
                            out.println("<p>"+lp.getId()+" "+lp.getItempedido().getNombre()+" "+lp.getCantidad()+" unidades");
                        }
                    }
                    out.println("</td>");
                    out.println("<td><input type='checkbox' name='anular' value='"+p.getId()+"'></td>");
                    out.println("</tr>");
                }
                out.println("<tr>");
                out.println("<td colspan=4><input type='submit' name='submitAnular' value='ANULAR PEDIDOS MARCADOS'></td>");
                out.println("</tr>");
            %>
            </form>
        </table>
        <a href="tienda.jsp">VOLVER A LA TIENDA</a>
    </body>
</html>
