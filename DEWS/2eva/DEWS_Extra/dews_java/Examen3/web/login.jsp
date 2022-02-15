<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Login</h1>
        <form action="ServletLogin" method="POST">
            Usuario: 
            <input type="text" name="usuario"> 
            
            Password: 
            <input type="password" name="password"> 
            
            <input type="submit" name="loginComoAlumno" value="ALUMNO">
            <input type="submit" name="loginComoImpartidor" value="IMPARTIDOR">
        </form>
    </body>
</html>
