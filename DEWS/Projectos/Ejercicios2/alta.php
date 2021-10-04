<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Alta</title>
</head>

<body>
    <?php
    function dibujar(){
        ?>
        <form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
            <table>
                <tr>
                    <td><label for="newuser">Login:</label></td>
                    <td><input type="text" name="newuser"></td>
                </tr>
                <tr>
                    <td><label for="newpass">Password:</label></td>
                    <td><input type="password" name="newpass"></td>
                </tr>
            </table>
            <input type="submit" name="registrar" value="REGISTRAR">

        </form>
    <?php
    }
    function userExists($userName)
    {
        $fp = fopen("usuarios.txt", "r");
        while (!feof($fp)) {
            $linea = fgets($fp);
            $arrArt = explode("%", $linea);
            if ($userName == $arrArt[0]) {
                return true;
            }
        }
        return false;
    }

    if (isset($_POST["registrar"])) {
        if (userExists($_POST["newuser"])) {
            echo "<h3>REGISTRATE</h3>";
            echo "Lo sentimos ya existe el usuario " . $_POST["newuser"];
            dibujar();
        } else {
            $linea = $_POST["newuser"] . "%" . $_POST["newpass"];
            $file = fopen("usuarios.txt", "a");
            fwrite($file, "\n");
            fwrite($file,  $linea);
            fclose($file);
            echo $_POST["newuser"].": Has sido dado de alta";
            echo "<h1><u><a href='./charla.php'>ENTRAR AL CHAT</a></u></h1>";
        }
    } else {
        echo "<h3>REGISTRATE</h3>";
        dibujar();
    }
    ?>
</body>

</html>