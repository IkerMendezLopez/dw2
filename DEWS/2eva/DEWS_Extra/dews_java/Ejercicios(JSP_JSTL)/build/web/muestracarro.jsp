<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("mapaProductos")==null){
        response.sendRedirect("ServletPrepararProductos");
    }
    else{
        HashMap <String, Integer> mapaProductos = (HashMap <String, Integer>)session.getAttribute("mapaProductos");
        
        if(!mapaProductos.isEmpty()){
            out.print("<h2>TU CARRO</h2>");
            out.print("<ul>");
                           
            Iterator <String> it = mapaProductos.keySet().iterator();
            while(it.hasNext()){
                String clave = it.next();
                out.print("<li><strong>" + clave + ": </strong>" + mapaProductos.get(clave) + " unidades</li>");
            }
                    
            out.print("</ul>");
        }
    }
%>


        
       