<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="beans.Alumno"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Actividad"%>
<%@page import="beans.Impartidor"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>IMPARTIDOR</h1>
        <%  
            HttpSession ses = request.getSession();
            Impartidor impartidor = (Impartidor)ses.getAttribute("impartidor");
            out.print("SOCIO: "+impartidor.getNombre()+" "+impartidor.getApellido());
            out.print("<table>");
            for (Actividad actividad : (ArrayList<Actividad>)ses.getAttribute("actividades")) {
                String estilo = "";
                if(request.getParameter("actividad")!=null && actividad.getId() == Integer.parseInt(request.getParameter("actividad"))){
                    estilo = "background-color: greenyellow";
                }
                out.print("<tr style='"+estilo+"'>"
                            + "<td>"+actividad.getNombre()+"</td>"
                            + "<td><a href='ServletAvisos?actividad="+actividad.getId()+"'>ASISTENCIA</a></td>"
                        + "</tr>");
            }
            out.print("</table>");
            
            if(ses.getAttribute("mapa")!=null){
                out.print("<table>"
                        + "<tr><th>Nombre</th><th>Teléfono</th><th>Email</th><th>Ultima asistencia</th><th>Tipo de aviso</th></tr>");
                        HashMap<Alumno, java.util.Date> mapa = (HashMap<Alumno, java.util.Date>) ses.getAttribute("mapa");
                        for (Map.Entry<Alumno, java.util.Date> en : mapa.entrySet()) {
                            Alumno alumno = en.getKey();
                            java.util.Date fecha = en.getValue();
                            SimpleDateFormat formateador = new SimpleDateFormat("dd-MM-yyyy");
                            String f =formateador.format(fecha);
                            
                            Date dif = new Date(new Date().getTime()-fecha.getTime());
                            if(dif.getTime()/1000>(60*60*24*100)){
                                String avisado ="";
                                if(request.getParameter("avisado")!=null&&request.getParameter("avisado").equals(alumno.getDni())){
                                    //no se el caracter de los minutos en java, no está en los apuintes
                                    avisado = "AVISADO: "+alumno.getNombre()+" "+alumno.getApellidos()+" "+ new SimpleDateFormat("dd-mm-yyyy H:m:s").format(new Date());
                                }
                                out.print("<tr>");
                                    out.print("<form method='POST' action='ServletAvisos'>");
                                        out.print("<input type='hidden' name='dni' value='"+alumno.getDni()+"'>");
                                        out.print("<input type='hidden' name='actividad' value='"+request.getParameter("actividad")+"'>");
                                        out.print("<td>"+alumno.getNombre()+" "+alumno.getApellidos()+"</td>");
                                        out.print("<td><input type='text' name='telefono' value='"+alumno.getTelefono()+"'></td>");
                                        out.print("<td><input type='text' name='email' value='"+alumno.getEmail()+"'></td>");
                                        out.print("<td>"+f+"</td>");
                                        out.print("<td>"
                                                + "<input type='radio' name='aviso' value='email' checked>email"
                                                + "<input type='radio' name='aviso' value='telefono'>telefono"
                                                + "<input type='submit' name='submitAviso' value='AVISAR'>"
                                                + ""+avisado
                                                + "</td>");
                                    out.print("</form>");
                                out.print("</tr>");
                            }else{
                                out.print("<tr>");
                                    out.print("<td>"+alumno.getNombre()+" "+alumno.getApellidos()+"</td>");
                                    out.print("<td>"+alumno.getTelefono()+"</td>");
                                    out.print("<td>"+alumno.getEmail()+"</td>");
                                    out.print("<td>"+f+"</td>");
                                    out.print("<td></td>");
                                out.print("</tr>");
                            }
                        }
                out.print("</table>");
            }
        %>
         
    </body>
</html>
