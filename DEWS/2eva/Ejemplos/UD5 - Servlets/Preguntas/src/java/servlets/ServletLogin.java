/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akaitz
 */
public class ServletLogin extends HttpServlet {
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            if(request.getParameter("fail") != null){
                out.println("<p style='color: red'>Contraseña incorrecta. Inténtalo de nuevo.</p>");
            }
            out.println("<h1>Introduzca la contraseña para acceder a la aplicación</h1>");
            out.println("<form action='/Preguntas/ServletRedir' method='post'>");
            out.println("<input type='password' name='clave'>");
            out.println("<input type='submit' name='login' value='Login'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
}
