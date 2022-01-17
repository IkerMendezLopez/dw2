<%-- 
    Document   : visor_imagenes
    Created on : 11 ene 2022, 10:35:21
    Author     : dw2
--%>

<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Imagen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%! 
    final String CARPETA="images"; 
    
    ArrayList<Imagen> imagenesDeCarpeta(String nomCarpeta){
        ArrayList<Imagen> arl= new ArrayList<Imagen>();
        File carpeta = new File(getServletContext().getRealPath(nomCarpeta));
        String[] arr = carpeta.list();
 
        for (int i = 0; i < arr.length; i++) {
            arl.add(new Imagen(nomCarpeta+"/"+arr[i]));
        }
        return arl;
    }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="visor_imagenes.jsp" method="post">
            <label for="imagen">Imagen:</label>
            <select name="imagenes" id="selectImg">
                <% 
                    ArrayList<Imagen> arl = imagenesDeCarpeta(CARPETA);
                    for(Imagen i : arl){
                        out.print("<option value="+i.getNombre()+">"+i.getNombre()+"</option>");
                    }
                    
                %>
            </select>
            <label for="Tamaño">Tamaño:</label>
            <input type="radio" name="tamanios" value="300"><label for="300">300 px</label>
            <input type="radio" name="tamanios" value="400"><label for="400">400 px</label>
            <input type="radio" name="tamanios" value="500"><label for="500">500 px</label>
            <input type="submit" value="VER IMAGEN">
        </form>
    </body>
</html>
