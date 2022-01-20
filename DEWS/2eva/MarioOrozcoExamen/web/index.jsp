<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>LOGIN</h1>
        <p>ALUMNO-> 12345678Z - blagil</p>
        <p>IMPARTIDOR-> 1 - damocles</p>
        <form method="POST" action="ServletLogin">
            <p>Usuario:
                <input type="text" name="usuario">
                Password:
                <input type="password" name="password">
                <input type="submit" name="submitAlumno" value="ALUMNO">
                <input type="submit" name="submitImpartidor" value="IMPARTIDOR">
            </p>
        </form>
    </body>
</html>
