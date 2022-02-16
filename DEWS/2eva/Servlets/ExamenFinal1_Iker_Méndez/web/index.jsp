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
        <h1>Programando ponencias para HOY(<%= sdf.format(new Date()) %>)</h1>
    </body>
</html>
