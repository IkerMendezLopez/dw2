/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author dw2
 */
@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {
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
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        //Para evitar problemas con caracteres especiales
        request.setCharacterEncoding("UTF-8");
        if(request.getParameter("alumno") == null && request.getParameter("profesor")==null){
            doGet(request, response);
        }else{
            System.out.println(request.getParameter("profesor"));
            if(request.getParameter("alumno")!=null){
                Alumno al = bd.loginAlumno(request.getParameter("usuario"), request.getParameter("password"));
                if(al.getDni()!=null){
                   
                    request.getSession().setAttribute("nombreAl", al.getNombre()+" "+ al.getApellidos());
                    request.getSession().setAttribute("actividadesAct", bd.actividadesParticipa(al));
                    request.getSession().setAttribute("actividadesNoAct", bd.actividadesNoParticipa(al));
                    request.getRequestDispatcher("alumno.jsp").forward(request, response);
                }else{
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }else if(request.getParameter("profesor")!=null){
                try{
                    Impartidor im = bd.loginImpartidor(Integer.parseInt(request.getParameter("usuario")), request.getParameter("password"));
                    if(im.getId()!=0){
                        request.getRequestDispatcher("impartidor.jsp").forward(request, response);
                    }else{
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }
                }catch(NumberFormatException e){
                    System.err.println("Id invalido");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
        }
    }   
   
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
