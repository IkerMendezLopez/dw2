<%-- 
    Document   : variablesImplicitas
    Created on : 15-dic-2021, 9:49:29
    Author     : Akaitz
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EL y Variables Implicitas</title>
    </head>
    <body>
        <h1>EL y Variables Implicitas</h1>
        <ul>
            <li>Nombre de la aplicación: ${pageContext.request.contextPath}</li>
            <li>Navegador del cliente: ${header["User-Agent"]}</li>
            <li>Id Sesión: ${cookie.JSESSIONID.value}</li>
            <li>Web Server: ${pageContext.servletContext.serverInfo}</li>
            <li>Valor parametro: ${param.usuario}</li>
            <li>Valor parametro: ${param["usuario"]}</li>
        </ul>
        <p><a href="index.jsp">Regresar al inicio</a></p>
    </body>
</html>
