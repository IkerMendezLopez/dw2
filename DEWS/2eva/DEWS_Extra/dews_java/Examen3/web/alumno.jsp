<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alumno</title>
    </head>
    <body>
        <h1>APARTADO ALUMNO</h1>
        <h2>Socio: ${sessionScope.alumno.nombre} ${sessionScope.alumno.apellidos}</h2>
        <h3>ACTIVIDADES ASIGNADAS</h3>
        <table>
            <tr><th>ACTIVIDAD</th><th>PRECIO</th><th>IMPARTIDOR</th></tr>
            <c:forEach items="${sessionScope.actividades_participa}" var="actividad">
                <tr>
                    <td>${actividad.nombre}</td>
                    <td>${actividad.coste_mensual} €</td>
                    <td>${actividad.impartidor.nombre} ${actividad.impartidor.apellido}</td>
                </tr>
            </c:forEach>
        </table>
        <h3>NUEVAS INSCRIPCIONES</h3>
        <table>
            <tr><th>ACTIVIDAD</th><th>IMPARTIDOR</th><th>APUNTARSE</th></tr>
            <c:forEach items="${sessionScope.actividades_disponibles}" var="actividad">
                <tr>
                    <td>${actividad.nombre}</td>
                    <td>${actividad.impartidor.nombre} ${actividad.impartidor.apellido}</td>
                    <td>
                        <c:if test="${sessionScope.actividadesApuntar==null || 
                              !sessionScope.actividadesApuntar.contains(actividad.id)}">
                            <a href="ServletInscripcion?accion=apuntar&id=${actividad.id}">APUNTARSE</a>
                        </c:if>
                        <c:if test="${sessionScope.actividadesApuntar!=null && 
                              sessionScope.actividadesApuntar.contains(actividad.id)}">
                            <a href="ServletInscripcion?accion=anular&id=${actividad.id}" style="background-color: yellow;">ANULAR</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            
        </table>
        <c:if test="${sessionScope.actividadesApuntar!=null && 
                    !sessionScope.actividadesApuntar.isEmpty()}">
            <a href="ServletInscripcion?accion=guardar">
                GUARDAR ${sessionScope.actividadesApuntar.size()} INSCRIPCIONES
            </a>
        </c:if>
    </body>
</html>
