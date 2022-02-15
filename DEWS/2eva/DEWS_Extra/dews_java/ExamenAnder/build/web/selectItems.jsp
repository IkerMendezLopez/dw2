<%@page import="beans.Item"%>
<%@page import="dao.DaoExam"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>selectItems</title>
    </head>
    <body>
        
        <%
            if(request.getParameter("errorPrecios")!=null){
                out.print("<p style='color: red;'>" + request.getParameter("error") +  "</p>");
            }
            
            
            //double minPrecio = Double.parseDouble(request.getAttribute("minPrecio").toString());
            //double maxPrecio = Double.parseDouble(request.getAttribute("maxPrecio").toString());
        %>
        
        
        <form method="post" action="ServletItemsPrecio">
            <h2>Introduce rango de precios</h2>
            
            <p>entre <input type="text" name="minPrecio" value="<%= request.getAttribute("minPrecio") %>"/> y <input type="text" name="maxPrecio" value="<%= request.getAttribute("maxPrecio") %>"/> € <input type="submit" name="submitVerItems" value="Ver items con ventas"/></p>
        </form>
        
        
        <%
            if(request.getSession().getAttribute("lstItems")!=null){
        %>
                <jsp:include page="tablaitems.jsp"/>
        <%
            }
        %>
        
        
    </body>
</html>
