<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro</title>
    </head>
    <body>
        
        <%
            if(request.getAttribute("errorRegistro")!=null){
                out.print("<p style='color: red'><strong>" + request.getAttribute("errorRegistro") + "</strong></p>");
            }
        %>
        
        <form method="post" action="ServletRegistro">
            <h2>REGISTRO</h2>
            
            <table>
                <tr>
                    <td>Usuario</td>
                    <td><input type="text" name="usuario"></td>
                </tr>
                
                <tr>
                    <td>Contraseña</td>
                    <td><input type="password" name="password"></td>
                </tr>
                
                <tr>
                    <td>Domicilio</td>
                    <td><input type="text" name="domicilio"></td>
                </tr>
                
                <tr>
                    <td>Cod.Postal</td>
                    <td><input type="text" name="codigoPostal"></td>
                </tr>
                
                <tr>
                    <td>Telefono</td>
                    <td><input type="number" name="telefono"></td>
                </tr>
                
                <tr>
                    <td>E-mail</td>
                    <td><input type="text" name="email"></td>
                </tr>
            </table>
            
            
            <input type="submit" name="submitRegistrarse" value="Registrarse">
            <input type="submit" name="submitReset" value="Reset">
        </form>
        
    </body>
</html>
