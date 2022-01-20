package servlets;
import beans.Actividad;
import beans.Alumno;
import beans.Impartidor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletLogin extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        response.sendError(403);/* out.println("<!DOCTYPE html>");out.println("<html>");out.println("<head>");out.println("<title>Servlet ServletLogin</title>");out.println("</head>");out.println("<body>");out.println("<h1>Mario Orozco ha patrocinado este servlet</h1>");out.println("</body>");out.println("</html>");}*/
        }
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        if(request.getParameter("submitAlumno")!=null){
            Alumno a = dao.ClaseDao.loginAlumno(usuario, password);
            if(a!=null){
                ArrayList<Actividad> actividadesSI = dao.ClaseDao.actividadesParticipa(a);
                ArrayList<Actividad> actividadesNO = dao.ClaseDao.actividadesNoParticipa(a);
                request.getSession().setAttribute("actividadesSI", actividadesSI);
                request.getSession().setAttribute("actividadesNO", actividadesNO);
                request.getSession().setAttribute("apuntarse", new HashSet<Integer>());
                request.getSession().setAttribute("alumno", a);
                
                response.sendRedirect("alumno.jsp");
            }else{
                response.sendRedirect("index.jsp");
            }
        }else if(request.getParameter("submitImpartidor")!=null){
            Impartidor i = dao.ClaseDao.loginImpartidor(usuario, password);
            if(i!=null){
                ArrayList<Actividad> actividades = dao.ClaseDao.actividadesImpartidor(i.getId());
                request.getSession().setAttribute("actividades", actividades);
                request.getSession().setAttribute("impartidor", i);
                
                response.sendRedirect("impartidor.jsp");
            }else{
                response.sendRedirect("index.jsp");
            }
        }else{
            response.sendError(403);
        }
    }
}
