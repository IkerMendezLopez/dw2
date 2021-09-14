<!DOCTYPE html>
<html>
    <head>
        <title>TITULO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <?php
        $arrTemp = [25, 23, 12, 15, 17, 18, 20, 13, 24];
        echo "<p>La media de temperatura del mes es : " . round((array_sum($arrTemp) / count($arrTemp)), 0) . " (REDONDEADA)</p>";
        echo "<p>La media de temperatura del mes es : " . floor((array_sum($arrTemp) / count($arrTemp))) . " (TRUNCADA)</p>";
        sort($arrTemp);
        echo "<p> Temperaturas mas bajas: </p>";
        echo "<ol>";
        for ($index = 0; $index < 5; $index++) {
            echo "<li>".$arrTemp[$index]."</li>";
        }
        echo "</ol>";
        rsort($arrTemp);
         echo "<p> Temperaturas mas altas: </p>";
        echo "<ol>";
        for ($index = 0; $index < 5; $index++) {
            echo "<li>".$arrTemp[$index]."</li>";
        }
        echo "</ol>";
        ?>
       
    </body>
</html>

