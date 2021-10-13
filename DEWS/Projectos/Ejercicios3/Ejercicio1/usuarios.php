<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Usuarios</title>
</head>

<body>
    <?php
    session_start();

    function validar($nom)
    {
        foreach (str_split($nom) as $l) {
            if (ctype_digit($l)) {
                return false;
            }
        }
        return true;
    }

    $err = "";
    if (isset($_GET['borrar'])) {
        session_destroy();
    } else {
        if (isset($_POST['aniadir'])) {
            if (isset($_POST['nombre']) && !empty($_POST['nombre'])) {
                if (validar($_POST['nombre'])) {
                    if (isset($_SESSION['sesNombres'])) {
                        $_SESSION['sesNombres'][] = $_POST['nombre'];
                    } else {
                        $_SESSION['sesNombres'] =  [$_POST['nombre']];
                    }
                } else {
                    $err = "No has introducido el nombre unicamente con letras y espacios";
                }
            }
        }
    }


    ?>
    <span style="color:red"><?php echo $err; ?></span>
    <form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
        <label for="texto">Escriba algun nombre:</label>
        <input type="text" name="nombre"><br>
        <input type="submit" name="aniadir" value="Añadir">
        <input type="reset" name="borrar" value="Borrar">
    </form>
    <a href='usuarios.php?borrar'>Cerrar Sesion(Se perderan los datos almacenados)</a>
    <?php
    if (!isset($_POST['aniadir']) || !isset($_SESSION['sesNombres'])) {
        echo "<p>Todavia no se han introducido nombres</p>";
    } else {
        if (isset($_POST['nombre'])) {
            echo "<p>Datos introducidos:</p>";
            echo "<ul>";
            for ($i = 0; $i < count($_SESSION['sesNombres']); $i++) {
                echo "<li>" . $_SESSION['sesNombres'][$i] . "</li>";
            }
            echo "</ul>";
        }
    }
    ?>
</body>

</html>