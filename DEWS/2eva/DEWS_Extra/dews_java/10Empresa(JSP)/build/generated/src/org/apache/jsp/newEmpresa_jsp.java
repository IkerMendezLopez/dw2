package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import beans.Empresa;

public final class newEmpresa_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Nueva Empresa</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        \n");
      out.write("        ");

            if(request.getParameter("submitCrearEmpresa")!=null){
                try{
                    String nombre = request.getParameter("nombre");
                    float beneficio = Float.parseFloat(request.getParameter("beneficio"));
                    int maxTrab = Integer.parseInt(request.getParameter("maxTrabajadores"));

                    Empresa e = new Empresa(nombre, beneficio, maxTrab); //Variable de ambito pagina
                    session.setAttribute("empresa", e);
                }
                catch(NumberFormatException e){
                    request.setAttribute("error", e);
        
      out.write("\n");
      out.write("                    ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "newEmpresaError.jsp", out, false);
      out.write("\n");
      out.write("        ");

                }
            }       
        
      out.write("\n");
      out.write("                \n");
      out.write("                \n");
      out.write("\n");
      out.write("        \n");
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
      out.write("                    <td>Beneficio:</td>\n");
      out.write("                    <td><input type=\"text\" name=\"beneficio\"></td>\n");
      out.write("                </tr>\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td>Nº maximo de Trabajadores: </td>\n");
      out.write("                    <td><input type=\"text\" name=\"maxTrabajadores\"></td>\n");
      out.write("                </tr>\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <td><input type=\"submit\" name=\"submitCrearEmpresa\" value=\"Crear Empresa\"></td>\n");
      out.write("                </tr>    \n");
      out.write("            </table>\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("        ");

            if(session.getAttribute("empresa")!=null){
                out.print("<p><a href='trabajadores.jsp'>Añadir/Ver Trabajadores</a></p>");
            }
        
      out.write("    \n");
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
