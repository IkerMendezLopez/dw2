/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akaitz
 */
@WebServlet(name = "ServletContador", urlPatterns = {"/ServletContador"})
public class ServletContador extends HttpServlet {

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
        //Declaramos la variable contador de visitas
        int contador = 0;
        
        //Comprobar si existe el contador de visitas en la cookie
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie c: cookies){
                if(c.getName().equals("contadorVisitas")){
                    //Como los valores de las cookies se guardan como strings hay que convertirlos
                    contador = Integer.parseInt(c.getValue());
                }
            }
        }
        
        //Incrementar contador en uno
        contador++;
        //Agregamos el contador a la respusta al navegador, si existe lo sobreescribirá
        Cookie c = new Cookie("contadorVisitas", Integer.toString(contador));
        //La cookie se almacenará en el cliente por 1 hora (3600 seg)
        c.setMaxAge(3600);
        response.addCookie(c);
        
        //Mandamos la respuesta al navegador
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Contador de Visitas</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<p>Contador de visitas de cada cliente: " + contador + "</p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
