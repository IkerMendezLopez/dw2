<%-- 
    Document   : index
    Created on : 10-ene-2022, 12:43:36
    Author     : Akaitz
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Libreria</title>
    </head>
    <body>
        <%-- El parámetro libros se encuentran el ambito de sesión --%>
        <c:if test="${libros == null}">
            <jsp:forward page="ServletControlador"/>
        </c:if>
        
        <c:forEach items="${libros}" var="libro">
            <p>
                <a href="index.jsp?idlibro=${libro.idLibro}">${libro.titulo}</a>
            </p>
            <c:if test="${libro.idLibro == param.idlibro}">
                <p>
                    Género: ${libro.genero}, Páginas: ${libro.paginas}
                </p>
            </c:if>
        </c:forEach>
                
        <h3>INSERTA UN NUEVO LIBRO</h3>
        <form action="ServletControlador" method="post">
            Titulo: <input type="text" name="titulo">
            Páginas: <input type="text" name="paginas" value="${libroerroneo.paginas}">
            Género: <input type="text" name="genero" value="${libroerroneo.genero}">
            Autor: <select name="idautor">
                <c:forEach items="${autores}" var="autor">
                    <option value="${autor.key}">${autor.value}</option>
                </c:forEach>
            <input type="submit" name="insertar" value="INSERTAR LIBRO">
            </select>
        </form>
            <a href="devoluciones">Ir a devoluciones</a>
        <p>${errorinsercion}</p>
    </body>
</html>
