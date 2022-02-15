<!DOCTYPE html>
<?php
    require 'funciones.php';
    
    $con = conexion();
    if(!$con){
        die("<br><strong>CONEXION ERRONEA!</strong></br>");
    }
    mysqli_set_charset($con, "utf8");

    session_start();
?>

<html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="css/stylesheet.css"/>
        <title>SUBASTAS CIUDAD JARDIN</title>
    </head>
    <body>
        <div id="header">
            <h1>SUBASTAS CIUDAD JARDIN</h1>
        </div>
        
        
        <div id="menu">
            <a href="index.php">Home</a> 
            <?php
            /*
                if(!isset($_SESSION['id'])){
                    echo "<a href='login.php'>Login</a>";
                }
                else{
                    echo "<a href='logout.php'>LogOut</a>";
                    echo "<a href='nuevoitem.php'> Nuevo item</a>";
                }
              */
            ?> 
        </div>
        
        
        <div id="container">
            <div id="bar">
                <ul>
                    <li><a href='subastas_vencidas.php'>SUBASTAS VENCIDAS</a></li>
                    <li><a href='subastas_vigentes.php'>SUBASTAS EN CURSO</a></li>
                </ul>
            </div>
            
            <div id="main">
               <!--aqui ira nuestra pagina-->
             

