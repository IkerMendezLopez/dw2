/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Libro;
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
@WebServlet(name = "ServletDevolver", urlPatterns = {"/devoluciones"})
public class ServletDevolver extends HttpServlet {
    private GestorBD bd;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new GestorBD();
    }
   
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
        request.getSession().setAttribute("prestados", bd.librosPrestados());
        request.getSession().setAttribute("dias", bd.librosPrestados());
        ArrayList<Integer> devolucionesMarcadas = (ArrayList<Integer>)request.getSession().getAttribute("devolucionesMarcadas");
        if(request.getParameter("grabar")!=null){
            int numDev =0;
            for(int d: devolucionesMarcadas){
                if(bd.devolverLibro(d)){
                    numDev++;
                }
            }
            devolucionesMarcadas.clear();
            request.getSession().setAttribute("prestados", bd.librosPrestados());
            request.getSession().setAttribute("devueltos", numDev);
        }
        if(request.getParameter("id")!=null){
            if(devolucionesMarcadas !=null){
                if(devolucionesMarcadas.contains(Integer.parseInt(request.getParameter("id")))){
                    devolucionesMarcadas.remove((Object)Integer.parseInt(request.getParameter("id")));
                }else{
                    devolucionesMarcadas.add(Integer.parseInt(request.getParameter("id")));
                }
                request.getSession().setAttribute("devolucionesMarcadas", devolucionesMarcadas);
                for(Integer i: devolucionesMarcadas){
                    System.out.println(i);
                }
            } else {
                devolucionesMarcadas = new ArrayList<Integer>();
                devolucionesMarcadas.add(Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("devolucionesMarcadas", devolucionesMarcadas);

            }
        }
        request.getRequestDispatcher("devoluciones.jsp").forward(request, response);
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
}
