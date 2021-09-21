<!DOCTYPE html>
<html>
    <head>
        <title>TITULO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <?php
        $ciudades = ["Vitoria", "Barcelona", "Madrid", "Vigo", "Vitoria", "Barcelona", "Madrid", "Vigo", "Bilbao", "Jerez"];
        $ciu = array_unique($ciudades);
        echo "<ol>";
        foreach ($ciu as $i) {
            echo "<li>" . $i . "</li>";
        }
        echo "</ol>";
        ?>

    </body>
</html>

