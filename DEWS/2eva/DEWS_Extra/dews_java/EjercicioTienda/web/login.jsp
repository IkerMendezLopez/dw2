<%-- 
    Document   : login
    Created on : 20-ene-2021, 18:09:57
    Author     : LemonDrops
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <%
        if(session.getAttribute("err")!=null){
    %>
            <script type="text/javascript">
                alert("<% out.print(session.getAttribute("err")); %>");
            </script>
    <%
        }  
    %>
    </head>
    <body>
        <form method="GET" action="ServletLogin">
            <table>
                <tr>
                    <td>Usuario:</td>
                    <td><input type="text" name="nomcli" /></td>
                </tr>
                <tr>
                    <td>Contraseña:</td>
                    <td><input type="password" name="pass" /></td>
                </tr>
                <tr>
                    <td><input type="submit" name="submitLogin" value="Login" /></td>
                    <td><input type="reset" value="Reset" /></td>
                    <td><a href="registro.jsp">REGISTRARSE</a></td>
                </tr>
            </table>
        </form>
         <%
          if(request.getParameter("registrado")!=null){
              out.print("<p style='color: green;'>Cliente registrado</p>");
          }  
        %>
    </body>
</html>
