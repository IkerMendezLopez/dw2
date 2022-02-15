<%@page import="java.util.HashSet"%>
<%@page import="beans.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TablaItems</title>
    </head>
    <body>
        
        <h2>ITEMS</h2>
        
        <%
            HashSet <Item> lstItems = (HashSet <Item>) session.getAttribute("lstItems");
            
            
                for(Item i : lstItems){
                    out.print("<p><a href='tablaitems.jsp?itemSelect=" + i.getId() +"'>" + i.getNombre() + "</a></li>");
                    
                    if(request.getParameter("itemSelect")!=null){
                        int idItemSelect = Integer.parseInt(request.getParameter("itemSelect"));
                        System.out.print(idItemSelect);
                        if(idItemSelect==i.getId()){
                             out.print("<p><strong>Precio: </strong>" + i.getPrecio() + "</p>");
                             
                            out.print("<p><strong>Compradores: " + i.getLstNombres().size() + "</strong></p>");
                            
                            out.print("<ul>");
                                for(String nombre : i.getLstNombres()){
                                   out.print("<li>" + nombre + "</li>");
                                }
                            out.print("</ul>");
                        }
                    }
                }
            

        %>
        
    </body>
</html>
