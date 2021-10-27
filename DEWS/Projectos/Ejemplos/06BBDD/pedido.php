<!DOCTYPE html>
<?php
    session_start();
    require 'bbdd.php';
    $con = conexion();
    
    
    if(!isset($_SESSION['dni'])){
        header('location:registro.php');
        exit();
    }
    
    
    if(!isset($_SESSION['pedido'])){ //1- Comprueba si el array de sesion pedidos existe y si no existe lo crea
        $_SESSION['pedido'] = array();
    }
        
    
    if(isset($_POST['submitpedir1'])){
        $idalimento = $_POST['idalimento'];
        
        if(array_key_exists($idalimento, $_SESSION['pedido'])){ //2-Comprueba si existe la clave de dicho alimento en el array de la sesion de pedidos
            $_SESSION['pedido'][$idalimento]++; //3- En caso de existir incrementa el id
        }
        else{
            $_SESSION['pedido'][$idalimento]=1; //4- En caso de no existir pone a 1 el contador de unidades del idalimento correspondiente
        }
    }
    
    
    if(isset($_GET['formalizar'])){
        
        $total=0;
        foreach($_SESSION['pedido'] as $idalimento => $ctd){
            $total+= $ctd * precioDe($con, $idalimento);
        }
     
        $idpedido = nuevoPedido($con, $_SESSION['dni'], $total);

        
        //Mostrar clave del nuevo pedido generado y vaciar el pedido en sesion
        echo "<p>Generado pedido de id $idpedido</p>";
        $_SESSION['pedido'] = [];
    }
    
    

?>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Pedido</title>
    </head>
    
    <body>
        <?php
            $alims = alimentos2($con);
            
            echo "<table>";
                foreach($alims as $alim){
                    echo "<form action='" . $_SERVER['PHP_SELF'] . "' method='post'>";
                        echo "<tr>";
                            echo "<td>" . $alim->nombre . "</td>";
                            echo "<td>" . $alim->precio . "</td>";
                            
                            echo "<input type='hidden' name='idalimento' value='". $alim->id ."'/>";
                            echo "<td><input type='submit' name='submitpedir1' value='Añadir unidad'/></td>";
                            
                            if(array_key_exists($alim->id, $_SESSION['pedido'])){
                                echo "<td>";
                                    echo $_SESSION['pedido'][$alim->id] . " uds"; //5- Muestra el valor(el contador de unides) de la posicion del array correspondiente al id de alimento
                                echo "</td>";
                            }
                        echo "</tr>";
                    echo "</form>";
                }
            echo "</table>";
            
            
            $enlace = $_SERVER['PHP_SELF'] . "?formalizar";
            echo "<a href='$enlace'>FORMALIZAR PEDIDO</a>";
        ?>
        
</body>
</html>
