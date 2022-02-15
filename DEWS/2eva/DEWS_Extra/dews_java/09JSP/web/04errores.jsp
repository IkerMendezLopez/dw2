<%
    if(request.getParameter("error")!=null){
        
        String strA = request.getParameter("a");
        String strB = request.getParameter("b");
        String strC = request.getParameter("c");
        String error = request.getParameter("error");
        
        out.print("<p style='color: red'><strong>La ecuacion "+ strA + "x<sup>2</sup>+" + strB + "x+" + strC +  "=0  " + error + "</strong></p>");
    }

%>
