<%
    int a = Integer.parseInt(request.getParameter("a"));
    int b = Integer.parseInt(request.getParameter("b"));
    int c = Integer.parseInt(request.getParameter("c"));
    
    double x1 = (-b + Math.sqrt((b*b)-(4*a*c)))/(2*a);
    double x2 = (-b - Math.sqrt((b*b)-(4*a*c)))/(2*a);
    
    out.print("<p style='color: green'><strong>X1: " + x1 + "</strong></p>");
    out.print("<p style='color: green'><strong>X2: " + x2 + "</strong></p>");

%>
