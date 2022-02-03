<%-- 
    Document   : errores
    Created on : 05-ene-2022, 23:28:42
    Author     : Akaitz
--%>

<%
    out.println("<p style='color: red'>");
    out.println(request.getParameter("error"));
    out.println("</p>");
%>