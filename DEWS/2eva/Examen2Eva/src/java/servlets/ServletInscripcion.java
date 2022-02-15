/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Actividad;
import beans.Alumno;
import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author dw2
 */
@WebServlet(name = "ServletInscripcion", urlPatterns = {"/ServletInscripcion"})
public class ServletInscripcion extends HttpServlet {
    private Dao bd;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new Dao();
    }
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        HttpSession ses = request.getSession();
        ArrayList<Integer> inscripcionesMarcadas = (ArrayList<Integer>)request.getSession().getAttribute("inscripcionesMarcadas");
        if(inscripcionesMarcadas==null){
            inscripcionesMarcadas = new ArrayList<Integer>();
            inscripcionesMarcadas.add(Integer.parseInt(request.getParameter("id")));
            request.getSession().setAttribute("inscripcionesMarcadas", inscripcionesMarcadas);
        }else{
             if(inscripcionesMarcadas.contains(Integer.parseInt(request.getParameter("id")))){
                    inscripcionesMarcadas.remove((Object)Integer.parseInt(request.getParameter("id")));
            }else{
                inscripcionesMarcadas.add(Integer.parseInt(request.getParameter("id")));
            }
            request.getSession().setAttribute("inscripcionesMarcadas", inscripcionesMarcadas);
            for(Integer i: inscripcionesMarcadas){
                System.out.println(i);
            }
        }
        if(request.getParameter("grabar")!=null){
            for(int i: inscripcionesMarcadas){
                bd.inscribir(i, request.getParameter("alumno"));
            }
            inscripcionesMarcadas.clear();
        }else{
            if(request.getParameter("id")!=null){
                
            }
        }
        response.sendRedirect("alumno.jsp");
    }

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
