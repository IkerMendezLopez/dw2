package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;
import beans.Alumno;
import java.util.ArrayList;
import beans.Actividad;
import beans.Impartidor;

public final class impartidor_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <h1>IMPARTIDOR</h1>\n");
      out.write("        ");
  
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
                            
                            if((fecha.getTime()+1000*60*60*24*100)>new java.util.Date().getTime()){
                                out.print("<tr>");
                                    out.print("<td>"+alumno.getNombre()+" "+alumno.getApellidos()+"</td>");
                                    out.print("<td><input type='text' name='telefono' value='"+alumno.getTelefono()+"'></td>");
                                    out.print("<td><input type='text' name='email' value='"+alumno.getEmail()+"'></td>");
                                    out.print("<td>"+f+"</td>");
                                    out.print("<td>"
                                            + "<input type='radio' name='aviso' value='email'>email"
                                            + "<input type='radio' name='aviso' value='telefono'>telefono"
                                            + "<input type='submit' name='submitAviso' value='AVISAR'>"
                                            + "</td>");
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
        
      out.write("\n");
      out.write("         \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
