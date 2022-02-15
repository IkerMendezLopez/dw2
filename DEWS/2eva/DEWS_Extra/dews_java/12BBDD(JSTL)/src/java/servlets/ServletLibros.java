package servlets;

import beans.Libro;
import dao.GestorBD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletLibros extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        GestorBD gbd = new GestorBD();
        request.getSession().setAttribute("libros", gbd.libros());
        request.getSession().setAttribute("mapaAutores", gbd.autores());
        
        //response.sendRedirect("libros.jsp");
        request.getRequestDispatcher("libros.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitInsertar")!=null){
            
            try{
                String titulo = request.getParameter("titulo");
                int paginas = Integer.parseInt(request.getParameter("paginas"));
                String genero = request.getParameter("genero");
                int idautor = Integer.parseInt(request.getParameter("idautor"));
                
                Libro l = new Libro(titulo, genero, paginas, idautor);
                GestorBD gbd = new GestorBD();
                
                gbd.insertarLibro(l);
            }
            catch(NumberFormatException e){
                request.setAttribute("errorInsert", "*Introduzca un valor numerico para el Nº de paginas!");
            }
        }
        
        doGet(request, response);
    }

    


}
