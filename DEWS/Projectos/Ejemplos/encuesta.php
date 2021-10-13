<!DOCTYPE html>
<?php
    session_start();
    
    if(!isset($_SESSION['login'])){
        header('location:index.php');
        exit;
    }
    
    $fondo = "white";
    if(isset($_COOKIE['sexo'])){
        if($_COOKIE['sexo']=='M'){
            $fondo = "pink";
        }
        else{
            $fondo = "yellow";
        }
    }
?>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body bgcolor="<?php echo $fondo?>">
        <?php

            if(!isset($_SESSION['visitados'])){ 
                $_SESSION['visitados'] = array(); //3- Array para guardar dentro los lugares visitados
            }
            
            
            if(isset($_GET['lugar'])){ //2- Variable con el lugar seleccionado

                if(!in_array($_GET['lugar'], $_SESSION['visitados'])) //4- Comprobar si en el array visitados se encuentra el lugar seleccionado
                    $_SESSION['visitados'] [] = $_GET['lugar'];
                
                //print_r($_SESSION['visitados']);
            }
            
            
            
            $lugares=array("London", "Madrid", "Sydney", "Caracas");
        
            echo "<p>Encuesta para " . $_SESSION['login'] . "</p>";
            echo "<p> Pincha los lugares que has visitado</p>";
            
            echo "<ul>";
            foreach($lugares as $lugar){
                $enlace = $_SERVER['PHP_SELF']."?lugar=$lugar"; //1- Variable en la que guardara el enlace del lugar selecionado
                
                if(in_array($lugar, $_SESSION['visitados'])){ //5- Si en el array visitados se encuentra ya el lugar(si ya se ha clickado en el enlace anteriormente), simplemente mostrar el nombre como texto, en vez de como si fuese un enlace
                    echo "<li>$lugar</li>";
                }
                else{
                    echo "<li><a href='$enlace'>$lugar</a></li>";
                }
            }
            echo "</ul>";
            
            
        ?>
    </body>
</html>
