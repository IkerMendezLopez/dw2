
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Akaitz
 */
@WebServlet(name = "ServletCarrito", urlPatterns = {"/ServletCarrito"})
public class ServletCarrito extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Procesamos el nuevo artículo
        String articuloNuevo = request.getParameter("articulo");
        boolean vacio = true;
        
       //Creamos o recuperamos el objeto http session
        HttpSession sesion = request.getSession();

        //Recuperamos la lista de artículos agregados previamente, si existen
        List<String> articulos = (List<String>) sesion.getAttribute("articulos");

        //Verificamos si la lista de articulos existe
        if (articulos == null) {
            //Inicializamos la lista de artículos y lo añadimos a la sesión
            articulos = new ArrayList<>();
            sesion.setAttribute("articulos", articulos);
        }
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Artículos</title>");            
            out.println("</head>");
            out.println("<body>");
            //Revisamos y agregamos el artículo
            if (!articuloNuevo.trim().equals("")) {
                articulos.add(articuloNuevo);
            }else{
                vacio = false;
            }
            if(!vacio){
                out.print("<p style='color: red'>No ha introducido un nuevo artículo</p>");
            }
            if(!articulos.isEmpty()){
                out.print("<h1>Lista de artíuclos</h1>");
                out.print("<ul>");
                //Iteramos todos los artículos
                for (String articulo : articulos) {
                    out.print("<li>" + articulo + "</li>");
                }
                out.print("</ul>");
            }
            out.print("<a href='/02EjemploCarritoCompra'>Regresar al inicio</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
