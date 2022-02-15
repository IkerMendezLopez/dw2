<%-- 
    Document   : index
    Created on : 27-nov-2020, 12:35:09
    Author     : dw2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h2>Añade un Mensaje</h2>
        
        <form method="post" action="ServletAniadirMensaje">
            Mensaje: <input type="text" name="emisor"/>
            Autor: <input type="text" name="texto"/>
            <input type="submit" name="submitAniadir" value="Añadir Mensaje"/>
        </form>
    </body>
</html>
