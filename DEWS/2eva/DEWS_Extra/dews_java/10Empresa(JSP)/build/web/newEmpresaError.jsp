<%
    if(request.getAttribute("error")!=null){
        
        Exception e = (Exception) request.getAttribute("error");
        if(request.getAttribute("error") instanceof NumberFormatException){
            out.print("<p style='color: red'><strong>Los campos deben ser del tipo adecuado: " + e.getMessage() + "</strong></p>");
        }
        else{
            out.print("<p style='color: red'><strong>" + e.getMessage() + "</strong></p>");
        }
        
    }
%>