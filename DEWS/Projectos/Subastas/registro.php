<?php
include "utils.php";

if (isset($_POST['enviar'])) {
    $errores = [];
    if (isset($_POST['usuario']) && !empty($_POST['usuario'])) {
        if(existsUser($_POST['usuario'])){
            echo "<p style='color:red'>Error, el usuario ". $_POST['usuario']." ya esta registrado</p>";
        }
    }else{
        array_push($errores, "Usuario");
    }
    if (isset($_POST['nomCompleto']) && !empty($_POST['nomCompleto'])) {
    }else{
        array_push($errores, "Nombre Completo");
    }
    if (isset($_POST['pass']) && !empty($_POST['pass'])) {
    }else{
        array_push($errores, "Password");
    }
    if (isset($_POST['pass2']) && !empty($_POST['pass2'])) {
    }else{
        array_push($errores, "Repite Password");
    }
    if (isset($_POST['pass2']) && !empty($_POST['pass2'])) {
    }else{
        array_push($errores, "Email");
    }
    if(count($errores)>0){
        echo "<p style='color:red'>Error, rellena los campos: " . concatenar($errores) . "</p>";
    }
}
?>

<h1>REGISTRO</h1>
<p>Para registarte en <?php echo (SITE_TITLE) ?>, rellena el siguiente formulario </p>
<form method="post" action="<?php echo $_SERVER['PHP_SELF'] ?>">
    <table>
        <tr>
            <td>Usuario</td>
            <td><input type="text" name="usuario" size="20"></td>
        </tr>
        <tr>
            <td>Nombre Completo</td>
            <td><input type="text" name="nomCompleto" size="20"></td>
        </tr>
        <tr>
            <td>Password</td>
            <td><input type="text" name="pass" size="20"></td>
        </tr>
        <tr>
            <td>Repite Password</td>
            <td><input type="text" name="pass2" size="20"></td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="mail" size="20"></td>
        </tr>
    </table><br>
    <input type="submit" value="Registrarse" name="enviar">
</form>

<?php
include "pie.php";
?>