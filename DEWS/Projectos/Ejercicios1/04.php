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
            $arr2 = [];
            foreach ($arr as $value) {
                $img = md5_file($value);
                if(!in_array($img, $arr2)){
                    $arr2[]=$value;
                }
            }
            foreach ($arr2 as $pos){
                if($cont<3){
                    echo "<td> <img src='". $pos ."' alt='Camara' width='100' height='100'></td>";
                   
                }else{
                    echo "</tr>";
                    echo "<tr>";
                    echo "<td> <img src='". $pos ."' alt='Camara' width='100' height='100'></td>";
                    $cont=0;
                }
                 $cont++;
            }
            echo "</tr>";
            echo "</table>";
        }
        
        tabla(["./imagenes/camara.jpg","./imagenes/portatil.jpg","./imagenes/television.jpg","./imagenes/tierra.jpg","./imagenes/tierra.jpg","./imagenes/camara_1.jpg","./imagenes/camara_1_1.jpg","./imagenes/portatil_1.jpg"]);
        
        ?>

    </body>
</html>

