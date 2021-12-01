/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dw2
 */
public class ServletFormOpinion extends HttpServlet {

    private ArrayList<String> secciones;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            dibujarForm(out, false);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("nombre").equals("")) {
            try (PrintWriter out = response.getWriter()) {
                dibujarForm(out, true);
            }
        }
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void cargarSecciones(String file) {
        secciones = new ArrayList<String>();
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String linea = br.readLine();
            while (linea != null) {
                secciones.add(linea);
                linea = br.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServletFormOpinion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void dibujarForm(PrintWriter out, boolean error) {
        String required = "<span style='color:red;'>*</span>";
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>FormOpinion</title>");
        out.println("</head>");
        out.println("<body>");
        if(error){
            out.println("<p style='color:red;'>Completa todos los campos obligatorios("+required+")</p>");
        }
        out.println("<h1>Formulario Opinion</h1>");
        out.println("<form method='post' action='ServletFormOpinion'>");
        out.println("<label>"+required+"<b>Nombre:</b></label>");
        out.println("<input type='text' name='nombre'><br><br>");
        out.println("<label>"+required+"<b>Apellidos:</b></label>");
        out.println("<input type='text' name='apellidos'><br><br>");
        out.println("<label>"+required+"<b>Opinion que le a merecido este sitio web</b></label><br>");
        out.println("<input type='radio' value='B' name='opi'>");
        out.println("<label>Buena</label><br>");
        out.println("<input type='radio' value='R' name='opi'>");
        out.println("<label>Regular</label><br>");
        out.println("<input type='radio' value='M' name='opi'>");
        out.println("<label>Mala</label><br><br>");
        out.println("<label>Comentarios</label><br>");
        out.println("<textarea rows='10' cols='40' name='comentarios'></textarea><br><br>");
        out.println("<label><b>Tus secciones favoritas</b></label><br><br>");
        cargarSecciones(getServletContext().getRealPath("secciones.txt"));
        for (String seccion : secciones) {
            out.println("<input type='checkbox' name='secciones[]'>");
            out.println("<label>" + seccion + "</label><br>");
        }
        out.println("<br><input type='submit' value='Enviar Opinion' name='enviar'>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

}
