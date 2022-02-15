package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class ServletLogin extends HttpServlet {
   
  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession s = request.getSession(); //si la session no existe te la crea
        
        if(request.getParameter("submitLogin")!=null){
            s.setAttribute("nombre", request.getParameter("nombre"));
            response.sendRedirect("Servlet2");
        }
        else{
            response.sendRedirect("index.html");
        }
    }
}
