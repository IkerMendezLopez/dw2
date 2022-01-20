package servlets;
import beans.Alumno;
import beans.Aviso;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletAvisos extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("actividad")!=null){
            HashMap<Alumno, java.util.Date> mapa = dao.ClaseDao.mapaAsistenciaActividad(Integer.parseInt(request.getParameter("actividad")));
            request.getSession().setAttribute("mapa", mapa);
        }
        
        request.getRequestDispatcher("impartidor.jsp").forward(request, response);
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("submitAviso")==null)response.sendError(403);
        
        
        String dni = request.getParameter("dni");
        String destino = request.getParameter(request.getParameter("tipo"));
        
        Aviso aviso = new Aviso(dni, destino);
        String ruta = this.getInitParameter("fich_avisos");
        System.err.println("RUTA: "+ruta);       
        /*
        PrintWriter pw = new PrintWriter(new FileWriter(this.getServletContext().getRealPath(ruta)));
        pw.println(aviso.toString());
        pw.close();
        */
        response.sendRedirect("impartidor.jsp?actividad="+request.getParameter("actividad")+"&avisado="+dni);
    }
}
