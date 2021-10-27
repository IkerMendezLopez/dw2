<!DOCTYPE html>
<?php
    session_start();
    require 'bbdd.php';
    
    $con = conexion();
    if(!$con){
        die("<br>Conexin erronea</br>");
    }
    
    
    $error="";
    if(isset($_POST['submitregistrar'])){
        $dni = $_POST['dni'];
        $nombre = $_POST['nombre'];
        
        $okRegistro = registrar($con, $dni, $nombre); //devuelve true, false dependiendo de si ha podido registrar al cliente
        if($okRegistro){
            $_SESSION['dni'] = $dni;
            
            header('location:pedido.php');
            exit();
        }
        else{
            $error = "<strong>*DNI duplicado!</strong>";
        }
    }

?>


<html>
    <head>
        <meta charset="UTF-8">
        <title>Registro</title>
    </head>
    <body>
        
        <?php echo "<p>$error</p>"?>
        <form method="post" action="<?php echo $_SERVER['PHP_SELF']?>">
            DNI: <input type="text" name="dni"/>
            Nombre: <input type="text" name="nombre"/>
            <input type="submit" name="submitregistrar" value="Registrar"/>            
        </form>
        
    </body>
</html>
