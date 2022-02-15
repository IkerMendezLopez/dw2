package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ServletConversor extends HttpServlet {

    private final static String [] MONEDA =  new String [] {"$", "Yenes" , "Libras"};
    
    
    private static float convertir(float cantidad, String moneda){
    
        switch(moneda){
            case "$" : cantidad *= 1.19;
                break;
            case "Yenes" : cantidad *= 124.18;
                break;
            case "Libras" : cantidad *= 0.89;
                break;
        }
        
        
        return cantidad;
    }            
            
    private void dibujarForm(PrintWriter out, HttpServletRequest request){
        
        String cantidad = "";
        if(request.getParameter("cantidad")!=null){
            cantidad = request.getParameter("cantidad");
        }
        
        String monedaRepoblada = "";
        if(request.getParameter("moneda")!=null){
            monedaRepoblada = request.getParameter("moneda");
        }
                
        
        out.print("<form method='post' action='" + request.getRequestURI() + "'>");
            out.print("<h2>Conversor de Divisas</h2>");
            
            out.print("<label>Introduce cantidad </label>");
            out.print("<input type='text' name='cantidad' value='" + cantidad + "'/><br><br>");
            
            out.print("<label>Introduce moneda </label>");
            out.print("<select name='moneda'>");
                for(String moneda :  MONEDA){
                    
                    String selected = "";
                    if(moneda.equals(monedaRepoblada)){
                        selected = "selected";
                    }
                    
                    out.print("<option value='" + moneda + "' " + selected + ">" + moneda + "</option>");
                }
            out.print("</select><br><br>");
            
            out.print("<input type='submit' name='submitConvertir' value='CONVERTIR'/>");
        out.print("</form>");
    }
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletConversor</title>");            
            out.println("</head>");
            out.println("<body>");
            
            dibujarForm(out, request);

            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        boolean convertido = false;
        float conversion = 0;
        String divisa = "";
        float cantidad = 0;
        if(request.getParameter("submitConvertir")!=null){
            
            try{
                cantidad = Float.parseFloat(request.getParameter("cantidad"));
                divisa = request.getParameter("moneda");
                
                conversion = convertir(cantidad, divisa);
                convertido = true;
                
                if(divisa.equals("Libras")){
                    divisa = "£";
                }
                else{
                    if(divisa.equals("Yenes")){
                        divisa = "¥";
                    }
                }
            }
            catch(NumberFormatException e){
                e.printStackTrace();
            }
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ServletConversor</title>");            
            out.println("</head>");
            out.println("<body>");
            
            dibujarForm(out, request);
            
            if(convertido){
                out.print("<p style='color: green'><strong>" + cantidad + " € = " + conversion + " " + divisa + "</strong></p>");
            }
            else{
                out.print("<p style='color: red'><strong>*Conversion no realizada!</strong></p>");
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
