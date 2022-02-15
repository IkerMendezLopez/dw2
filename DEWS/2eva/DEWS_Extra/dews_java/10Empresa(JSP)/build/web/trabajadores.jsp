<%@page import="beans.Movil"%>
<%@page import="beans.Trabajador"%>
<%@page import="beans.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("empresa")==null){
        request.setAttribute("noEmpresa", "Debes crear una empresa...");
%>
        <jsp:forward page="newEmpresa.jsp"/>
        
<%
    }

    Empresa e = (Empresa) session.getAttribute("empresa");
    if(request.getParameter("trabajarEmpresa")!=null){
        e.trabajar();
    }
    
    if(request.getParameter("trabajarTrabajador")!=null){
        e.getTrabajadores()[Integer.parseInt(request.getParameter("trabajarTrabajador"))].trabajar();
    }


    String errorCrearTrabajador = null;
    if(request.getParameter("submitAniadirTrabajador")!=null){
        String nombre = request.getParameter("nombre");
        String dni = request.getParameter("dni");
        String numero = request.getParameter("numero");
        int bateria = Integer.parseInt(request.getParameter("bateria"));

        Trabajador t = new Trabajador();
            t.setNombre(nombre);
            t.setDni(dni);
            t.setDinero(0);
        Movil m = new Movil();
            m.setNumero(numero);
            m.setBateria(bateria);
        t.setMovil(m);
        
        if(!e.contratar(t)){
            errorCrearTrabajador = "La empresa no admite mas trabajadores!";
        }
    }
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trabajadores</title>
    </head>
    <body>
        <%
            out.print("<p>" + e.toString() + "</p>");
            
            if(errorCrearTrabajador!=null){
                out.print("<p style='color: red'><strong>" + errorCrearTrabajador + "</strong></p>");
            }
            
            
            Trabajador [] trabajadores = e.getTrabajadores();
            out.print("<table>");
                out.print("<tr>");
                    out.print("<th>Nombre</th>");
                    out.print("<th>DNI</th>");
                    out.print("<th>Dinero</th>");
                    out.print("<th>Movil</th>");
                    out.print("<th>Bateria</th>");
                    out.print("<th>Trabajar</th>");
                out.print("</tr>");
                
            for(int i=0; i<e.getTrabActuales(); i++){
                Trabajador t = trabajadores[i];
                out.print("<tr>");
                    out.print("<td>" + t.getNombre() + "</td>");
                    out.print("<td>" + t.getDni() + "</td>");
                    out.print("<td>" + t.getDinero() + "</td>");
                    out.print("<td>" + t.getMovil().getNumero() + "</td>");
                    out.print("<td>" + t.getMovil().getBateria() + "</td>");
                    String enlace = request.getRequestURI()+"?trabajarTrabajador=" + i;
                    out.print("<td><a href='" + enlace + "'>Trabajar</a></td>");
                    
                out.print("</tr>");
            }
            out.print("</table>");
        %>
        
        
        <h2>NUEVO TRABAJADOR</h2>
        <form method="post" action="<%= request.getRequestURI() %>">
            <table>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre"></td>
                </tr>

                <tr>
                    <td>DNI: </td>
                    <td><input type="text" name="dni"></td>
                </tr>

                <tr>
                    <td>Nº Movil: </td>
                    <td><input type="number" name="numero"></td>
                </tr>
                
                <tr>
                    <td>Bateria (%): </td>
                    <td><input type="number" name="bateria"></td>
                </tr>
                
                <tr>
                    <td><input type="submit" name="submitAniadirTrabajador" value="AÑADIR TRABAJADOR"></td>
                </tr> 
            </table>
        </form>
        
            
            <p><a href="<%= request.getRequestURI()+"?trabajarEmpresa" %>">Trabajar Empresa</a></p>
        
    </body>
</html>
