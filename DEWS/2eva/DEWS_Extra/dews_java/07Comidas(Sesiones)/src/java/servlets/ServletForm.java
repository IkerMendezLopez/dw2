package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletForm extends HttpServlet {
   
    private void dibujarFormArriba(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        
        out.print("<form method='post' action='" + request.getRequestURI() + "'>");
            out.print("<input type='submit' name='submitCrear' value='Generar Campos'/>");
            out.print("<label> con </label>");
            out.print("<input type='number' name='cantAlimentos' value='5'/>");
            out.print("<label> alimentos</label>");
        out.print("</form><br><br>");
    }
    
    private void dibujarFormAbajo(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        
        int cantidad = Integer.parseInt(request.getParameter("cantAlimentos"));
        
        out.print("<form method='post' action='ServletGrabar'>");
            out.print("<table>");
                out.print("<tr><th>Num</th><th>Comida</th><th>Precio</th></tr>");
                
                for(int i=0; i<=cantidad; i++){    
                    out.print("<tr>");
                        out.print("<td>Comida " + i + "</td>");
                        out.print("<td><input type='text' name='nombres'/></td>");
                        out.print("<td><input type='number' name='precios' value='1'/></td>");
                    out.print("</tr>");
                }
                
            out.print("</table>");
            
            out.print("<br><input type='submit' name='submitGrabar' value='CREAR FICHERO DE COMIDAS'/>");
        out.print("</form>");
    }
  
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Formulario Comidas</title>");            
            out.println("</head>");
            out.println("<body>");
            
            dibujarFormArriba(request, response, out);
                
            out.println("</body>");
            out.println("</html>");
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitCrear")!=null){
            
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Formulario Comidas</title>");            
                out.println("</head>");
                out.println("<body>");

                dibujarFormArriba(request, response, out);
                dibujarFormAbajo(request, response, out);

                out.println("</body>");
                out.println("</html>");
            } 
        }
    }

    


}
