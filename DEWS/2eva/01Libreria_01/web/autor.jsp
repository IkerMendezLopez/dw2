<%-- 
    Document   : autor
    Created on : 14 feb 2022, 20:49:53
    Author     : Méndez
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${autor == null}">
            <jsp:forward page="autores"/>
        </c:if>
        <h1>Libros de ${autor}</h1>
        <ul>
            <c:forEach items="${librosAutor}" var="libro">
                <li><a href="autores?aniadir=${libro.idLibro}&nombreAutor=${autor}">${libro.titulo}</a></li>
            </c:forEach>
        </ul>
        <c:if test="${aniadido!=null}">
            <p>Libro prestado</p> 
        </c:if>
            <p><a href="autores">Volver a Autores</a></p>
    </body>
</html>
