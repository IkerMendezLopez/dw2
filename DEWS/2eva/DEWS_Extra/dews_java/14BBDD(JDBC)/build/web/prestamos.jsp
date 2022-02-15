<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prestamos</title>
    </head>
    <body>
        <h1>PRESTAMOS</h1>
        
        
        <form method="post" action="ServletPrestamos">
            <table>
                <tr>
                    <th></th>
                    <th>TITULO</th>
                    <th>GENERO</th>
                    <th>PAGINAS</th>
                </tr>

                <c:forEach items="${sessionScope.libros}" var="libro">
                    <tr>
                        <td><input type="checkbox" name="idLibros" value="${libro.idlibro}" /></td>
                        <td>${libro.titulo}</td>
                        <td>${libro.genero}</td>
                        <td>${libro.paginas} paginas</td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.mapa.get(libro.idlibro)eq 0}">
                                    <span style="color: red">SIN PRESTAMOS</span>
                                </c:when>
                                
                                <c:otherwise>
                                    <a href="prestamos.jsp?idlibro=${libro.idlibro}">${sessionScope.mapa.get(libro.idlibro)} veces prestado</a>
                                    
                                    <c:if test="${param.idlibro==libro.idlibro}">
                                        <table>
                                            <tr>
                                                <th>Fecha</th>
                                                <th>Fecha Devolucion</th>
                                            </tr>

                                            <c:forEach items="${sessionScope.mapaPrestamos.get(libro.idlibro)}" var="prestamo"> <!-- Recogemos del mapa de sesion el valor(prestamo) del libro actual en el que se ha pinchado -->
                                                <tr>
                                                    <td>${prestamo.strFecha()}</td>
                                                    
                                                    <c:if test="${prestamo.fechadev==null}">
                                                        <td style="color: red">Sin devolver</td>
                                                    </c:if>
                                                        
                                                    <c:if test="${prestamo.fechadev!=null}">
                                                        <td>${prestamo.strFechaDev()}</td>
                                                    </c:if>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                    
                <tr>
                    <td colspan="4">
                        <input type="submit" name="submitPrestar" value="TOMAR PRESTADOS"/>
                    </td>    
                </tr>       
            </table>
        </form>

        
    </body>
</html>
