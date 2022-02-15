<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Registro</h1>
        <form method="POST" action="ServletRegistro">
            <table>
                <tr>
                    <td>Usuario</td>
                    <td><input type="text" name="nombre" /></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" /></td>
                </tr>
                <tr>
                    <td>Domicilio</td>
                    <td><input type="text" name="domicilio" /></td>
                </tr>
                <tr>
                    <td>Código Postal</td>
                    <td><input type="text" name="codigopostal" /></td>
                </tr>
                <tr>
                    <td>Teléfono</td>
                    <td><input type="text" name="telefono" /></td>
                </tr>
                <tr>
                    <td>E-mail</td>
                    <td><input type="text" name="email" /></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submitRegistro" value="Registrarse" /> <input type="reset" value="Reset" /></td>
                </tr>
            </table>
        </form>
        <%
          if(request.getParameter("err")!=null){
              out.print("<p style='color: red;'>No se pudo registrar usuario</p>");
          }  
        %>
    </body>
</html>
