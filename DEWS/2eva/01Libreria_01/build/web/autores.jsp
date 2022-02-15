<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:if test="${autores == null}">
            <jsp:forward page="autores"/>
        </c:if>
        <h1>Lista de Autores</h1>
         <table>
             <tr>
                 <th>Nombre</th>
                 <th>Fecha de nacimiento</th>
                 <th>Nacionalidad</th>
                 <th>Ver Libros</th>
             </tr>
            <c:forEach items="${autores}" var="autor">
                <tr>
                    <td>${autor.nombre}</td>
                    <td><fmt:formatDate value="${autor.fechanac}" pattern="yyyy/MM/dd"/></td>
                    <td>${autor.nacionalidad}</td>
                    <td>${autor.idAutor}</td>
                    <td><a href="autores?idAutor=${autor.idAutor}&nombreAutor=${autor.nombre}" >Ver Libros</a></td>
                </tr>
            </c:forEach>
        </table>
        <h1>Añadir Autor</h1>
        <form action="autores" method="post"> 
            <table>
                <tr>
                    <td><label for="nombre">Nombre:</label></td>
                    <td><input type="text" name="nombre"></td>
                </tr>
                <tr>
                    <td><label for="fecha">Fecha de nacimiento:</label></td>
                    <td><input type="text" name="fecha"></td>
                </tr>
                <tr>
                    <td><label for="nacionalidad">Nacionalidad:</label></td>
                    <td><input type="text" name="nacionalidad"></td>
                </tr>
                <tr>
                    <td><input type="submit" name="nuevoAutor" value="Añadir"></td>
                </tr>
            </table>
        </form>
        <c:if test="${errorAniadir!=null}">
            <p>${errorAniadir}</p>
        </c:if>
    </body>
</html>
