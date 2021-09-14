<!DOCTYPE html>

    <head>
        <title>TITULO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    
        <?php
        echo date_format(new DateTime(), 'jS, F Y, l');
        $fecha_actual = date("Y-m-d");
        $s = strtotime('2021/12/31') - strtotime($fecha_actual);
        $d = intval($s / (60 * 60 * 24));
        echo "<p>Faltan " . $d . " dias para acabar el año</p>";
        $arr = ["Hola", "buenos", "dias"];
        echo "<p>" . $arr[0] . " " . $arr[1] . " " . $arr[2] . "</p>";
        $enies = "ñoño";
        $sinenies = str_replace("ñ", "gn", $enies);
        echo "<p>Cadena sin Ñ: " . $sinenies . "</p>";

        function arrAleatorio($n, $lim1, $lim2): array {
            $arrAl = [];
            for ($i = 0; $i < $n; $i++) {
                $arrAl[$i] = rand($lim1, $lim2);
            }
            return $arrAl;
        }

        echo "<p> Array aleatorio entre 10 y 30 con 5 posiciones: </p>";
        echo "<ul>";
        foreach (arrAleatorio(5, 10, 30) as $i) {
            echo "<li>" . $i . "</li>";
        }
        echo "</ul>";
        define("CIFRADO", ["A" => "20", "H" => "9R", "M" => "abcd"]);

        function cadenaCifrada($cadena): string {
            $chopeadita = str_split($cadena);
            $cad = "";
            for ($index = 0; $index < count($chopeadita); $index++) {
                if (!isset(CIFRADO[$chopeadita[$index]])){
                    $cad.=$chopeadita[$index];
                }else{
                    $cad .= CIFRADO[$chopeadita[$index]];
                }
            }
            return $cad;
        }

        echo "Cadena cifrada: ". cadenaCifrada("HOLA AMO");
        ?>

    
