<%@page import="java.util.HashMap"%>
<%@page import="beans.Libro"%>
<%@page import="java.util.Date"%>
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
            <c:when test="${requestScope.error eq 0}">
                <p style='color: red'><strong>*No hay libros sin devolver!</strong></p>
            </c:when>

            <c:otherwise>
                <h2>DEVOLUCIONES</h2>

                <ol>
                    <c:forEach items="${sessionScope.lstPrestamos}" var="prestamo">
                        <li>
                            ${sessionScope.mapaLibros.get(prestamo.idlibro).titulo},
                            ${prestamo.diasPrestado()} dias prestado
                            
                            <c:choose>
                                <c:when test="${sessionScope.lstDevoluciones.contains(prestamo.idprestamo)}">
                                    <a href="ServletDevolver?idPrestamo=${prestamo.idprestamo}" style="color: orange">REVERTIR DEVOLUCION</a>
                                </c:when>
                                
                                <c:otherwise>
                                    <a href="ServletDevolver?idPrestamo=${prestamo.idprestamo}" style="color: green">MARCAR DEVOLUCION</a>
                                </c:otherwise>
                            </c:choose>
                            
                        </li><br>
                    </c:forEach>
                </ol>
                
                <c:if test="${sessionScope.lstDevoluciones.size()>0}">
                    <a href="ServletDevolver?grabarDevoluciones" style="margin: auto">GRABAR DEVOLUCIONES (${sessionScope.lstDevoluciones.size()} libros)</a>
                </c:if>
                
            </c:otherwise>
        </c:choose>     
    </body>
</html>
