<%-- 
    Document   : index
    Created on : 05-ene-2022, 16:32:21
    Author     : Akaitz
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ecuaciones</title>
    </head>
    <body>
        <%
            if(request.getParameter("resolver") != null){
                if(!request.getParameter("a").equals("") && !request.getParameter("b").equals("") && !request.getParameter("c").equals("")){
                    int a = Integer.parseInt(request.getParameter("a"));
                    int b = Integer.parseInt(request.getParameter("b"));
                    int c = Integer.parseInt(request.getParameter("c"));
                    
                    if(a == 0){
        %>
                        <jsp:include page="/WEB-INF/error.jsp">
                            <jsp:param name="error" value="No es una ecuación de segundo grado"/>
                        </jsp:include>
        <%
                    }else if((b*b - 4*a*c) < 0){
        %>
                        <jsp:include page="/WEB-INF/error.jsp">
                            <jsp:param name="error" value="Soluciones imaginarias"/>
                        </jsp:include>
        <%
                    }else{
                        double x1 = (-b + Math.sqrt(b * b - 4 * a * c) / (2 * a));
                        double x2 = (-b - Math.sqrt(b * b - 4 * a * c) / (2 * a));
        %>
                        <jsp:include page="/WEB-INF/solucion.jsp">
                            <jsp:param name="x1" value="<%= x1 %>"/>
                            <jsp:param name="x2" value="<%= x2 %>"/>
                        </jsp:include>
        <%
                    }
                }else{
        %>
                    <jsp:include page="/WEB-INF/error.jsp">
                        <jsp:param name="error" value="Hay que rellenar todas las casillas"/>
                    </jsp:include>
        <%
                }
            }
        %>
        <h1>Ecuaciones de segundo grado</h1>
        <form method="post" action="<%= request.getRequestURI() %>">
            <input type="text" name="a">x<sup>2</sup> + <input type="text" name="b">x + <input type="text" name="c"> = 0
            <input type="submit" name="resolver" value="Resolver">
        </form>
    </body>
</html>
