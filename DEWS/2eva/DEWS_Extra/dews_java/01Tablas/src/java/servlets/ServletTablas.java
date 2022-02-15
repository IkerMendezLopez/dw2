package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletTablas extends HttpServlet {

    private int cantTablas;
    
    @Override
    public void init() throws ServletException {
        super.init(); 
        
        cantTablas = 0;
        System.err.println("Estas en metodo init(solo entras aqui la primera vez que se ejecuta el servlet), valor de cuantasTablas-->0");
    }

    
    
    private static void verTablasNum(int num, PrintWriter out){
        
        out.println("<h2>Tabla del " + num + "</h2>");
        
        for(int i=1; i<=10; i++){
            out.println("<p>" + num + " * " + i + " = " + (num*i) + "</p>");
        }
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ServletTablas</title>");            
                out.println("</head>");
                out.println("<body>");
                
                if(request.getParameter("num")!=null){
                
                    int num = Integer.parseInt(request.getParameter("num"));
                    
                    verTablasNum(num, out);
                    cantTablas++;
                }
                else{
                    for(int i=1; i<=10; i++){
                        out.print("<p><a href='ServletTablas?num=" + i + "'>Tabla del " + i + "</a></p>");
                    }
                }

                out.println("<i>Un total de <strong>" + cantTablas + " tabla/tablas</strong> visualizadas</i>");
                out.println("</body>");
                out.println("</html>");
            }
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
