package servlets;

import beans.ConversionCF;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletConversor extends HttpServlet {
    
    private HashSet <String> locales = new HashSet <String>();
    
    
    //EJERCICIO 1
    private void dibujarResultado(PrintWriter out, HttpServletRequest request, boolean opcion){
        
        String txtC = request.getParameter("txtC");
        String txtF = request.getParameter("txtF");
        
        if(opcion){
            if(txtC!=""){
                double conversion = Double.parseDouble(txtC) * 9/5 + 32;
                
                out.print("<h3>Resultado de la conversion: </h3>");
                
                out.print("<p><strong>Valor en celsius: </strong>" + txtC +"</p>");
                out.print("<p><strong>Valor en fahrenheit: </strong>" + conversion +"</p>");    
            }
            else{
                out.print("<p><strong>ERROR: </strong> debes indicar los grados en Celsius</p>");
            }
            
            out.print("<p><a href='conversorCF.html'>Enlace para volver al formulario</a></p>");
        }
        else{
            if(txtF!=""){
                double conversion = (Double.parseDouble(txtF)-32) * 5/9;
                
                out.print("<h3>Resultado de la conversion: </h3>");
                
                out.print("<p><strong>Valor en celsius: </strong>" + conversion +"</p>");
                out.print("<p><strong>Valor en fahrenheit: </strong>" + txtF +"</p>");
            }
            else{
                out.print("<p><strong>ERROR: </strong> debes indicar los grados en Fahrenheit</p>");
            }
            
            out.print("<p><a href='conversorCF.html'>Enlace para volver al formulario</a></p>");
        }       
    }
    
    
    //EJERCICIO1 : CAMBIO1
    private void dibujarResultado1(PrintWriter out, HttpServletRequest request, boolean opcion){
        
        String txtC = request.getParameter("txtC");
        String txtF = request.getParameter("txtF");

        if(opcion){
            if(txtC!=""){
                double temp = Double.parseDouble(txtC);
                ConversionCF cf = new ConversionCF(temp, 'c');
                
                out.print("<h3>Resultado de la conversion: </h3>");
                
                out.print("<p><strong>Valor en celsius: </strong>" + txtC +"</p>");
                out.print("<p><strong>Valor en fahrenheit: </strong>" + cf.getCelsius() +"</p>");    
            }
            else{
                out.print("<p><strong>ERROR: </strong> debes indicar los grados en Celsius</p>");
            }
            
            out.print("<p><a href='conversorCF.html'>Enlace para volver al formulario</a></p>");
        }
        else{
            if(txtF!=""){
                double temp = Double.parseDouble(txtF);
                ConversionCF cf = new ConversionCF(temp, 'f');
                
                out.print("<h3>Resultado de la conversion: </h3>");
                
                out.print("<p><strong>Valor en celsius: </strong>" + cf.getFahrenheit() +"</p>");
                out.print("<p><strong>Valor en fahrenheit: </strong>" + txtF +"</p>");
            }
            else{
                out.print("<p><strong>ERROR: </strong> debes indicar los grados en Fahrenheit</p>");
            }
            
            out.print("<p><a href='conversorCF.html'>Enlace para volver al formulario</a></p>");
        }       
    }
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String op1 = request.getParameter("submitCelFa");
        String op2 = request.getParameter("submitFaCel");
        
        if(op1!=null || op2!=null){
            response.setContentType("text/html;charset=UTF-8");
            
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Resultado Conversion</title>");            
                out.println("</head>");
                out.println("<body>");

                /*
                //EJERCICIO1
                if(op1!=null){
                    dibujarResultado(out, request, true);
                }
                else{
                    dibujarResultado(out, request, false);
                }*/
                
                /*
                //EJERCICIO1 : CAMBIO1
                if(op1!=null){
                    dibujarResultado1(out, request, true);
                }
                else{
                    dibujarResultado1(out, request, false);
                }*/
                
                
                //EJERCICIO1 : CAMBIO2
                locales.add(request.getLocale().toString());
                
                if(op1!=null){
                    dibujarResultado1(out, request, true);
                }
                else{
                    dibujarResultado1(out, request, false);
                }
                
                out.print("<p>Se han establecido conexiones desde " + locales.size() + " distintos locale´s</p>");


                out.println("</body>");
                out.println("</html>");
            }
        }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        
    }
    
}
