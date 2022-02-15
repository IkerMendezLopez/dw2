package servlets;

import beans.Correccion;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletPregunta extends HttpServlet {
    
    
    private int [] numsAzar(){
        
        int limitInf = Integer.parseInt(this.getInitParameter("num1"));
        int limitSup = Integer.parseInt(this.getInitParameter("num2"));
        
        int [] nums = new int [2];
        nums[0] =  limitInf + (int) (Math.random() * limitSup);
        do{
            nums[1] =  limitInf + (int) (Math.random() * limitSup);
        }while(nums[0]==nums[1]);
        
        return nums;
    }
    
    
    private void dibujarForm (PrintWriter out){
        
        int [] nums = numsAzar();
        String pregunta = nums[0] + " + " + nums[1] + "?";
        int rptaOk = nums[0] + nums[1];
        int rptaMal1 = rptaOk +1;
        int rptaMal2 = rptaOk - 1;
        
        
        out.print("<form method='post' action='ServletPregunta'>");
            out.print("<label>" + pregunta + "</label><br><br>");
            
            out.print("<input type='radio' name='rpta' value='" + rptaOk + "' checked/>" + rptaOk + "<br><br>");
            out.print("<input type='radio' name='rpta' value='" + rptaMal1 + "'/>" + rptaMal1 + "<br><br>");
            out.print("<input type='radio' name='rpta' value='" + rptaMal2 + "'/>" + rptaMal2 + "<br><br>");
            
            out.print("<input type='hidden' name='num1' value='" + nums[0] + "'/>");
            out.print("<input type='hidden' name='num2' value='" + nums[1] + "'/>");
        
            out.print("<input type='submit' name='submitResponder' value='RESPONDER'/>");
        
        out.print("</form>");
    }

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            
            out.print("<h2>Preguntas</h2>");
            
            if(request.getAttribute("correccion")!=null){
                
                Correccion c = (Correccion) request.getAttribute("correccion");
                String color;
                String msg;
                
                if(c.isCorrecta()){
                    color = "green";
                    msg = "Enhorabuena has acertado!";
                }
                else{
                    color = "red";
                    msg = " La respuesta es incorrecta!";
                }
                
                out.print("<p style='color:" + color + "'><strong>" + msg + "</strong></p>");
                
            }
            
            dibujarForm(out);
            
            out.println("</body>");
            out.println("</html>");
        
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if(request.getParameter("submitResponder")!=null){
         
            int num1 = Integer.parseInt(request.getParameter("num1"));
            int num2 = Integer.parseInt(request.getParameter("num2"));
            int rpta = Integer.parseInt(request.getParameter("rpta"));
            
            Correccion c;
            if(rpta==(num1+num2)){
                c = new Correccion(true, rpta);
            }
            else{
                c = new Correccion(false, rpta);
            }   
            
            request.setAttribute("correccion", c);
            doGet(request, response); //si llega aqui lo manda al doGet de arriba
        }
    }
}
