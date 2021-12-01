/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dw2
 */
public class ServletConversor extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String opCel = request.getParameter("submitCel");
        String opFah = request.getParameter("submitFah");
        if (opCel != null || opFah != null) {

            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><title>Conversion</title></head>");
                out.println("<body>");
                out.println("<h1>Resultado</h1>");
                String opcion = "";
                if (opCel != null) {
                    opcion = "cel";
                } else {
                    opcion = "fah";
                }
                dibujarResultado(out, request, opcion);
                out.println("</body>");
                out.println("</html>");
            }

        }
    }

    private void dibujarResultado(PrintWriter out, HttpServletRequest request, String opc) {
        try {
            String txt = "";
            double conversion;
            String tipo = "";
            String otro = "";
            if (opc == "cel") {
                txt = request.getParameter("celTxt");
                conversion = Double.parseDouble(txt) * 9 / 5 + 32;
                tipo = "Celsius";
                otro = "Fahrenheit";
            } else {
                txt = request.getParameter("fahTxt");
                conversion = (Double.parseDouble(txt) - 32) * 5 / 9;
                tipo = "Fahrenheit";
                otro = "Celsius";
            }
            out.print("<h3>Resultado de la conversion: </h3>");
            out.print("<p><strong>Valor en " + tipo + ": </strong>" + txt + "</p>");
            out.print("<p><strong>Valor en " + otro + ": </strong>" + conversion + "</p>");
        } catch (NumberFormatException e) {
            out.print("<p style='color:red'><strong>Se ha producido un error, vuelva a introducir los datos</strong></p>");
        }

        out.print("<p><a href='conversorCF.html'>Volver al formulario</a></p>");
    }
}
