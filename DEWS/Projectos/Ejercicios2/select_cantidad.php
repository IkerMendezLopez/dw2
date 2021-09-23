<!DOCTYPE html>
<html lang="en">

<head>
    <title>Seleccionar Cantidad IMG</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>
    <?php

    function cuantasImg()
    {
        echo "hola";
        $cont = 0;
        $arrext = array("jpg", "png", "JPG", "PNG");
        $dir = opendir("../imagenes");
        echo "hola";
        while ($elemento = readdir($dir)) {
            echo "entra";
            if (!is_dir($dir . $elemento)) {
                $ext = str_split($elemento, ".");
                if (in_array($ext, $arrext)) {
                    $cont++;
                }
            }
        }
        return $cont;
    }
    ?>
    <form action="eval_imag.php" method="post">
        <label for="cantImg">¿Cuantas imágenes deseas ver?</label>
        <select id="cantImg" name="cantImg">
            <?php
            cuantasImg();
            for ($i = 2; $i < cuantasImg(); $i++) {
                echo "<option> . $i . </option>";
            }
            ?>
        </select><br>
        <input type="submit" name="ver" value="VER IMAGENES">
    </form>
</body>

</html>