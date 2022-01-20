package servlets;
import beans.Actividad;
import beans.Alumno;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletInscripcion extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession ses = request.getSession();
        
        if(request.getParameter("actividad")!=null){
            if(request.getParameter("apuntarse")!=null){
                ((HashSet<Integer>)ses.getAttribute("apuntarse")).add(Integer.parseInt(request.getParameter("actividad")));
            }
            if(request.getParameter("anular")!=null){
                ((HashSet<Integer>)ses.getAttribute("apuntarse")).remove(Integer.parseInt(request.getParameter("actividad")));
            }
        }
        if(request.getParameter("guardar")!=null){
            HashSet<Integer> acts = ((HashSet<Integer>)ses.getAttribute("apuntarse"));
            Alumno al = (Alumno) request.getSession().getAttribute("alumno");
            for (Integer act : acts) {
                dao.ClaseDao.inscribir(act, al.getDni());
            }
            ArrayList<Actividad> actividadesSI = dao.ClaseDao.actividadesParticipa(al);
            ArrayList<Actividad> actividadesNO = dao.ClaseDao.actividadesNoParticipa(al);
            request.getSession().setAttribute("actividadesSI", actividadesSI);
            request.getSession().setAttribute("actividadesNO", actividadesNO);
        }
        
        response.sendRedirect("alumno.jsp");
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(403);
    }
}
