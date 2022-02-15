<%@page import="beans.Prestamo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar Prestamo</title>
    </head>
    <body>
        <%
            if(request.getAttribute("prestamo")!=null){
                out.print("<p style='color: green'><strong>Prestamo realizado correctamente! Libro prestado: " + ((Prestamo)request.getAttribute("prestamo")).getIdlibro() + "</strong></p>");
            }
            else{
                out.print("<p style='color: red'><strong>No se ha podido insertar el prestamo!</strong></p>");
            }
        %>
        
        <form method="post" action="ServletInsertar">            
            Fecha <input type="date" name="fecha"/>
            Fecha Devolucion <input type="text" name="fechaDev"/>
            ID Libro <input type="text" name="idLibro"/>
            
            <input type="submit" name="submitInsertar" value="INSERTAR"/>
        </form>
        
    </body>
</html>
