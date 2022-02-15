package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.io.File;
import java.util.ArrayList;
import beans.Imagen;

public final class visor_005fimagenes_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {


    private final static String URL = "img_visor";


    public String tamanioDesglosado(String imagen){
        /*
        int mb = this.tamanio/1000000;
        int resto = this.tamanio%mb;
        int kb = this.tamanio
        */
        return null;
    }


    private ArrayList <Imagen> imagenesDeCarpeta(){

        ArrayList <Imagen> lstImagenes = new ArrayList<Imagen>();
        
        File f = new File(getServletContext().getRealPath(URL));
        String [] imagenes = f.list();

        for(String imagen : imagenes){
            String ruta = URL + "/" + imagen;
            String nombre = imagen.substring(0, imagen.lastIndexOf("."));
                File fImagen = new File(ruta);
            long tamanio = fImagen.length();
            
            lstImagenes.add(new Imagen(ruta, nombre, tamanio));
        }
        
        return lstImagenes;
    }
    
    


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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Visor de Imagenes</title>\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <style>\n");
      out.write("        form{\n");
      out.write("            border: 2px solid black;\n");
      out.write("            width: 502px;\n");
      out.write("            padding: 2px;\n");
      out.write("        }\n");
      out.write("    </style>\n");
      out.write("    \n");
      out.write("    <body>\n");
      out.write("        ");

            boolean mostrarImg = false;
            if(request.getParameter("submitVer")!=null){
                if(request.getParameter("pixeles")!=null){
                    mostrarImg = true;
                }
                else{
                    out.print("<p style='color: red'><strong>*Debes seleccionar un tamño antes de visualizar la imagen!</strong></p>");
                }
            }
        
      out.write("\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        <form method=\"post\" action=\"");
      out.print( request.getRequestURI() );
      out.write("\">\n");
      out.write("            <strong>Imagen:</strong>\n");
      out.write("            <select name=\"cmbImagenes\">\n");
      out.write("            ");

                ArrayList <Imagen> lstImagenes = imagenesDeCarpeta();
                
                for(Imagen img : lstImagenes){
                    out.print("<option value='" + img.getNombre() + "'>" + img.getNombre() + "</option>");
                }
            
      out.write("\n");
      out.write("            </select>\n");
      out.write("            \n");
      out.write("            <strong>Tamaño:</strong>\n");
      out.write("            <input type=\"radio\" name=\"pixeles\" value=\"300\">300px\n");
      out.write("            <input type=\"radio\" name=\"pixeles\" value=\"400\">400px\n");
      out.write("            <input type=\"radio\" name=\"pixeles\" value=\"500\">500px\n");
      out.write("            \n");
      out.write("            <input type=\"submit\" name=\"submitVer\" value=\"VER IMAGEN\">\n");
      out.write("        </form>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("        ");

            if(mostrarImg){
                String imgSeleccionada = request.getParameter("cmbImagenes");
                Imagen img = new Imagen(imgSeleccionada);
                String ruta = "";
                
                ArrayList <Imagen> lstImagenes2 = imagenesDeCarpeta();
                if(lstImagenes.contains(img)){
                    Imagen imgSeleccionada2 =  lstImagenes2.get(lstImagenes2.indexOf(img));
                    ruta = imgSeleccionada2.getRuta();
                }
                
                
                
                String tamanio = tamanioDesglosado(imgSeleccionada);
                out.print("<p><strong>Tamaño         </strong></p>"); //FALTA EL TAMAÑO
                
                String enlace = URL + "/" + request.getParameter("cmbImagenes") + "." + ruta.substring(ruta.lastIndexOf(".")+1, ruta.length());
                int pixeles = Integer.parseInt(request.getParameter("pixeles"));
                out.print("<img src='" + enlace + "' width='" + pixeles + "' height='" + pixeles + "'/>");
            }
        
      out.write("\n");
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
