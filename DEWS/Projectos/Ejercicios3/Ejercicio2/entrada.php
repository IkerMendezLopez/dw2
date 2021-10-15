<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Entrada</title>
</head>
<body>
    <?php 
        if(isset($_GET['error'])){
            echo "<p style='color:red;'>Combinacion errónea de usuario-password</p>";
        }
    ?>
    <p>Si eres SOCIO, introduce tu usuario y password</p>
    <form method="post" action="autenticacion.php">
        <label for="user">USUARIO:</label>
        <input type="text" name="user"><br>
        <label for="password">PASSWORD</label>
        <input type="password" name="pass"><br>
        <input type="submit" name="acSoc" value="Acceso Socio">
        <hr>
        <p>Si no dispones de usuario, entra como invitado</p>
        <input type="submit" name="acInv"  value="Acceso Invitado">
    </form>
</body>
</html>