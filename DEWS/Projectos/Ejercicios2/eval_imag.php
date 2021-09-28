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
        if (isset($_POST["cantImg"])) {
            $dir = opendir($carpeta);
            while ($elemento = readdir($dir)) {
                if (!is_dir("../imagenes/" . $elemento)) {
                    
                }
            }
        }
    }


    ?>


</body>

</html>