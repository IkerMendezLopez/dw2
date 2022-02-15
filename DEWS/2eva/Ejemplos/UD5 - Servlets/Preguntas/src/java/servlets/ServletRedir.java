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
public class ServletRedir extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if(request.getParameter("login") != null){
            String login = request.getParameter("clave");
            String password = getInitParameter("password");
            
            //Si el password introducido por el usuario es el mismo que se 
            //encuentra en la configuración del Servlet redirige a ServletPregunta
            //Si no redirige a login de nuevo
            if(login.equals(password)){
                response.sendRedirect("/Preguntas/ServletPregunta");
            }else{
                response.sendRedirect("/Preguntas/ServletLogin?fail");
            }
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        //A esta página solo se puede acceder desde el formulario del ServletLogin
        response.sendRedirect("/Preguntas/ServletLogin"); 
    }

}
