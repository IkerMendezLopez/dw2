<!DOCTYPE html>
<?php
    require 'funciones.php';
    
    $con = conexion();
    if(!$con){
        die("<br><strong>CONEXION ERRONEA!</strong></br>");
    }
    mysqli_set_charset($con, "utf8");

    session_start();
    session_destroy();
    session_start();
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
            if(!isset($_SESSION['cursosAct'])){
                
            }
            
            $cursosHoy = cursosDeHoy($con, date('Y/m/d'));
        
            
            
            echo "<form method='post' action='". $_SERVER['PHP_SELF'] ."'>";
                echo "<h2>CURSOS de hoy (" . date('d/m/Y') .")</h2>";
                
                echo "<table>";
                    $cursosHoy = cursosDeHoy($con, date('Y/m/d'));
                    
                    foreach($cursosHoy as $curso){
                        echo "<tr>";
                            echo "<td></td>";
                            echo "<td></td>";
                            echo "<td></td>";
                        echo "</tr>";
                    }
                    



                echo "</table>";
            echo "</form>";
        
        
        ?>
    </body>
</html>
