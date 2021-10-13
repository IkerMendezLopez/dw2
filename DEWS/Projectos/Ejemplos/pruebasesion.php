<!DOCTYPE html>
<?php
    session_start();
    
    if(!isset($_SESSION['clicks'])){
        $_SESSION['clicks'] = 0;
    }
    
    if(isset($_GET['click'])){
        $_SESSION['clicks']++;
    }
?>

<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
            echo "<p>" . session_id() . "</p>";
            echo "<p>" . $_SESSION['clicks'] . "</p>";
            echo "<p><a href='pruebasesion.php?click'>Pincha Aqui</a></p>";
        ?>
    </body>
</html>
