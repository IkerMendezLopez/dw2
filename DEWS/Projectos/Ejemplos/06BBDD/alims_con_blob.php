<!DOCTYPE html>
<?php
    require 'bbdd.php';
    $con = conexion();
    
    
    if(isset($_POST['submitimagen'])){
        
        $idalimento = $_POST['idalimento'];
        if(is_uploaded_file($_FILES['imagen']['tmp_name'])){
            
            //Pasar a texto el archivo imagen, escapando barras, comillas y caracteres raros
            $blobimg = addslashes(file_get_contents($_FILES['imagen']['tmp_name'])); 
            guardarImagen($con, $idalimento, $blobimg);
        }
        else{
            echo "<p>No se ha podido subir imagen de $idalimento</p>";
        }
    }
?>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        
        <?php
        
            $alims = alimentos3($con);
        
            echo "<table>";
                foreach($alims as $alim){
                    echo "<tr>";
                        echo "<td>$alim->nombre</td>";
                        
                        if($alim->imagen!=""){
                            echo "<td><img height='80' src='data:image/jpeg;base64," . base64_encode($alim->imagen) . "'/></td>";
                        }
                        else{
                            //echo "<p>$alim->nombre no tiene IMG: <a href='#'>SUBIR BLOB</a></p><br>";
                            echo "<form method='post' action='" . $_SERVER['PHP_SELF'] . "' enctype='multipart/form-data'>"; //lo de enctype es para poder subir files
                                echo "<input type='hidden' name='MAX_FILE_SIZE' value='1000000'/>";
                                echo "$alim->nombre no tiene IMG: <input type='file' name='imagen'/>";
                                echo "<input type='hidden' name='idalimento' value='$alim->id'/>";
                                echo "<input type='submit' name='submitimagen' value='Subir IMG'/><br>";
                            echo "</form>";
                        }
                        
                    echo "</tr>";
                }
            echo "</table>";
        ?>
        
        
    </body>
</html>
