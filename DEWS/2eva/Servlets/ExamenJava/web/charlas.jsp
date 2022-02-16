<%-- 
    Document   : charlas.jsp
    Created on : 16 feb 2022, 10:17:12
    Author     : dw2
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Charlas</title>
    </head>
    <body>
        <h2>Preparando charlas de hoy</h2>
        <form action="ServletCharlas" method="post">
            <table>
                 <tr>
                     <th>HORA</th>
                     <th>TEMA</th>
                     <th>SALA</th>
                     <th>DESCUENTO</th>
                     <th></th>
                 </tr>
                <c:forEach items="${listaHoras.keySet()}" var="horaKey">
                    <tr>
                        <td><label name="hora">${horaKey[0]}:${horaKey[1]}</label></td>
                        <td><input type="text" name="tema"></td>
                        <td><select name="salas">
                            <c:forEach items="${listaHoras.get(horaKey)}" var="sala" varStatus="loop">
                                 <option value="${loop.index}">${sala.getId_sala()} para ${sala.getCapacidad()}</option> 
                            </c:forEach>
                        </select></td>
                        <td><input type="checkbox" name="descuento">Descuento</td>
                        <td><input type="submit" name="grabar" value="Grabar Charla"></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
