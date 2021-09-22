<!DOCTYPE html>

<head>
    <title>TITULO</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <?php
    $error = false;
    $textoAntes = "";
    $errorCesar = "";
    $errorTexto = "";
    $sel1 = "";
    $sel2 = "";
    $sel3 = "";
    $cadena = "";

    function ficheros() {
        $dir = opendir("./ficheros");
        while ($elemento = readdir($dir)) {
            if (!is_dir($path . $elemento)) {
                echo "<option>" . $elemento . "</option>";
            }
        }
    }

    function cifradoCesar($cadena) {
        $cadenaAux = "";
        for ($index = 0; $index < strlen($cadena); $index++) {
            $cadenaAux .= chr(mb_ord($cadena[$index]) + $_POST["botones"]);
        }
        return $cadenaAux;
    }

    function cifradoSust($cadena) {
        $cadenaAux = "";
        $clave="";
        $fp = fopen("./ficheros/" . $_POST["seleccion"], "r");
        while (!feof($fp)) {
            $clave = fgets($fp);
        }
        fclose($fp);
        for ($index = 0; $index < strlen($cadena); $index++) {
            $cadenaAux.=$clave[mb_ord($cadena[$index])-mb_ord('A')];
        }
        return $cadenaAux;
    }

    if (isset($_POST["cesar"]) || isset($_POST["sust"])) {
        if ($_POST["texto"] == "") {
            $errorTexto = '*Texto Vacio*';
            $error = true;
        } else {
            $textoAntes = $_POST["texto"];
        }
        if (isset($_POST["cesar"])) {
            if (!isset($_POST["botones"])) {
                $errorCesar = '*Debes indicar un desplazamiento*';
                $error = true;
            } else {
                if ($_POST["botones"] == "3") {
                    $sel1 = "checked";
                } else if ($_POST["botones"] == "5") {
                    $sel2 = "checked";
                } else {
                    $sel3 = "checked";
                }
            }
        }
        if (!$error) {
            $cadena = strtoupper($_POST["texto"]);
            if (isset($_POST["sust"])) {
                $cadena = cifradoSust($cadena);
            } else {
                $cadena = cifradoCesar($cadena);
            }
        }
    }
    ?>
    <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="post">
        <label for="texto">Texto a cifrar</label>
        <input type="text" name="texto" id="texto" value="<?php echo $textoAntes ?>">
        <?php echo $errorTexto ?><br><br>
        <label>Desplazamiento</label><br>
        <input type="radio" id="rd3" name="botones" value="3" <?php echo $sel1 ?>>
        <label for="rd3">3</label><br>
        <input type="radio" id="rd5" name="botones" value="5" <?php echo $sel2 ?>>
        <label for="rd5">5</label><br>
        <input type="radio" id="rd10" name="botones" value="10" <?php echo $sel3 ?>>
        <label for="rd10">10</label><br>
        <input type="submit" name="cesar" value="CIFRADO CESAR">
        <?php echo $errorCesar ?>
        <br><br>
        <label for="seleccion">Fichero de clave</label>
        <select id="seleccion" name="seleccion">
            <?php ficheros() ?>
        </select><br>
        <input type="submit" name="sust" value="CIFRADO POR SUSTITUCION">
    </form><h1> 
        <?php echo $cadena ?></h1>
</body>
</html>


