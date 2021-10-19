<?php 
include "libmenu.php";
session_start();
if (!isset($_SESSION['sesion'])) {
	header('Location: ./entrada.php');
}
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>SU PEDIDO</h1>
    <ul>
        <?php 
            $precioTotal = 0;
            foreach ($_SESSION['sesion']['comanda'] as $key => $value) {
                echo "<li>".strtoupper($key).": ".$value." - ".damePrecio($value)."€</li>";
                $precioTotal+= intval(damePrecio($value));
            }
        ?>
    </ul>
    <?php 
        if(isset($_SESSION['sesion']['descuento'])){
            $desc =doubleval("0." .$_SESSION['sesion']['descuento']);
        }
        echo "TOTAL: " . ($precioTotal-($precioTotal*$desc)) . "€<br>";
    ?>
    <a href="pedido.php?nuevo">Realizar otro pedido</a>
</body>
</html>