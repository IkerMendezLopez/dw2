<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Eval Imag</title>
</head>

<body>
    <?php

    function rutas_imag($carpeta)
    {
        $arr = array();
        $cont = 0;
        if (isset($_POST["cantImg"])) {
            $arrext = array("jpg", "png", "tiff");
            $dir = opendir($carpeta);
            while ($elemento = readdir($dir)) {
                if (!is_dir($carpeta . $elemento)) {
                    $ext = explode(".", $elemento);
                    if (in_array($ext[1], $arrext)) {
                        $arr[$cont] = $carpeta ."/". $elemento;
                        $cont++;
                    }
                }
            }
        }
        return $arr;
    }

    function crearTabla()
    {
        $arrRan = array_rand(rutas_imag("../imagenes"), $_POST["cantImg"]);
        $arrImg = array();
        for ($i = 0; $i < count($arrRan); $i++) {
            $arrImg[$i] = rutas_imag("../imagenes")[$arrRan[$i]];
        }
        echo "  <form action=" . $_SERVER['PHP_SELF'] . " method='post'>
                    <table>
                        <tr>";
        for ($i = 0; $i < count($arrImg); $i++) {
            echo "          <td>
                                <img src=". $arrImg[$i] ." alt=".$arrImg[$i]." width='100' height='100'>  
                            </td>
                            <td>
                                <input type='checkbox' name='checkArr[]' value=".substr($arrImg[$i], strrpos($arrImg[$i], "/")+1, strlen($arrImg[$i]))."> 
                                <label for='checkArr[]' value='Me gusta'>Me gusta</label>  
                            </td>
                        </tr>";
        }
        echo "      </table>
                    <input type='submit' name='enviar' value='ENVIAR VALORACIONES'>
                </form>";
    }

    function aniadirLinea(){
        $linea = "";
        for ($i=0; $i < count($_POST['checkArr']) ; $i++) { 
            $linea .= $_POST['checkArr'][$i] . "    ";
        }
        $file = fopen("imagenes.txt", "a");
        fwrite($file, $_SERVER['REMOTE_ADDR'] .": ". $linea . PHP_EOL);
        fclose($file);
    }
    
    if(isset($_POST['enviar'])){
        if(!isset($_POST['checkArr'])){
            echo "  <h3>Sentimos que no le haya gustado ninguna</h3>";
        }else{
            echo "  <h3>Gracias por tu envio</h3>";
            aniadirLinea();
        }
        echo "      <a href='select_cantidad.php'>Volver a la página inicial</a>";
    }else{
        crearTabla();
    }
    ?>


</body>

</html>