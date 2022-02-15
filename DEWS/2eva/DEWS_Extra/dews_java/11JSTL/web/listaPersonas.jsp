<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Personas</title>
    </head>
    
    <style>
        table, th, td{
            border: 1px solid black;
            border-collapse: collapse;
        }
        
        td{
            width: 60px;
            text-align: center;
        }
        
        li{
            text-align: left;
        }
    </style>
    
    <body>
        
        <c:if test="${sessionScope.personas==null}">
            <jsp:forward page="ServletInicio"/>
        </c:if>
        
        <table>
            <tr>
                <th>Nombre</th>
                <th>Edad</th>
                <th>Aficiones</th>
            </tr>
            
            <c:forEach items="${sessionScope.personas}" var="persona">
                <tr>
                    <td>${persona.nombre}</td>
                    <td>${persona.edad}</td>
                    <td>
                        <ul>
                            <c:forEach items="${persona.aficiones}" var="aficion">
                                <li>${aficion}</li>
                            </c:forEach>
                        </ul>
                    </td>
                </tr>
            </c:forEach>
        </table>
        
    </body>
</html>
