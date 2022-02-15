<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Imagen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    private final static String URL = "img_visor";


    public String tamanioDesglosado(Imagen imagen){
    
        Imagen img = imagen;

        long mb = img.getTamanio()/1000000;
        long resto = img.getTamanio()%mb;
        //long kb = resto/;
        //resto = resto%kb;
        
        return null;
    }


    private ArrayList <Imagen> imagenesDeCarpeta(){

        ArrayList <Imagen> lstImagenes = new ArrayList<Imagen>();
        
        File f = new File(getServletContext().getRealPath(URL));
        String [] imagenes = f.list();

        for(String imagen : imagenes){
            String ruta = URL + "/" + imagen;
            String nombre = imagen.substring(0, imagen.lastIndexOf("."));
                File fImagen = new File(ruta);
            long tamanio = fImagen.length();
            
            lstImagenes.add(new Imagen(ruta, nombre, tamanio));
        }
        
        return lstImagenes;
    }
    
    

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visor de Imagenes</title>
    </head>
    
    <style>
        form{
            border: 2px solid black;
            width: 502px;
            padding: 2px;
        }
    </style>
    
    <body>
        <%
            boolean mostrarImg = false;
            if(request.getParameter("submitVer")!=null){
                if(request.getParameter("pixeles")!=null){
                    mostrarImg = true;
                }
                else{
                    out.print("<p style='color: red'><strong>*Debes seleccionar un tamño antes de visualizar la imagen!</strong></p>");
                }
            }
        %>
        
        
        <form method="post" action="<%= request.getRequestURI() %>">
            <strong>Imagen:</strong>
            <select name="cmbImagenes">
            <%
                ArrayList <Imagen> lstImagenes = imagenesDeCarpeta();
                
                for(Imagen img : lstImagenes){
                    out.print("<option value='" + img.getNombre() + "'>" + img.getNombre() + "</option>");
                }
            %>
            </select>
            
            <strong>Tamaño:</strong>
            <input type="radio" name="pixeles" value="300">300px
            <input type="radio" name="pixeles" value="400">400px
            <input type="radio" name="pixeles" value="500">500px
            
            <input type="submit" name="submitVer" value="VER IMAGEN">
        </form>
            
            
        <%
            if(mostrarImg){
                String imgSeleccionada = request.getParameter("cmbImagenes");
                Imagen img = new Imagen(imgSeleccionada);
                String ruta = "";
                
                ArrayList <Imagen> lstImagenes2 = imagenesDeCarpeta();
                if(lstImagenes.contains(img)){
                    Imagen imgSeleccionada2 =  lstImagenes2.get(lstImagenes2.indexOf(img));
                    ruta = imgSeleccionada2.getRuta();
                }
                
                
                
                String tamanio = tamanioDesglosado(imgSeleccionada);
                out.print("<p><strong>Tamaño         </strong></p>"); //FALTA EL TAMAÑO
                
                String enlace = URL + "/" + request.getParameter("cmbImagenes") + "." + ruta.substring(ruta.lastIndexOf(".")+1, ruta.length());
                int pixeles = Integer.parseInt(request.getParameter("pixeles"));
                out.print("<img src='" + enlace + "' width='" + pixeles + "' height='" + pixeles + "'/>");
            }
        %>
        
    </body>
</html>
