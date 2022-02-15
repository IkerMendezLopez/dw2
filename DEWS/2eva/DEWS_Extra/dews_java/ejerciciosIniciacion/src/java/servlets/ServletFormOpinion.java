package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletFormOpinion extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
            
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Opinion</title>");            
            out.println("</head>");
            out.println("<body>");
            
                out.print("<form method='post' action='ServletFormOpinion'>");
                    out.print("<strong>Nombre</strong> <input type='text' name='nombre' size='16'/><br><br>");
                    out.print("<strong>Apellidos</strong> <input type='text' name='apellidos' size='32'/><br><br>");
                    
                    out.print("<strong>Opinion que le ha merecido este sitio web</strong><br>");
                    out.print("<input type='radio' name='opinion' value='B'/>Buena<br>");
                    out.print("<input type='radio' name='opinion' value='R'/>Regular<br>");
                    out.print("<input type='radio' name='opinion' value='M'/>Mala<br><br>");
                    
                    out.print("<p>Comentarios</p>");
                    out.print("<textarea></textarea><br><br>");
                    
                    out.print("<strong>Tus secciones favoritas</strong>");
                    

                out.print("</form>");
            
            out.println("</body>");
            out.println("</html>");
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    


}
