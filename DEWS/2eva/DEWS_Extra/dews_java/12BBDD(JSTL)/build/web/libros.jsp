<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Libros</title>
    </head>
    <body>
        
        <c:if test="${sessionScope.libros==null}">
            <jsp:forward page="ServletLibros"/>
        </c:if>
        
        
        <h2>LIBROS</h2>
        <c:forEach items="${sessionScope.libros}" var="libro">
            <p><a href="libros.jsp?idlibro=${libro.idlibro}">${libro.titulo}</a>
            
            <c:if test="${param.idlibro==libro.idlibro}">
                GENERO: ${libro.genero}
            </c:if>
            </p>
        </c:forEach>
        
        
        <hr><h2>INSERTAR UN LIBRO</h2>
        <form method="post" action="ServletLibros">
            <label for="titulo">Titulo </label><input type="text" name="titulo" id="titulo" value="${param.titulo}">
            <label for="paginas">Nº Paginas </label><input type="number" name="paginas" id="paginas" value="${param.paginas}">
            <label for="genero">Genero </label><input type="text" name="genero" id="genero" value="${param.genero}">
            <label for="autor">Autor </label>
            <select name="idautor" id="autor">
                <c:forEach items="${sessionScope.mapaAutores}" var="par">
                    <option value="${par.key}">${par.value.nombre}</option>
                </c:forEach>
            </select>
            
            <input type="submit" name="submitInsertar" value="INSERTAR">
        </form> 
        
        
        <c:if test="${requestScope.errorInsert!=null}">
            <p style="color: red"><strong>${requestScope.errorInsert}</strong></p>
        </c:if>
        
    </body>
</html>
