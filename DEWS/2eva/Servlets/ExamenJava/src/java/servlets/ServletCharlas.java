/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Sala;
import dao.GestorBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
@WebServlet(name = "ServletCharlas", urlPatterns = {"/ServletCharlas"})
public class ServletCharlas extends HttpServlet {

    private GestorBD bd;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        bd = new GestorBD();
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("error", null);
        if(request.getParameter("grabar")==null){ 
            if(request.getParameter("hora").equals("")||request.getParameter("minutos").equals("")||request.getParameter("intervalo").equals("")){
                request.setAttribute("error", "Rellena todos los campos");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }else{
                try{
                    int hora= Integer.parseInt(request.getParameter("hora"));
                    int minutos= Integer.parseInt(request.getParameter("minutos"));
                    int intervalo= Integer.parseInt(request.getParameter("intervalo"));
                    if(hora>23 ||hora<0){
                        request.setAttribute("error", "Hora incorrecta");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }else if(minutos>59 || minutos<0){
                        request.setAttribute("error", "Minutos incorrectos");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }else if(intervalo<0){
                        request.setAttribute("error", "Intervalo incorrecto");
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    }else{
                        int minsTot = hora*60+minutos;
                        //hora maxima las 21
                        int max= 21*60;
                        LinkedHashMap<String[], ArrayList<Sala>> mapa = new LinkedHashMap<>();
                        ArrayList<Sala> salasPrep = bd.salasPreparadas();
                        while (minsTot<=max){
                            mapa.put(pasarHyM(minsTot), salasPrep);
                            minsTot+=intervalo;
                        }
                        request.setAttribute("listaHoras", mapa);
                        request.getRequestDispatcher("charlas.jsp").forward(request, response);
                    }
                }catch(NumberFormatException e){
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    request.setAttribute("error", "Solo se permiten numeros!");
                }
            }
        }else{
            
        }
    }

    public String[] pasarHyM(int minutosTot){
        int horas= minutosTot/60;
        int minutos =minutosTot%60;
        String[] arr = new String[2];
        String h="";
        String m="";
        if(horas<10){
            h=0+"";
        }
        if(minutos<10){
            m=0+"";
        }
        h+=horas;
        m+=minutos;
        return new String[]{h,m};
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
