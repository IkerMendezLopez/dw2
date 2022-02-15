<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>ALUMNO</h1>
        <c:out value="SOCIO: ${sessionScope.alumno.nombre} ${sessionScope.alumno.apellidos}"/>
        <table>
            <tr><th colspan="3">ACTIVIDADES ASIGNADAS</th></tr>
            <tr><th>ACTIVIDAD</th><th>COSTE MENSUAL</th><th>IMPARTIDOR</th></tr>
            <c:forEach items="${sessionScope.actividadesSI}" var="actividad">
                <tr>
                    <td>${actividad.nombre}</td>
                    <td>${actividad.coste_mensual}</td>
                    <td>${actividad.impartidor.nombre} ${actividad.impartidor.apellido}</td>
                </tr>
            </c:forEach>
        </table>
        <table>
            <tr><th colspan="3">NUEVAS INSCRIPCIONES</th></tr>
            <tr><th>ACTIVIDAD</th><th>IMPARTIDOR</th><th>APUNTARSE</th></tr>
            <c:forEach items="${sessionScope.actividadesNO}" var="actividad">
                <tr>
                    <td>${actividad.nombre}</td>
                    <td>${actividad.impartidor.nombre} ${actividad.impartidor.apellido}</td>
                    <c:choose>
                        <c:when test="${!sessionScope.apuntarse.contains(actividad.id)}">
                            <td><a href='ServletInscripcion?apuntarse&actividad=${actividad.id}'>APUNTARSE</a></td>
                        </c:when>
                        <c:otherwise>
                            <td><a href='ServletInscripcion?anular&actividad=${actividad.id}'>ANULAR</a></td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${sessionScope.apuntarse.size()>0}">
            <a href="ServletInscripcion?guardar">GUARDAR LAS ${sessionScope.apuntarse.size()} NUEVAS INSCRIPCIONES</a>
        </c:if>
    </body>
</html>
