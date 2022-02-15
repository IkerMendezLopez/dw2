<%-- 
    Document   : alumno
    Created on : 4 feb 2022, 9:39:22
    Author     : dw2
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
        <p>Socio: ${nombreAl}</p>
        <table>
            <tr>
                <th>ACTIVIDAD</th>
                <th>PRECIO</th>
                <th>IMPARTIDOR</th>
            </tr>
            <c:forEach items="${actividadesAct.keySet()}" var="actividadKey">
                <tr>
                    <td>${actividadKey.nombre}</td>
                    <td>${actividadKey.coste_mensual}</td>
                    <td>${actividadesAct.get(actividadKey).nombre} ${actividadesAct.get(actividadKey).apellido}</td>     
                </tr>
            </c:forEach>
        </table>
        <table>
            <tr>
                <th>ACTIVIDAD</th>
                <th>IMPARTIDOR</th>
                <th>APUNTARSE</th>
            </tr>
            <c:forEach items="${actividadesNoAct.keySet()}" var="actividadNoKey">
                <tr>
                    <td>${actividadNoKey.nombre}</td>
                    <td>${actividadesNoAct.get(actividadNoKey).nombre} ${actividadesNoAct.get(actividadNoKey).apellido}</td>  
                    <c:if test="${inscripcionesMarcadas.contains(actividadNoKey.id)}">
                        <td><a href="ServletInscripcion?id=${actividadNoKey.id}" style="color:red">ANULAR</a></td> 
                    </c:if>
                    <c:if test="${!inscripcionesMarcadas.contains(actividadNoKey.id)}">
                        <td><a href="ServletInscripcion?id=${actividadNoKey.id}">APUNTARSE</a></td> 
                    </c:if>
                </tr>
            </c:forEach>
        </table>
         <c:if test="${inscripcionesMarcadas.size()!=0 && inscripcionesMarcadas!=null}">
            <p><a href="ServletInscripcion?grabar=true">GUARDAR LAS(${inscripcionesMarcadas.size()} NUEVAS INSCRIPCIONES)</p>
        </c:if>
    </body>
</html>
