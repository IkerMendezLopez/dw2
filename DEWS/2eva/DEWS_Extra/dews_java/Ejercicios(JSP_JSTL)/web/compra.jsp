<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("lstSession")==null){
%>  
        <jsp:include page="ServletPrepararProductos"/>
<%        
    }

    if(session.getAttribute("mapaProductos")==null){
        HashMap <String, Integer> carro = new HashMap <String, Integer>();
        session.setAttribute("mapaProductos", carro);
    }

    HashMap <String, Integer> carro = (HashMap <String, Integer>) session.getAttribute("mapaProductos");


    if(request.getParameter("submitAdquirir")!=null){
        String nomProd = request.getParameter("nomProd");

        if(!carro.containsKey(nomProd)){
            carro.put(nomProd, 1);
        }
        else{
            Integer cant = carro.get(nomProd);
            cant++;
            carro.put(nomProd, cant);
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Compra</title>
    </head>
       
    <body>
        <table>
            <tr>
                <th>PRODCUTO</th>
                <th>PEDIR</th>
            </tr>  
        <%
            ArrayList <String> lstSession = (ArrayList <String>) session.getAttribute("lstSession");
            for(String producto : lstSession){
                out.print("<form method='post' action='" + request.getRequestURI() + "'>");
                    out.print("<tr>");
                        out.print("<td>" + producto + "</td>");
                        out.print("<input type='hidden' name='nomProd' value='" + producto + "'>");
                        out.print("<td><input type='submit' name='submitAdquirir' value='Adquirir unidad'/></td>");
                        
                        if(carro.containsKey(producto)){
                            out.print("<td>" + carro.get(producto) + " unidades</td>");
                        }
                    out.print("</tr>");
                out.print("</form>");
            }
        %>
        </table>
        
        <jsp:include page="muestracarro.jsp"/>
    </body>
</html>
