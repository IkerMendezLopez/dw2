package servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class EscribeApuesta extends HttpServlet {

    @Override
    public void init() throws ServletException {
        
        try{
            ArrayList <String> lstEquipos = new ArrayList<String>();
            
            String nomFich = this.getServletContext().getInitParameter("fichEquipos");
            String url = this.getServletContext().getRealPath(nomFich);
            
            BufferedReader br  = new BufferedReader(new FileReader(url));
            String linea = br.readLine();
            while(linea!=null){
                lstEquipos.add(linea);
                
                linea = br.readLine();
            }
            
            br.close();
            
            this.getServletContext().setAttribute("equipos", lstEquipos);
        }
        catch (IOException ex) {
        }
    }
    
    
    private void dibujarTabla(HttpServletRequest request, HttpServletResponse response, PrintWriter out){
        
        HttpSession s = request.getSession();
        if(s.getAttribute("nombre")==null){
            s.setAttribute("nombre", request.getParameter("nombre"));
        }
        
        
        ArrayList <String> equipos = (ArrayList <String>) this.getServletContext().getAttribute("equipos");
        String [] opciones = {" ","1", "X", "2"};
        
        out.print("<form method='post' action='ProcesaApuesta'>");
            out.print("<table>");
                out.print("<h3>" + s.getAttribute("nombre") + ", escribe tu apuesta: </h3>");

                for(int i=0; i<equipos.size(); i++){
                    out.print("<tr>");
                        String [] pares = equipos.get(i).split("/");
                        String equipo1 = pares[0];
                        String equipo2 = pares[1];

                        out.print("<td>" + equipo1 + "</td>");
                        out.print("<td>" + equipo2 + "</td>");
                        
                        out.print("<td>");
                            out.print("<select name='comboOp'>");
                               
                                for(int j=0; j<opciones.length; j++){

                                    if(s.getAttribute("apuestasRealizadas")!=null){
                                        String [] apuestasRealizadas = (String[]) s.getAttribute("apuestasRealizadas"); //Tambien se podria hacer con el array de los valores que se hayan enviado ya que al volvera  este servlet con el forward es accesible a ellos

                                        if(apuestasRealizadas[i].equals(opciones[j])){
                                            out.print("<option value='" + opciones[j] +"' selected>" + opciones[j] + "</option>");
                                        }
                                        else{
                                            out.print("<option value='" + opciones[j] +"'>" + opciones[j] + "</option>");
                                        }
                                    }
                                    else{
                                        if(j==0){
                                            out.print("<option value='" + opciones[j] +"' selected>" + opciones[j] + "</option>");
                                        }
                                        else{
                                            out.print("<option value='" + opciones[j] +"'>" + opciones[j] + "</option>");
                                        }
                                    }
                                }
                                
                            out.print("</select>");
                        out.print("</td>");
                        
                        
                        if(s.getAttribute("apuestasRealizadas")!=null){
                            String [] apuestasRealizadas = (String[]) s.getAttribute("apuestasRealizadas");
                            
                            if(apuestasRealizadas[i].equals(" ")){
                                out.print("<td><span style='color: red'><strong>*Elije un valor para dicha apuesta!<strong></span></td>");
                            }
                        }
                    out.print("</tr>");
                }

                out.print("<tr><td><input type='submit' name='submitGuardar' value='GUARDAR APUESTA'/></td></tr>");

            out.print("</table>");
        out.print("</form>");
    }
  
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        doPost(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean redirije = false;
        if(request.getParameter("submitEscribir")!=null){
            if(request.getParameter("nombre").equals("")){
                redirije = true;
            }
        }
        else{
            if(request.getParameter("revisar")!=null){
               redirije = false;
            }
            else{
                if(request.getParameter("error")==null){
                    redirije = true;
                }
            }
        }
        
        
        if(redirije){
            response.sendRedirect("Ejercicio3.html");
        }
        else{
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Preguntas</title>");            
                out.println("</head>");
                out.println("<body>");
                
                dibujarTabla(request, response, out);

                out.println("</body>");
                out.println("</html>");
            }
        }
        
        
        
        
        
    }

    


}
