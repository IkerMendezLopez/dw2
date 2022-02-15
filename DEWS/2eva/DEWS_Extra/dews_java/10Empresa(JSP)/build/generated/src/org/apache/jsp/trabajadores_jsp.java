package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import beans.Movil;
import beans.Trabajador;
import beans.Empresa;

public final class trabajadores_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    if(session.getAttribute("empresa")==null){
        request.setAttribute("noEmpresa", "Debes crear una empresa...");

      out.write("\n");
      out.write("        ");
      if (true) {
        _jspx_page_context.forward("newEmpresa.jsp");
        return;
      }
      out.write("\n");
      out.write("        \n");

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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Trabajadores</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <h2>NUEVO TRABAJADOR</h2>\n");
      out.write("        <form method=\"post\" action=\"");
      out.print( request.getRequestURI() );
      out.write("\">\n");
      out.write("            <table>\n");
      out.write("                <tr>\n");
      out.write("                    <td>Nombre:</td>\n");
      out.write("                    <td><input type=\"text\" name=\"nombre\"></td>\n");
      out.write("                </tr>\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>DNI: </td>\n");
      out.write("                    <td><input type=\"text\" name=\"dni\"></td>\n");
      out.write("                </tr>\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>Nº Movil: </td>\n");
      out.write("                    <td><input type=\"number\" name=\"numero\"></td>\n");
      out.write("                </tr>\n");
      out.write("                \n");
      out.write("                <tr>\n");
      out.write("                    <td>Bateria (%): </td>\n");
      out.write("                    <td><input type=\"number\" name=\"bateria\"></td>\n");
      out.write("                </tr>\n");
      out.write("                \n");
      out.write("                <tr>\n");
      out.write("                    <td><input type=\"submit\" name=\"submitAniadirTrabajador\" value=\"AÑADIR TRABAJADOR\"></td>\n");
      out.write("                </tr> \n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("        \n");
      out.write("            \n");
      out.write("            <p><a href=\"");
      out.print( request.getRequestURI()+"?trabajarEmpresa" );
      out.write("\">Trabajar Empresa</a></p>\n");
      out.write("        \n");
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
