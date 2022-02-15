package servlets;

import beans.AlmacenMensajes;
import beans.Mensaje;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletAniadirMensaje extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitAniadir")!=null){
            String emisor = request.getParameter("emisor");
            String texto = request.getParameter("texto");
            
            Mensaje m = new Mensaje();
            m.setEmisor(emisor);
            m.setTexto(texto);
            m.setFecha(new java.util.Date());
            m.setEnContra(0);
            m.setaFavor(0);
            
            AlmacenMensajes.aniadirMensaje(m);
            
            response.sendRedirect("ServletVerMensajes");
        }
    }
}
