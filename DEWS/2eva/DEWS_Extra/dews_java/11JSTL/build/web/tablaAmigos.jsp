<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla Amigos</title>
    </head>
    
    <style>
        table, td, th{
            border: 1px solid black;
            border-collapse: collapse;
        }
        td{
           width: 120px; 
           text-align: center;
        }
        
    </style>
    <body>
        
        <c:choose>
            <c:when test="${requestScope.mapaAmigos==null}">
                <jsp:forward page="ServletInicio2"/>
            </c:when>
            
            
            <c:otherwise>
                <table>
                    <tr>
                        <th>NOMBRE</th>
                        <th>DESCRIPCION</th>
                        <th>AMIGOS</th>
                    </tr>
                    
                    <c:forEach items="${requestScope.mapaAmigos}" var="pareja">
                        <tr>
                            <td>${pareja.key.nombre}</td> <!-- si dejamos dollar{pareja.key} visualizara el metodo toString -->
                            <td>${pareja.key.caracteristicas()}</td>
                            <td>
                                <c:forEach items="${pareja.value}" var="persona_amigo" varStatus="estado">
                                    ${estado.index +1} ${persona_amigo.nombre} &nbsp;<br>
                                </c:forEach>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
        
    </body>
</html>
