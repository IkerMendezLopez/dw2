<%@page import="java.io.File"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Imagen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%!
    private final static String URL = "img_visor";


    public String tamanioDesglosado(long tamanio){
    
        String[] tamainos = new String[]{"BYTES","KB","MB","GB","TB"};
        int cont = 0;
        String desglose = "";
        long tamainoRestante = tamanio;
        while(tamainoRestante >= 1024){
            desglose = tamainoRestante%1024 + tamainos[cont] + " " + desglose;
            tamainoRestante /= 1024;
            cont++;
        }
        if(tamainoRestante > 0)
            desglose = tamainoRestante + tamainos[cont] +  desglose;
        
        
        return desglose;
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
                    String seleccionado = "";
                    if(request.getParameter("cmbImagenes")!=null && request.getParameter("cmbImagenes").equals(img.getNombre())){
                        seleccionado = "selected";
                    }
                    out.print("<option value='" + img.getNombre() + "' " + seleccionado + ">" + img.getNombre() + "</option>");
                }
            %>
            </select>
            
            <strong>Tamaño:</strong>
            <%
                for(int i=3; i<6; i++){
                    String checkeado = "";
                    if(request.getParameter("pixeles")!=null && request.getParameter("pixeles").equals(i+"00")){
                        checkeado = "checked";
                    }
                    
                    out.print("<input type='radio' name='pixeles' id='pixeles" + i + "' value='" + i + "00' " + checkeado + "><label for='pixeles" + i + "'>" + i + "00px</label>");
                }
            %>
            
            <input type="submit" name="submitVer" value="VER IMAGEN">
        </form>
          
            
            
        <%
            if(mostrarImg){
                String imgSeleccionada = request.getParameter("cmbImagenes");
                Imagen img = new Imagen(imgSeleccionada);
                String ruta = "";
                long tamanio = 0;
                
                ArrayList <Imagen> lstImagenes2 = imagenesDeCarpeta();
                if(lstImagenes.contains(img)){
                    Imagen imgSeleccionada2 =  lstImagenes2.get(lstImagenes2.indexOf(img));
                    ruta = imgSeleccionada2.getRuta();
                    tamanio = imgSeleccionada2.getTamanio();
                }
                
                
                
                String strTamanio = tamanioDesglosado(tamanio);
                out.print("<p><strong>Tamaño        </strong>" + strTamanio + "</p>");
                
                String enlace = URL + "/" + request.getParameter("cmbImagenes") + "." + ruta.substring(ruta.lastIndexOf(".")+1, ruta.length());
                int pixeles = Integer.parseInt(request.getParameter("pixeles"));
                out.print("<img src='" + enlace + "' width='" + pixeles + "' height='" + pixeles + "'/>");
            }
        %>
        
    </body>
</html>
