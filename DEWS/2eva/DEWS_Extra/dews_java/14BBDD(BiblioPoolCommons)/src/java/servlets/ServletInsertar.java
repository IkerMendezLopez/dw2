package servlets;

import beans.Prestamo;
import dao.DaoBiblio;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletInsertar extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
       response.sendRedirect("insertarPrestamo.jsp");
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitInsertar")!=null){
            
            try {
                int idLibro = Integer.parseInt(request.getParameter("idLibro"));
                String strFecha = request.getParameter("fecha");
                String strFechaDev = request.getParameter("fechaDev");
                SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
                Date fecha = formateador.parse(strFecha);
                Date fechaDev = formateador.parse(strFechaDev);
                
                Prestamo p = new Prestamo();
                p.setIdlibro(idLibro);
                p.setFecha(fecha);
                p.setFechaDev(fechaDev);
                
                
                    //Si se ha podido realizar el prestamo guardamos el prestamo en el request, de lo contrario lo guardamos null (tambien guardamos el prestamo con valor null en caso de excepcion)
                boolean insertado = DaoBiblio.insertarPrestamo(p);
                if(insertado){
                    request.setAttribute("prestamo", p);
                }
                else{
                    request.setAttribute("prestamo", null);
                }
            }
            catch (ParseException e) {
                System.err.println("Error en el metodo: " + e.getMessage());
                request.setAttribute("prestamo", null);
            }
            finally{
                request.getRequestDispatcher("insertarPrestamo.jsp").forward(request, response);
            }
        }  
    }

    


}
