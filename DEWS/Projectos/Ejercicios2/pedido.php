<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pedido</title>

    <style>
        caption,
        #total {
            text-align: center;
            background-color: lightgray;
        }
    </style>
</head>

<body>
    <table>
        <caption>ELIGE TU PEDIDO</caption>
        <?php

        if (isset($_POST["aniadir"]) && $_POST["nombre"]!="" && $_POST["precio"]!="") {
            $linea = $_POST["nombre"].";".$_POST["precio"];
            $file = fopen("articulos.txt", "a");
            fwrite($file, "\n");
            fwrite($file,  $linea);
            fclose($file);
            if(isset($_FILES["archivo"])){
                move_uploaded_file($_FILES['archivo']['tmp_name'], './' . $_POST["nombre"] . '.txt');
            }
        }

        $precioF = 0;
        if (isset($_GET["precioTotal"])) {
            $precioF = $_GET["precioTotal"] + $_GET["precioArt"];
        }
        $fp = fopen("articulos.txt", "r");
        while (!feof($fp)) {
            $linea = fgets($fp);
            $arrArt = explode(";", $linea);
            echo <<<HTML
                <tr>
                    <td>$arrArt[0]</td>
                    <td>$arrArt[1]</td>
                    <td><a href="pedido.php?precioArt=$arrArt[1]&precioTotal=$precioF">Añadir unidad</a></td>
                HTML;
                    if(isset($_FILES["archivo"]) && $arrArt[0]==$_POST["nombre"]){
                        echo "<td> <a href=./' ". $_POST["nombre"] . "txt></td>";
                    }
                echo"</tr>";
        }
        echo "  <tr>
                    <td id='total' colspan='3'>TOTAL:" . $precioF . "€</td>
                </tr>";

        fclose($fp);
        ?>

    </table>
    <?php
    if (isset($_GET["precioArt"])) {
    ?>
        <br>
        <form method='post' action="<?php echo $_SERVER['PHP_SELF']; ?>" enctype="multipart/form-data">
            <table>
                <caption>AÑADE ARTICULO</caption>
                <tr>
                    <td>Nombre:</td>
                    <td colspan='2'>Precio(€):</td>
                </tr>
                <tr>
                    <td><input type='text' name='nombre'></td>
                    <td><input type='text' name='precio'></td>
                    <td><input type="submit" name="aniadir" value="AÑADIR"></td>
                </tr>
                <tr>
                    <td colspan='3'><input type="file" name="archivo"></td>
                </tr>
            </table>

        </form>
    <?php
    }
    ?>

</body>

</html>