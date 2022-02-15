<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Devoluciones</title>
    </head>
    <body>
        <c:if test="${prestados == null}">
            <jsp:forward page="ServletDevolver"/>
        </c:if>
        <c:if test="${devueltos!=null && devueltos>0}">
            <p style="color:red">Se han devuelto ${devueltos} libros</p>
        </c:if>
        <h1>Libros sin devolver</h1>
        <table>
            <c:forEach items="${prestados.keySet()}" var="prestadoKey" varStatus="loop">
                <tr>
                    <td>${loop.index}.-</td>
                    <td>${prestados.get(prestadoKey).titulo}, ${prestadoKey.dias} dias</td>
                    <c:if test="${devolucionesMarcadas.contains(prestadoKey.id)}">
                        <td><a href="devoluciones?id=${prestadoKey.id}" style="color:red">REVERTIR DEVOLUCION</a></td> 
                    </c:if>
                    <c:if test="${!devolucionesMarcadas.contains(prestadoKey.id)}">
                        <td><a href="devoluciones?id=${prestadoKey.id}">MARCAR DEVOLUCION</a></td> 
                    </c:if>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${devolucionesMarcadas.size()!=0 && devolucionesMarcadas!=null}">
            <p><a href="devoluciones?grabar=true">GRABAR DEVOLUCIONES(${devolucionesMarcadas.size()} libros)</p>
        </c:if>
    </body>
</html>
