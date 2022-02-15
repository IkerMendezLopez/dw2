package servlets;

import beans.Libro;
import beans.Prestamo;
import dao.DaoDevoluciones;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletDevolver extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("grabarDevoluciones")!=null){
            DaoDevoluciones.grabarDevoluciones((ArrayList<Integer>) request.getSession().getAttribute("lstDevoluciones"));
               
            request.getSession().setAttribute("lstPrestamos", DaoDevoluciones.prestamos());
        }
        
        if(request.getParameter("idPrestamo")!=null){
            
            int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
            ArrayList <Integer> lstDevoluciones = (ArrayList <Integer>) request.getSession().getAttribute("lstDevoluciones");
            
            int posicionDevolucion = lstDevoluciones.indexOf(idPrestamo);
            if(posicionDevolucion!=-1){
                lstDevoluciones.remove(posicionDevolucion);
            }
            else{
                lstDevoluciones.add(idPrestamo);
            }
        }
        else{
            ArrayList <Prestamo> lstPrestamos = DaoDevoluciones.prestamos();
        
            if(lstPrestamos.isEmpty()){
                request.setAttribute("error", 0);
            }
            else{
                request.getSession().setAttribute("lstPrestamos", lstPrestamos);
                request.getSession().setAttribute("mapaLibros", DaoDevoluciones.libros());
                request.getSession().setAttribute("lstDevoluciones", new ArrayList <Integer>());
            }
        }
        
        
        request.getRequestDispatcher("devoluciones.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doGet(request, response);
    }

    


}
