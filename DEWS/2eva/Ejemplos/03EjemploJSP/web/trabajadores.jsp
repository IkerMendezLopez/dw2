<%-- 
    Document   : trabajadores
    Created on : 06-ene-2022, 19:42:36
    Author     : Akaitz
--%>

<%@page import="beans.TelefonoMovil"%>
<%@page import="beans.Trabajador"%>
<%@page import="beans.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabajadores</title>
    </head>
    <body>
        <%
            Empresa e = (Empresa)session.getAttribute("empresa");
            if(e == null){
                response.sendRedirect("empresa.jsp");
            }
            
            //Es la página contratar.jsp la que se encarga de procesar el formulario
            //y deja preparado a esta el resultado de la última contratación:
            //un atributo de request con el Trabajador contratado/no contratado
            //un parámetro con mensaje de error si no se ha podido contratar.
            
            if(request.getAttribute("ultimo_trabajador") != null){
                Trabajador ultimo_trab = (Trabajador)request.getAttribute("ultimo_trabajador");
                if(request.getParameter("error") != null){
                    out.println(request.getParameter("error") + " " + ultimo_trab.getNombre() + " no contratado");
                }else{
                    out.println(ultimo_trab.getNombre() + " contratado");
                }
            }
            
            if(request.getParameter("trabajar") != null){
                e.trabajar();
            }
        %>
        <%-- Datos de la empresa (sesión) y sus trabajadores --%>
        <table cellpadding="3">
            <tr bgcolor="cccccc">
                <td colspan="5">
                    Empresa: <%= e.getNombre() %>, 
                    Beneficio: <%= e.getBeneficio() %>€, 
                    Max trabajadores: <%= e.getTrabajadores().length %>
                </td>
            </tr>
            <tr bgcolor="#bbbbbb">
                <td colspan="5" align="center">TRABAJADORES DE LA EMPRESA</td>
            </tr>
            <tr>
                <td>NOMBRE</td>
                <td>DNI</td>
                <td>DINERO</td>
                <td>TELEF</td>
                <td>BATERIA</td>
            </tr>
            <%
                for(int i = 0;i < e.getCantTrab();i++){
                    Trabajador trab = e.getTrabajadores()[i];
                    out.print("<tr>");
                    out.print("<td>" + trab.getNombre() + "</td>");
                    out.print("<td>" + trab.getDni() + "</td>");
                    out.print("<td>" + trab.getDinero() + "</td>");
                    out.print("<td>" + ((TelefonoMovil)trab.getMovil()).getNumero() + "</td>");
                    out.print("<td>" + ((TelefonoMovil)trab.getMovil()).getBateria() + "</td>");
                    out.print("</tr>");
                }
            %>
        </table>
        
        <%-- Formulario para crear nuevo trabajador y trabajar --%>
        <form method="post" action="contratar.jsp">
            <table>
                <tr bgcolor="#bbbbbb">
                    <td colspan="2">NUEVO TRABAJADOR</td>
                </tr>
                <tr>
                    <td>Nombre: <input type="text" name="nombre"></td>
                    <td>DNI: <input type="text" name="dni"></td>
                </tr>
                <tr>
                    <td>Telefono: <input type="text" name="numero"></td>
                    <td>Bateria: <input type="text" name="bateria"></td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <input type="submit" name="nuevotrab" value="Añadir trabajador">
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: center">
                        <input type="submit" name="trabajar" value="Trabajar">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
