<%-- 
    Document   : index
    Created on : 05-ene-2022, 8:43:32
    Author     : Akaitz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.io.IOException" %>
<%@page import="java.util.Date" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página Pruebas</title>
        <%!
            void tabla(JspWriter out, int numero){
                for(int i = 1;i <= 10;i++){
                    try{
                        out.print(numero + " x " + i + " = " + numero*i + "<br>");
                    }catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
            
            int accesos = 0;
        %>
    </head>
    <body>
        <%---------- OBJETOS IMPLICITIOS ----------------%>
        <h2>Expresiones JSP</h2>
        <ul>
            <li>Fecha actual: <%= new Date() %></li>
            <li>Su máquina: <%= request.getRemoteHost() %></li>
            <li>Su ID  de sesión: <%= session.getId() %></li>
            <li>El párametro <code>nombre</code>: <%= request.getParameter("nombre") %></li>
        </ul>
        
        <%---------- MEZCLA JAVA-HTML -------------------%>
        <p>
            <% if(Math.random() < 0.5){%>
                <b>Que tenga un buen día</b>
            <%}else{
                out.println("<u>Que tenga mucha suerte " + request.getParameter("nombre") + "</u>");
               }
            %>
        </p>
        
        <%---------- MÉTODOS DEL SERVLET -----------------%>
        <%
            out.print("<h1>Tabla del 7</h1>");
            tabla(out, 7);
            out.print("<br>");
            out.print("<h1>Tabla del 4</h1>");
            tabla(out, 4);
        %>
        
        <%---------- ATRIBUTOS DEL SERVLET ---------------%>
        <p>
            <%= ++accesos + " accesos a esta página." %>
        </p>
        
        <%---------- DIFERENCIA DIRECTIVA/ACCIÓN INCLUDE --%>
        <%----- Directiva: incluye el código antes de compilarse el servlet: tiene
                acceso a variables de la principal. 
                Está más pensada para contenidos estáticos: pies, cabeceras o 
                plantillas.-------%>
        <% String colorparr = "red"; %>
        <%@include file="incluida_directiva.jsp" %>
        
        <%----- Acción: se incluye la salida de la página incluida.
                Está más pensada para contenido dinámico. Para hacer parte de la
                labor de la principal. -------%>
        <jsp:include page="WEB-INF/circulo.jsp"/>
        
    </body>
</html>
