/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Reserva;
import dao.GestorBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dw2
 */
@WebServlet(name = "ServletReservas", urlPatterns = {"/ServletReservas"})
public class ServletReservas extends HttpServlet {

   private GestorBD bd;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new GestorBD();
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("charlas", bd.mapaReservas());
        ArrayList<Integer> anulaciones = (ArrayList<Integer>)request.getSession().getAttribute("anulaciones");
        if(request.getParameter("anulada")==null){
            if(request.getParameter("reserva")!=null){
                request.setAttribute("reserva", request.getParameter("reserva"));  
            }
        }else{
            //no se si es necesario
            request.setAttribute("reserva", request.getParameter("reserva"));
            if(anulaciones==null){
               anulaciones = new ArrayList<>();
            }
            anulaciones.add(Integer.parseInt(request.getParameter("anulada")));
            request.setAttribute("anulaciones", anulaciones);
        }
        request.getRequestDispatcher("reservas.jsp").forward(request, response);
        
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
