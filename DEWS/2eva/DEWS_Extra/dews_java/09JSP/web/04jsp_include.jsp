<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Include Accion</title>
    </head>
    <body>
        
        <%
            if(request.getParameter("submitSolucion")!=null){
                
                try{
                    int a =Integer.parseInt(request.getParameter("a"));
                    int b =Integer.parseInt(request.getParameter("b"));
                    int c =Integer.parseInt(request.getParameter("c"));

                    if(a==0){
        %>
                        <jsp:include page="04errores.jsp" >
                            <jsp:param name="error" value="No es una ecuacion de 2º grado!"/>
                        </jsp:include>
        <%
                    }
                    else if (b*b -4*a*c < 0){
        %>          
                        <jsp:include page="04errores.jsp" >
                            <jsp:param name="error" value="La solucion son 2 Nº imaginarios!"/>
                        </jsp:include>
        <%
                    }
                    else{
        %>
                        <jsp:include page="04soluciones.jsp" />        
        <%
                    }
                }
                catch(NumberFormatException e){
        %>
                    <jsp:include page="04errores.jsp" >
                        <jsp:param name="error" value="Introduzca un valor de formato numerico!"/>
                    </jsp:include>
        <%
                }
            }
        %>

        
        <form method="post" action="<%= request.getRequestURI() %>">
            <input type="text" name="a" size="1">x <sup>2</sup> + <input type="text" name="b" size="1">x + <input type="text" name="c" size="1"> = 0
            <input type="submit" name="submitSolucion" value="solucion">
        </form>
        
    </body>
</html>
