<!DOCTYPE html>
<html lang="en">

<head>
    <title>Seleccionar Cantidad IMG</title>
    <meta charset="UTF-8">
    <meta name="viewport"
        content="width=device-width, initial-scale=1.0">
</head>

<body>
    <?php
    define("DIRIMG", "../imagenes");
    function cuantasImg()
    {
        $cont = 0;
        $arrext = array("jpg", "png", "tiff");
        $dir = opendir(DIRIMG);
        while ($elemento = readdir($dir)) {
            if (!is_dir("../imagenes/". $elemento)) {
                $ext = explode(".",$elemento);
                if (in_array($ext[1], $arrext)) {
                    $cont++;
                }
            }
        }
        return $cont;
    }
    ?>
    <form action="eval_imag.php"
        method="post">
        <label for="cantImg">¿Cuantas imágenes deseas ver?</label>
        <select id="cantImg"
            name="cantImg">
            <?php
            cuantasImg();
            for ($i = 2; $i <= cuantasImg(); $i++) {
                echo "<option> $i </option>";
            }
            ?>
        </select><br>
        <input type="submit"
            name="ver"
            value="VER IMAGENES">
    </form>
</body>

</html>