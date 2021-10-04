<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
</head>
<body>
    <?php
        if(isset($_GET['error'])){
            $error = $_GET['error'];
            echo $error;
        }
    ?>
    <form method="post" action="./validacion.php">
        <table>
            <tr>
                <td><label for="nomuser">Nombre de usuario:</label></td>
                <td><input type="text" name="user"></td>
                <td rowspan="2"><input type="submit" name="entrar" value="Entrar"></td>
            </tr>
            <tr>
                <td><label for="contra">Contraseña:</label></td>
                <td><input type="password" name="contra"></td>
            </tr>
        </table>


    </form>
</body>
</html>