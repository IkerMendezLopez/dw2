package servlets;

import beans.Actividad;
import beans.Alumno;
import dao.Dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletInscripcion extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        
        if(request.getParameter("id")!=null){
            Integer id = Integer.parseInt(request.getParameter("id"));
            System.out.println(id);
            
            if(sesion.getAttribute("actividadesApuntar")==null){
                ArrayList<Integer> actividades = new ArrayList<>();
                sesion.setAttribute("actividadesApuntar", actividades);
            }
            ArrayList<Integer> actividades = (ArrayList<Integer>)sesion.getAttribute("actividadesApuntar");
            
            if(request.getParameter("accion").equals("apuntar")){
                actividades.add(id);
            }
            else{
                actividades.remove(id);
            }
            response.sendRedirect("alumno.jsp");
        }
        
        else if(request.getParameter("accion").equals("guardar")){
            Alumno alumno = (Alumno)sesion.getAttribute("alumno");
            ArrayList<Integer> actividades = (ArrayList<Integer>) sesion.getAttribute("actividadesApuntar");
            
            System.out.println("aassad");
            
            Dao.inscribir(alumno, actividades);
            sesion.setAttribute("actividades_actuales", Dao.actividadesParticipa(alumno));
            sesion.setAttribute("actividades_disponibles", Dao.actividadesLibresNoParticipa(alumno));

            sesion.removeAttribute("actividadesApuntar");
            response.sendRedirect("alumno.jsp");
        } 
        else{
            response.sendRedirect("alumno.jsp");
        }
        
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
        

    }

}
