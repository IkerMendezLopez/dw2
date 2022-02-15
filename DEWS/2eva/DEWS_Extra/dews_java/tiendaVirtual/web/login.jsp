<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    
    <body>
        <c:if test="${requestScope.errorLogin!=""}">
            <script type="text/javascript">
                alert("<% out.print(request.getAttribute("errorLogin")); %>");
            </script>
        </c:if>
            
        
        <%
            if(request.getAttribute("registrado")!=null){
                out.print("<p style='color: green'><strong>" + request.getAttribute("registrado") + "</strong></p>");
            }
        %>
    
    
        
        <form method="post" action="ServletLogin">
            <h2>LOGIN</h2>
            
            <table>
                <tr>
                    <td>Usuario</td>
                    <td><input type="text" name="usuario" value="${requestScope.userLogin}"></td>
                </tr>
                
                <tr>
                    <td>Contraseña</td>
                    <td><input type="password" name="password"></td>
                </tr>
            </table>
            
            <input type="submit" name="submitLogin" value="Login">
            <input type="submit" name="submitReset" value="Reset">
            <a href="registro.jsp">REGISTRARSE</a>
        </form>
        
    </body>
</html>
