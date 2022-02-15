<%@page import="java.util.HashSet"%>
<%@page import="beans.Empresa"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva Empresa</title>
    </head>
    <body>
        
        <%
            if(application.getAttribute("todasEmpresas")==null){
                application.setAttribute("todasEmpresas", new HashSet<Empresa>());
            }
            HashSet <Empresa> todasEmpresas = (HashSet <Empresa>) application.getAttribute("todasEmpresas");
            
            
            String nombre = "";
            if(request.getParameter("submitCrearEmpresa")!=null){
                try{
                    nombre = request.getParameter("nombre");
                    float beneficio = Float.parseFloat(request.getParameter("beneficio"));
                    int maxTrab = Integer.parseInt(request.getParameter("maxTrabajadores"));

                    Empresa e = new Empresa(nombre, beneficio, maxTrab); //Variable de ambito pagina
                    if(todasEmpresas.contains(e)){
                        request.setAttribute("error", new Exception("Empresa ya existente!"));
        %>
                            <jsp:include page="newEmpresaError.jsp"/>
        <%
                    }
                    else{
                        session.setAttribute("empresa", e);
                        todasEmpresas.add(e);
                    }
                }
                catch(NumberFormatException e){
                    request.setAttribute("error", e);
        %>
                    <jsp:include page="newEmpresaError.jsp"/>
        <%
                }
            } 


            if(request.getAttribute("noEmpresa")!=null){
                out.print("<p style='color: orange'><strong>Crea una empresa primero!</strong></p>");
            }
        %>
                
                

        <h2>NUEVA EMPRESA</h2>
        <form method="post" action="<%= request.getRequestURI() %>">
            <table>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" value="<%= nombre%>"></td>
                </tr>

                <tr>
                    <td>Beneficio:</td>
                    <td><input type="text" name="beneficio"></td>
                </tr>

                <tr>
                    <td>Nº maximo de Trabajadores: </td>
                    <td><input type="text" name="maxTrabajadores"></td>
                </tr>

                <tr>
                    <td><input type="submit" name="submitCrearEmpresa" value="Crear Empresa"></td>
                </tr>    
            </table>
        </form>
            
        <%
            if(session.getAttribute("empresa")!=null){
                out.print("<p><a href='trabajadores.jsp'>Ver Trabajadores</a></p>");
            }
        %>    
    </body>
</html>
