package servlets;

import beans.Prestamo;
import dao.DaoBiblio;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletPrestamos extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession().setAttribute("libros", DaoBiblio.libros());
        request.getSession().setAttribute("mapa", DaoBiblio.mapCantidadPrestamos());
        request.getSession().setAttribute("mapaPrestamos", DaoBiblio.mapaPrestamos());
        
        response.sendRedirect("prestamos.jsp");
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitPrestar")!=null){
            
            String [] strIds = request.getParameterValues("idLibros");
            
            if(strIds!=null){
                int [] ids = new int [strIds.length];
            
                for(int i=0; i<ids.length; i++){
                    ids[i] = Integer.parseInt(strIds[i]);
                }
                
                DaoBiblio.prestarLibros(ids);
            }
        }
        
        request.getSession().setAttribute("mapa", DaoBiblio.mapCantidadPrestamos());
        response.sendRedirect("prestamos.jsp");
    }

}
