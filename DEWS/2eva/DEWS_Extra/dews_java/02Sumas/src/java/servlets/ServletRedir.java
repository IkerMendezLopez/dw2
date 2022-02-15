package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletRedir extends HttpServlet {

    private final String PASS = "123";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.sendRedirect("ServletLogin"); //Para redirigir a ServletLogin en caso de haber escrito arriba por la URL
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitLogin")!=null){
            
            String pass = request.getParameter("password");
            
            //La password es un parametro de contexto
            String password = this.getServletContext().getInitParameter("password");
            if(pass.equals(password)){
                response.sendRedirect("ServletPregunta");
            }
            else{
                response.sendRedirect("ServletLogin?errorLogin");
            }
            
            
            /*
            if(pass.equals(PASS)){
                response.sendRedirect("ServletPregunta");
            }
            else{
                response.sendRedirect("ServletLogin?errorLogin");
                
                //request.setAttribute("error", "Login Error");
                //request.getRequestDispatcher("ServletLogin").forward(request, response); //al hacer un forward ira al doPost de ServletLogin
            }*/
        }
    }
}
