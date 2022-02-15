<%-- 
    Document   : empresa
    Created on : 06-ene-2022, 18:54:54
    Author     : Akaitz
--%>

<%@page import="beans.Empresa"%>
<%@page import="java.util.HashSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empresa</title>
    </head>
    <body>
        <%
            if(application.getAttribute("empresas") == null){
                application.setAttribute("empresas", new HashSet<String>());
            }
            
            if(request.getParameter("enviaremp") != null){
                boolean errorCrearEmpresa = false;
                String nombre = request.getParameter("nombre");
                double beneficio;
                int trab;
                
                try {
                    trab = Integer.parseInt(request.getParameter("trab"));
                    beneficio = Double.parseDouble(request.getParameter("beneficio"));
                    
                    //Solo creamos la empresa en sesión si no hay otra con el 
                    //mismo nombre en el contexto
                    if(!((HashSet)application.getAttribute("empresas")).contains(nombre)){
                        session.setAttribute("empresa", new Empresa(nombre, beneficio, trab));
                        ((HashSet)application.getAttribute("empresas")).add(nombre);
                        out.println("<p>Empresa " + ((Empresa)session.getAttribute("empresa")).getNombre() + " creada</p>");
                        out.println("<p><a href='trabajadores.jsp'>Añadir trabajadores</a></p>");
                    }else{
                        out.println("<p>Debes introducir los datos de la empresa corrrectamente y no ser una empresa existente.</p>");
                    }
                } catch (NumberFormatException e) {
                    out.println("<p>Debes introducir los datos de la empresa correctamente.</p>");
                }
            }
            
            Empresa e = (Empresa)session.getAttribute("empresa");
         %>
        <form action="<%= request.getRequestURI() %>" method="post">
            <table cellpadding="3">
                <tr bgcolor="cccccc">
                    <td bgcolor="cccccc" colspan="2">
                        <h3>NUEVA EMPRESA</h3>
                    </td>
                </tr>
                <tr bgcolor="cccccc">
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" value="<%= (e==null)?"":e.getNombre() %>"></td>
                </tr>
                <tr bgcolor="cccccc">
                    <td>Beneficio:</td>
                    <td><input type="text" name="beneficio" value="<%= (e==null)?0:e.getBeneficio() %>"></td>
                </tr>
                <tr bgcolor="cccccc">
                    <td>Max de trabajadores:</td>
                    <td><input type="text" name="trab" value="<%= (e==null)?0:e.getTrabajadores().length %>"></td>
                </tr>
                <tr>
                    <td colspan="2" bgcolor="222222">
                        <p align="center">
                            <input type="submit" name="enviaremp" value="Crear empresa">
                        </p>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
