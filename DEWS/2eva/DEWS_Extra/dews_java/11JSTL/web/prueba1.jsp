<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba 1</title>
    </head>
    <body>
        
        <h2>Pagina de prubea JSTL</h2>
        
        <c:if test="${param.usuario!=null}">
            <p>Hola ${param.usuario}</p>
        </c:if>
            
            
        <c:choose>
            <c:when test="${Math.random()>0.5}">
                <p style="color: green"><strong>Te ha tocado la primitiva :D</p>
            </c:when>
            
            <c:otherwise>
                <p style="color: red">Sigue buscando :C</p>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>
