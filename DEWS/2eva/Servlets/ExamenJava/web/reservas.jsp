<%-- 
    Document   : reservas
    Created on : 16 feb 2022, 11:16:25
    Author     : dw2
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservas</title>
    </head>
    <body>
        <h1>Reservas</h1>
        <form action="ServletReservas" method="post">
            <table>
                 <tr>
                     <th>CHARLA</th>
                     <th>SALA</th>
                     <th>TARIFA</th>
                     <th>RESERVAS</th>
                 </tr>
                 <!-- LinkedHashMap<Charla, HashSet<Reserva>> -->
                <c:forEach items="${charlas.keySet()}" var="charlaKey" varStatus="loop1">
                    <tr>
                        <td><label name="nomCharla">${charlaKey.getTema()}</label></td>
                        <td><label name="sala">${charlaKey.getId_sala()}</label></td>
                        <td><label name="tarifa">${charlaKey.getTarifa()}</label></td>
                        <c:if test="${charlas.get(charlaKey).size()>0}">
                            <td><label name="reservas"><a href="ServletReservas?reserva=${loop1.index}">${charlas.get(charlaKey).size()} RESERVAS</a></label></td>
                            <c:if test="${reserva!=null && reserva==loop1.index}">
                                <c:forEach items="${charlas.get(charlaKey)}" var="reserva" varStatus="loop">
                                <tr>
                                    <td></td>
                                    <td></td>
                                    <td></td>
                                    <c:if test="${reserva.getPagado()==1}">
                                        <td>${reserva.getNom_cli()}(Reserva pagada)</td>
                                        <c:if test="${anulaciones.contains(reserva.getId_reserva())}">
                                            <td><a href="ServletReservas?reserva=${loop1.index}&anulada=${reserva.getId_reserva()}">Desmarcar anulación</a></td>
                                        </c:if>
                                        <c:if test="${!anulaciones.contains(reserva.getId_reserva())}">   
                                            <td><a href="ServletReservas?reserva=${loop1.index}&anulada=${reserva.getId_reserva()}">Anular</a></td>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${reserva.getPagado()==0}"> 
                                        <td>${reserva.getNom_cli()}(Reserva no pagada)</td>
                                      
                                    </c:if>
                                </tr>
                                </c:forEach>
                            </c:if>
                        </c:if>
                        <c:if test="${charlas.get(charlaKey).size()==0}">
                            <td><label name="reservas">Sin reservas</label></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
