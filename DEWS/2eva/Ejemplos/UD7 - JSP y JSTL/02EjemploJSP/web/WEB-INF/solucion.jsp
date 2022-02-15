<%-- 
    Document   : solucion
    Created on : 05-ene-2022, 23:43:27
    Author     : Akaitz
--%>

<%
    int a = Integer.parseInt(request.getParameter("a"));
    int b = Integer.parseInt(request.getParameter("b"));
    int c = Integer.parseInt(request.getParameter("c"));
    double x1 = Double.parseDouble(request.getParameter("x1"));
    double x2 = Double.parseDouble(request.getParameter("x2"));
    
    out.println("<ul>Solución a: " + a + "x<sup>2</sup> + " + b + "x + " + c + " = 0");
    out.println("<li>x1 = " + x1 + "</li>");
    out.println("<li>x2 = " + x2 + "</li>");
    out.println("</ul>");
%>