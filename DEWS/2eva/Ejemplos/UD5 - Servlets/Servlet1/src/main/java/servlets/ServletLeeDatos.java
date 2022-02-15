
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ServletLeeDatos", urlPatterns = {"/ServletLeeDatos"})
public class ServletLeeDatos extends HttpServlet{
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //Obtener datos del formulario
        request.setCharacterEncoding("utf-8");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String edad = request.getParameter("edad");
        String[] hobbies = request.getParameterValues("hobbies");
        
        //Crear respuesta
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head><title>Servlet que procesa un formulario basico</title></head>");
            out.println("<body>");
            out.println("<h1>Hola " + nombre + " " + apellidos + "</h1>");
            out.println("<p>Eres " + edad);
            if(hobbies != null){
                out.println("y tus hobbies son: </p>");
                out.println("<ul>");
                for (String hobby : hobbies) {
                    out.println("<li>" + hobby + "</li>");
                }
                out.println("</ul>");
            }else{
                out.println("</p>");
            }
            out.println("<p>Este formulario ha sido invocado con los siguientes parametros:</p>");
            out.println(request.getQueryString());
            out.println("</body>");
            out.println("</html>");
        } finally {
        }
    }
}
