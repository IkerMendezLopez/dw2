<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba Directiva Include</title>
    </head>
    <body>
        <p> Comienzo de pagina general </p>
        
        <% String color = "red"; %>
        <%@include file = "03incluido.jsp"%> <!-- Mediante la directiva include todo el codigo que este dentro del archivo se mostrara -->
        
        <p> Final de pagina general </p>
    </body>
</html>
