<%-- 
    Document   : index.jsp
    Created on : 16 feb 2022, 9:44:19
    Author     : dw2
--%>

<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reservas</title>
    </head>
    <body>
        <%!SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); %>
        <%!int hora = 9; %>
        <%!int mins = 30; %>
        <h1>Programando ponencias para HOY(<%= sdf.format(new Date()) %>)</h1>
        <form method="post" action="ServletCharlas">
            Desde las<input type="text" name="hora" value=<%=hora%>>h<input type="text" name="minutos" value=<%=mins%>>m
            <br>cada<input type="text" name="intervalo" >minutos
            <br><input type="submit" value="Organizar Potecias" name="organizar">
        </form>
        <c:if test="${error!=null}">
            <p>${error}</p> 
        </c:if>
            <p><a href="ServletReservas">Ir a Reservas</a></p>
    </body>
</html>
