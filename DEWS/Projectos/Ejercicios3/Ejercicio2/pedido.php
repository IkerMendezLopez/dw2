<?php 
session_start();
if (!isset($_SESSION['sesion'])) {
	header('Location: ./entrada.php');
}
if(isset($_GET['nuevo'])){
    $_SESSION['sesion']['comanda']=[];
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
    <form method="get" action="finpedido.php">
        <a href= "pedidoplato.php?tipo=primero">PRIMER PLATO</a><br><br>
        <a href= "pedidoplato.php?tipo=segundo">SEGUNDO PLATO</a><br><br>
        <a href= "pedidoplato.php?tipo=postre">POSTRE</a><br><br>
        <a href= "pedidoplato.php?tipo=bebida">BEBIDA</a><br><br>
        <?php 
            echo "<h1>SU ELECCION:</h1>";
            echo "<ul>";
            if(isset($_SESSION['sesion']['comanda']['primero'])){
                echo "<li>Primer Plato: ".$_SESSION['sesion']['comanda']['primero']."</li>";
            }
            if(isset($_SESSION['sesion']['comanda']['segundo'])){
                echo "<li>Segundo Plato: ".$_SESSION['sesion']['comanda']['segundo']."</li>";
            }
            if(isset($_SESSION['sesion']['comanda']['postre'])){
                echo "<li>Postre: ".$_SESSION['sesion']['comanda']['postre']."</li>";
            }
            if(isset($_SESSION['sesion']['comanda']['bebida'])){
                echo "<li>Bebida: ".$_SESSION['sesion']['comanda']['bebida']."</li>";
            }
            echo "</ul>";
            if(count($_SESSION['sesion']['comanda'])>0){
                echo "<input type='submit' value='Finalizar Pedido'>";
            }
        ?>
        
    </form>
</body>
</html>