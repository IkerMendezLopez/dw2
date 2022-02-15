package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;

public final class compra_jsp extends org.apache.jasper.runtime.HttpJspBase
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

    if(session.getAttribute("lstSession")==null){

      out.write("  \n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "ServletPrepararProductos", out, false);
      out.write('\n');
        
    }

    if(session.getAttribute("mapaProductos")==null){
        HashMap <String, Integer> carro = new HashMap <String, Integer>();
        session.setAttribute("mapaProductos", carro);
    }

    HashMap <String, Integer> carro = (HashMap <String, Integer>) session.getAttribute("mapaProductos");


    if(request.getParameter("submitAdquirir")!=null){
        String nomProd = request.getParameter("nomProd");

        if(!carro.containsKey(nomProd)){
            carro.put(nomProd, 1);
        }
        else{
            Integer cant = carro.get(nomProd);
            cant++;
            carro.put(nomProd, cant);
        }
    }

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Compra</title>\n");
      out.write("    </head>\n");
      out.write("       \n");
      out.write("    <body>\n");
      out.write("        <table>\n");
      out.write("            <tr>\n");
      out.write("                <th>PRODCUTO</th>\n");
      out.write("                <th>PEDIR</th>\n");
      out.write("            </tr>  \n");
      out.write("        ");

            ArrayList <String> lstSession = (ArrayList <String>) session.getAttribute("lstSession");
            for(String producto : lstSession){
                out.print("<form method='post' action='" + request.getRequestURI() + "'>");
                    out.print("<tr>");
                        out.print("<td>" + producto + "</td>");
                        out.print("<input type='hidden' name='nomProd' value='" + producto + "'>");
                        out.print("<td><input type='submit' name='submitAdquirir' value='Adquirir unidad'/></td>");
                        
                        if(carro.containsKey(producto)){
                            out.print("<td>" + carro.get(producto) + " unidades</td>");
                        }
                    out.print("</tr>");
                out.print("</form>");
            }
        
      out.write("\n");
      out.write("        </table>\n");
      out.write("        \n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "muestracarro.jsp", out, false);
      out.write("\n");
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
