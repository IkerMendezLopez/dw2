<!DOCTYPE html>
<?php
    session_start();
    
    require('bbdd.php');
    $con = conexion();
    
    if(!$con){
        die("<br><strong>CONEXION ERRONEA!</strong></br>");
    }
    
    
    
    if(isset($_GET['verPedido'])){
               
        $numPedidos = numPedidos($con, $_GET['verPedido']);
        $texto = "Sin pedidos";
    }
    
    
    if(isset($_POST['submitImagen'])){
        
        
       
        
        echo $_FILES['archivo']['tmp_name'];
    }
            
            



?>



<html>
    <head>
        <meta charset="UTF-8">
        <title>Clientes</title>
    </head>
    <body>
        <?php
            $linea = "";
            $clientes = clientes($con);
        
            echo "<table>";
                foreach($clientes as $cliente){
                    echo "<form method='post' action='" . $_SERVER['PHP_SELF'] . "'>";
                        echo "<tr>";
                            $linea = $cliente->nombre; //AQUI
                            echo "<td>$cliente->nombre</td>";

                            if(isset($_GET['verPedido']) && $_GET['verPedido']==$cliente->idcliente){
                                echo "<td><a href='clientes.php'>$numPedidos pedidos</a></td>";
                                $linea=$linea . " <a href='clientes.php'>$numPedidos pedidos</a>"; //AQUI
                                
                                $pedidos = pedidos($con, $cliente->idcliente);
                                
                                echo "<td>";
                                    echo "<ul>";
                                        foreach($pedidos as $pedido){
                                            echo "<li><strong>" . $pedido->idpedido . "</strong>,  " .  $pedido->preciototal . "€</li>";
                                        }
                                    echo "</ul>";
                                echo "</td>";
                            }
                            else{
                                $enlace = $_SERVER['PHP_SELF'] . "?verPedido=$cliente->idcliente";
                                echo "<td><a href='$enlace'>VER PEDIDO</a></td>";
                                $linea=$linea . " <a href='$enlace'>VER PEDIDO</a>"; //AQUI
                            }
                            
                            
                            //src='data:image/jpeg;base64," . base64_encode() . "'
                            echo "<td><img height='80' alt='NO IMG'/></td>";
                            echo "<td><input type='file' name='archivo'/></td>";
                            echo "<td><input type='submit' name='submitImagen' value='SUBMIR IMAGEN'/></td>";
                            
                            echo "<td>$linea</td>";
                        echo "</tr>";
                    echo "<form>";
                }
            echo "<table>";
            
        ?>
       
        
        
        
        
    </body>
</html>
