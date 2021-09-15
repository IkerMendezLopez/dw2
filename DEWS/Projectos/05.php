<!DOCTYPE html>
<html>
    <head>
        <title>TITULO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <?php
        

        Function peliculas($nombrePel) {
            //Inicializar array
            $arr = ["Julen" => ["Charlie y la fabrica de chocolate", "Seven", "El club de la lucha"],
            "Mendez" => ["El Padrino", "Pulp Fiction", "El club de la lucha"],
            "Unai" => ["El Padrino", "Seven", "Ciudad de Dios"]];
            //Numero de favoritos
            echo "<h2>Primera Parte</h2>";
            $cont = 0;
            foreach ($arr as $key => $peli) {
                foreach ($peli as $k => $v) {
                    if ($v == $nombrePel) {
                        $cont++;
                    }
                }
            }
            echo "La pelicula " . $nombrePel . " es la favorita de ". $cont . " personas<br>";
            //Peliculas Random
            echo "<h2>Segunda Parte</h2>";
            $dosPelis="";
            foreach ($arr as $key => $peli) {
                $dosPelis=$peli[rand(0,count($peli)-1)];
                $nRandom = rand(0,count($peli)-1);
                while($dosPelis==$peli[$nRandom]){
                    $nRandom = rand(0,count($peli)-1);
                }
                $dosPelis.=" - ".$peli[$nRandom];
                echo "Dos peliculas favoritas de ". $key . ": " . $dosPelis."<br>";
            }
            
        }
        echo peliculas("Pulp Fiction");
        ?>

    </body>
</html>

