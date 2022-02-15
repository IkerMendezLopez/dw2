package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Servlet2 extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession s = request.getSession();
        
        //Solo llegara aqui si se ha clickado en el enlace
        if(request.getParameter("fin")!=null && request.getParameter("fin").equals("si")){
            s.invalidate();
            s = request.getSession();
        }
        
        if(s.getAttribute("nombre")==null){
            response.sendRedirect("index.html");
        }
        else{
            long t1 = s.getCreationTime();
            Date d1 = new Date(t1);
            
            long t2 = s.getLastAccessedTime();
            Date d2 = new Date(t2);
            
            Date dDif = new Date(t2-t1);
            
            
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Info Sesion</title>");            
                out.println("</head>");
                out.println("<body>");
                
                    out.print("<h1 syle='text-decoration: underline'>INFORME DE LA SESION</h1>");
                    out.print("<h3>Buenos dias <em>" + s.getAttribute("nombre") + "</em></h3>");
                    out.print("<h3>Tu ID de sesion es " + s.getId() + "</h3>");
                    out.print("<h3>Sesion creada en " + d1.toString() + "</h3>");
                    out.print("<h3>Sesion accedida por ultima vez en " + d2.toString() + "</h3>");
                    out.print("<h3>Llevas conectado " + dDif.getMinutes() + "minutos y " + dDif.getSeconds() + "segundos</h3>");

                    String enlaceNoFin = request.getRequestURI() + "?fin=no";
                    out.print("<p><a href='" + enlaceNoFin + "'>Continuar</a></p>");
                    
                    String enlaceFin = request.getRequestURI() + "?fin=si";
                    out.print("<p><a href='" + enlaceFin + "'>Finalizar</a></p>");
                out.println("</body>");
                out.println("</html>");
            }
        }
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    


}
