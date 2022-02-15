/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import beans.Correccion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Akaitz
 */
public class ServletPregunta extends HttpServlet {
    
    /**
     * Creates the numbers that will be used in the form
     * @return int[5]
     */
    private int[] nums(){
        Random random = new Random();
        int[] nums = new int[5];
        int max = Integer.parseInt(getInitParameter("upLimit"));
        int min = Integer.parseInt(getInitParameter("downLimit"));
        
        nums[0] = random.nextInt((max - min) + 1) + min;
        nums[1] = random.nextInt((max - min) + 1) + min;
        nums[2] = nums[0] + nums[1];
        nums[3] = nums[2] - 1;
        nums[4] = random.nextInt((max - min) + 1) + min;
       
        return nums;
    }
    
    /**
     * Creates a new form in the reponse
     * @param out 
     */
    private void printForm(PrintWriter out){
        int[] nums = nums();
        out.println("<p> Cuánto es " + nums[0] + " + " + nums[1] + ":</p>");
        out.println("<form action='ServletPregunta' method='post'>");
        out.println("<input type='radio' name='ans' value='" + nums[3] + 
                "'>" + nums[3] + "<br>");
        out.println("<input type='radio' name='ans' value='" + nums[2] + 
                "'>" + nums[2] + "<br>");
        out.println("<input type='radio' name='ans' value='" + nums[4] + 
                "' checked>" + nums[4] + "<br>");
        out.println("<input type='submit' name='guess' value='Enviar'>");
        out.println("<input type='hidden' name='n1' value='" + nums[0] + "'>");
        out.println("<input type='hidden' name='n2' value='" + nums[1] + "'>");
        out.println("</form>");
    }
    
    private void printAnswer(PrintWriter out, Correccion ans){
        String suma = "Su respuesta a la suma <strong>" + ans.getN1() + "+" 
                + ans.getN2() + "</strong> ha sido ";
        if(ans.isGuessed()){
            out.println("<p style='color: green'>" + suma + "CORRECTA</p>");
        }else{
            out.println("<p style='color: red'>" + suma + "INCORRECTA</p>");
        }
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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Pregunta</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Responda a la siguiente pregunta</h1>");
            if(request.getAttribute("correccion") != null){
                Correccion ans = (Correccion) request.getAttribute("correccion");
                printAnswer(out, ans);
            }
            printForm(out);
            out.println("</body>");
            out.println("</html>");
        }
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
        if(request.getParameter("guess") != null){
            int n1 = Integer.parseInt(request.getParameter("n1"));
            int n2 = Integer.parseInt(request.getParameter("n2"));
            int ans = Integer.parseInt(request.getParameter("ans"));
            
            Correccion guess = new Correccion(n1, n2, ans);
            request.setAttribute("correccion", guess);
            //Se reenvia la información al servlet para volver a cargar la página
            doGet(request, response);
        }
        
    }
}