<!DOCTYPE html>
<html>
    <head>
        <title>TITULO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <?php
        
        function tabla($arr){
            $cont=0;
            echo "<table>";
            echo "<tr>";
            foreach (array_unique($arr) as $pos){
                if($cont<3){
                    echo "<td> <img src='". $pos ."' alt='Camara' width='100' height='100'></td>";
                }else{
                    echo "</tr>";
                    echo "<tr>";
                    $cont=0;
                }
                $cont++;
            }
            echo "</tr>";
            echo "</table>";
            echo "<img src='".$arr[0]."' alt='Camara' width='100' height='100'>";
        }
        
        tabla(["./imagenes/camara.jpg"],"./imagenes/portatil.jpg","./imagenes/television.jpg","./imagenes/tierra.jpg");
        
        ?>

    </body>
</html>

