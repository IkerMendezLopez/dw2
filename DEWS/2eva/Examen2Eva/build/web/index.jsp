<%-- 
    Document   : index
    Created on : 4 feb 2022, 9:10:46
    Author     : dw2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="ServletLogin" method="post">
            <label for="usuario">Usuario:</label>
            <input type="text" name="usuario">
            <label for="password">Password:</label>
            <input type="text" name="password">
            <input type="submit" name="alumno" value="ALUMNO">
            <input type="submit" name="profesor" value="PROFESOR">
        </form>
    </body>
</html>
