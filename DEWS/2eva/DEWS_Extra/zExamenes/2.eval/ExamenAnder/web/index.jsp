<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Index</title>
    </head>
    <body>
        <%
            if(request.getParameter("submitEntrar")!=null){
                if((request.getParameter("rad1")!=null) || request.getParameter("rad2")!=null){
                    if(request.getParameter("rad1")!=null){
                        request.getRequestDispatcher("ServletItemsPrecio").forward(request, response);
                    }
                    else{
                        request.getRequestDispatcher("ServletIncidencias").forward(request, response);
                    }
                }
            }
        %>
        
        
        
        <form method="post" action="index.jsp">
            <input type="radio" name="rad1" value="rad1"/> <label> Ver items vendidos (por precio)</label> <br>
            <input type="radio" name="rad2" value="rad2"/> <label> Ver Incidencias de pedidos </label><br>
            
            <input type="submit" name="submitEntrar" value="ENTRAR"/>
        </form>
        
        
    </body>
</html>
