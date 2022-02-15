<%-- 
    Document   : circulo
    Created on : 05-ene-2022, 10:03:09
    Author     : Akaitz
--%>

<%
    int radio = 5;
    if(request.getParameter("radio") != null){
        radio = Integer.parseInt(request.getParameter("radio"));
    }
    out.println("<p>Circulo de radio: " + radio + "</p>");
    out.println("<p>Area: " + Math.PI*radio*radio + "</p>");
    out.println("<p>Perimetro " + Math.PI*2*radio + "</p>");
%>
