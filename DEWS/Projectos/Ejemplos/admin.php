<!DOCTYPE html>
<?php
    session_start();
    
    if(!isset($_SESSION['login'])){
        header("location:index.php");
        exit;
    }
?>



<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php

            if(isset($_GET['guardar'])){
                
                $f = fopen("usuarios.txt", "a");
                
                foreach($_SESSION['nuevos'] as $usuario => $ciclo){
                    fputs($f, $usuario . "\t" . $ciclo . "\r\n");
                }
                
                fclose($f);
                echo "<p>Se han guardado" . count($_SESSION['nuevos']) . "usuarios</p>";
                unset($_SESSION['nuevos']);
            }
            
            
            if(!isset($_SESSION['nuevos'])){
                $_SESSION['nuevos'] = array();
            }
            
            
            if(isset($_GET['eliminar'])){ //Si clickan en el enlace eliminar, eliminamos del array nuevos el usuario correspondiente al enlace seleccionado
                $usuario = $_GET['eliminar'];
                unset($_SESSION['nuevos'] [$usuario]);
            }
            
            
            if(isset($_POST['submitAniadir'])){
                $usuario = $_POST['usuario'];
                $ciclo = $_POST['ciclo'];
                
                if(!array_key_exists($usuario, $_SESSION['nuevos'])){
                    $_SESSION['nuevos'] [$usuario] = $ciclo; //Como añadir a un array asociativo clave+valor a la vez 
                }
            }
        
            
            
            
            echo "<h2>" . $_SESSION['login'] . ", puede añadir usuarios</h2>"; 
        ?>
        
        
        
        
        <form action="<?php echo $_SERVER['PHP_SELF'] ?>" method="post">
            <input type="text" name="usuario"/>
            <select name="ciclo">
                <option value="DW">DW</option>
                <option value="DM">DM</option>
                <option value="SI">SI</option>
                <option value="AF">AF</option>
            </select>
            <input type="submit" name="submitAniadir" value="Añadir"/>
        </form>   
        
        
        
        <?php
            
            if(count($_SESSION['nuevos'])>0){
                echo "<h2>Usuarios a añadir</h2>";
                foreach($_SESSION['nuevos'] as $usuario => $ciclo){
                    echo "<br/>$usuario: $ciclo";
                    
                    $enlace = $_SERVER['PHP_SELF']."?eliminar=$usuario"; //Eliminar contiene el nombre del usuario
                    echo "<a href='$enlace'> Eliminar</a>";  
                }
                
                $enlace = $_SERVER['PHP_SELF']."?guardar";
                echo "<br/><br/><a href='$enlace'>GUARDAR USUARIOS NUEVOS </a";
            }
        ?>
        
    </body>
</html>
