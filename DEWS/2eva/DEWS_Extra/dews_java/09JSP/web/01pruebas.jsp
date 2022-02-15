<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba Sintaxis</title>
    </head>
    <body>
        
        <%!
            int accesos = 0;
            
            private boolean esBisiesto(int anio){
                if((anio%400==0) || (anio%4==0 && anio%100!=0)){
                    return true;
                }
                
                return false;
            }

            private void verSumatorio(int num, JspWriter out){
                
                int sumatorio = 0;
                try{
                    out.println("Sumatorio de " + num + ": ");
                    
                    for(int i=num; i>0; i--){
                        sumatorio+=i;
                       
                        out.print(i + "+");
                    }

                    out.print("0 = " + sumatorio);
                }
                catch(Exception e){}
            }
        %>
        
        
        <ul>
            <li> Fecha actual <strong><%= new Date() %></strong></li>
            <li> Remote IP <strong><%= request.getRemoteAddr() %></strong></li>
            <li> ID de sesion <strong><%= session.getId() %></strong></li>
            <li>
                Parametro saludo 
                <strong><%= (request.getParameter("saludo")==null)? "sin param" : request.getParameter("saludo") %></strong>
            </li>
            
            <li>
                <% 
                    if(Math.random()<0.5){
                %>
                        <p>Que pases un buen dia!</p>
                <% 
                    }
                    else{
                %>
                        <p>Vete a freir churros!</p>
                
                <% 
                    }
                %>
            </li>
            
            <li>
                <%
                    if(Math.random()<0.001){
                        out.print("<p>Te va a tocar la primitiva! :D</p>");
                    }
                    else{
                        out.print("<p>No te va a tocar la primitiva :C</p>");
                    }
                %>
            </li>
            
            <li>
                <%
                    if(esBisiesto(new Date().getYear())){
                        out.print("<p style='color: green'>Es Bisiesto</p>");
                    }
                    else{
                        out.print("<p style='color: red'>No es Bisiesto</p>");
                    }
                %>
            </li>
            
            <li>
                <% 
                    verSumatorio(7, out);
                %>    
            </li>
                
            <%
                accesos++;
                out.print("<p>Accesos a " + request.getRequestURI() + ":" + accesos + "</p>");
            %>
            
            
        </ul>
    </body>
</html>
