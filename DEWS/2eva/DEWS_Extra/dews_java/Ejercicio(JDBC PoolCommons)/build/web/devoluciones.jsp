<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Devoluciones</title>
    </head>
    <body>

        <c:choose>
            <c:when test="${param.error==null}">
                <p style='color: red'><strong>*No hay libros sin devolver o bien se ha generado un error!</strong></p>
            </c:when>

            <c:otherwise>
                <h2>DEVOLUCIONES</h2>

                <ol>
                    <c:forEach items="${sessionScope.prestamos}" var="prestamo">
                        <li>hola</li>


                    </c:forEach>
                </ol>
            </c:otherwise>
        </c:choose>   
       
            
        
    </body>
</html>
