<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="beans.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Actividad"%>
<%@page import="beans.Impartidor"%>
<%@page import="dao.Dao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("impartidor")==null)
        response.sendRedirect("login.jsp");
    else{
        Impartidor impartidor = (Impartidor) session.getAttribute("impartidor");
        ArrayList<Actividad> actividades = (ArrayList<Actividad>) session.getAttribute("actividades");
        SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMPARTIDOR</title>
    </head>
    <body>
        <h1>IMPARTIDOR</h1>
        <h2><%=impartidor%></h2>
        <table>
            <%
            int id = -1;
            if(request.getParameter("id")!=null)
                id = Integer.parseInt(request.getParameter("id"));
            
            for(Actividad act:actividades){
                if(id==act.getId())
                    out.println("<tr style='background-color:grey;'>");
                else
                    out.println("<tr>");
                
                out.println("<td>"+act.getNombre()+"</td>");
                out.println("<td><a href='impartidor.jsp?id="+act.getId()+"'>ASISTENCIA</td>");
                out.println("</tr");
            }
            %>
        </table>
        <%
        if(id!=-1){
            HashMap<Alumno, Date> mapa = Dao.mapaAsistenciaActividad(id);
            out.println("<table>");
                out.println("<tr>");
                    out.println("<th>Nombre</th>");
                    out.println("<th>Telefono</th>");
                    out.println("<th>Email</th>");
                    out.println("<th>Última asistencia</th>");
                    out.println("<th>Tipo de aviso</th>");
                out.println("</tr>");
            for(Alumno alumno: mapa.keySet()){
                out.println("<form method='POST' action='ServletAvisos'>");
                out.println("<input type='hidden' name='dni' value='" + alumno.getDni() + "'>");
                out.println("<tr>");
                    out.println("<td>"+alumno.getNombre()+"</td>");
                    out.println("<td><input type='text' name='telefono' value='"+alumno.getTelefono()+"'></td>");
                    out.println("<td><input type='text' name='email' value='"+alumno.getEmail()+"'></td>");
                    if(mapa.get(alumno)==null){
                        out.println("<td>SIN ASISTENCIAS</td>");
                    }
                    else{
                        out.println("<td>"+formateador.format(mapa.get(alumno))+"</td>");
                    }
                    out.println("<td>");
                        out.println("Email <input type='radio' name='aviso' value='email' checked>");
                        out.println("Teléfono <input type='radio' name='aviso' value='telefono'>");
                    out.println("</td>");
                    out.println("<td><input type='submit' name='avisar' value='AVISAR'></td>");
                    
                out.println("</tr>");
                out.println("</form>");
            }
            
        }
        %>
    </body>
</html>
<%
}
%>