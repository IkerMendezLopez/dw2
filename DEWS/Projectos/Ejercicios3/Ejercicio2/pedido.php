<?php 
session_start();
if (!isset($_SESSION['sesion'])) {
	header('Location: ./entrada.php');
}
if(isset($_GET['resetPlate'])){
	$_SESSION['sesion']['comanda'] = [];
}

if (isset($_GET['tipo']) && isset($_GET['plato'])) {
	$_SESSION['sesion']['comanda'][$_GET['tipo']] = $_GET['plato'];
}?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pedido</title>
</head>
<body>
    <a href= "pedidoplato.php?primero">PRIMER PLATO</a><br><br>
    <a href= "pedidoplato.php?segundo">SEGUNDO PLATO</a><br><br>
    <a href= "pedidoplato.php?postre">POSTRE</a><br><br>
    <a href= "pedidoplato.php?bebida">BEBIDA</a><br><br>
</body>
</html>