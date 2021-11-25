
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="CabeceraServlet", urlPatterns = {"/cabeceras"})
public class CabeceraServlet extends HttpServlet{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hola Mundo</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Cabeceras:</h1>");
            out.println("<ul>");
            Enumeration<String> nombreDeCabeceras = request.getHeaderNames();
            while (nombreDeCabeceras.hasMoreElements()) {
                String cabecera = nombreDeCabeceras.nextElement();
                out.println("<li><b>" + cabecera + "</b>" + request.getHeader(cabecera) + "</li>");
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");
        }finally{
            out.close();
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        processRequest(request, response);
    }
}
