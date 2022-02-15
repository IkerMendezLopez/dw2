package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletLogin extends HttpServlet {

   

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            
            
            out.print("<h2>Logueate</h2>");
            
            if(request.getParameter("errorLogin")!=null){
                out.print("<p style='color: red'><strong>*Login incorrecto. Intentalo de nuevo!</strong></p>");
            }
            
            /*if(request.getAttribute("error")!=null){
                out.print("<p style='color: red'><strong>*Login incorrecto. Intentalo de nuevo!</strong></p>");
            }*/
            
            out.print("<form method='post' action='ServletRedir'>");
                out.print("<input type='password' name='password'/><br><br>");
                out.print("<input type='submit' name='submitLogin' value='LOGIN'/>");
            out.print("</form>");
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //doGet(request, response); //si llega aqui lo manda al doGet de arriba
    }
}
