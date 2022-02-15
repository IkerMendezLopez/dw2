package servlets;

import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import dao.Dao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        if(request.getParameter("usuario").equals("") || request.getParameter("password").equals(""))
            doGet(request, response);
        else{
            String user = request.getParameter("usuario");
            String pass = request.getParameter("password");
            
            if(request.getParameter("loginComoAlumno")!=null){
                Alumno alumno = Dao.loginAlumno(user, pass);
            
                if(alumno==null){
                    doGet(request, response);
                }
                else{
                    HttpSession sesion = request.getSession();
                    ArrayList<Actividad> actividadesApuntado = Dao.actividadesParticipa(alumno);
                    ArrayList<Actividad> actividadesDisponibles = Dao.actividadesLibresNoParticipa(alumno);
                    
                    sesion.setAttribute("alumno", alumno);
                    sesion.setAttribute("actividades_participa", actividadesApuntado);
                    sesion.setAttribute("actividades_disponibles", actividadesDisponibles);

                    response.sendRedirect("alumno.jsp");
                }
            }
            else{
                try{
                    int id = Integer.parseInt(user);
                    Impartidor impartidor = Dao.loginImpartidor(id, pass);
                    
                    if(impartidor==null){
                        doGet(request, response);
                    }
                    else{
                        HttpSession sesion = request.getSession();
                        sesion.setAttribute("impartidor", impartidor);
                        sesion.setAttribute("actividades", Dao.actividadesImpartidor(impartidor));
                        response.sendRedirect("impartidor.jsp");
                    }
                    
                }catch(NumberFormatException e){
                    doGet(request, response);
                }
            }
                
            
        }

    }

}
