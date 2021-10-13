<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Inicio Sesion</title>
    </head>
    <body>
        <?php
            if(isset($_GET['error'])){
                echo "<p>Login Erroneo</p>";
            }
        ?>
        
        
        <form action="checkLogin.php" method="post">
            Login <input type="text" name="login"/>
            Passwd <input type="text" name="pass"/>
            <input type="radio" name="sexo" value="H"/> Hombre
            <input type="radio" name="sexo" value="M"/> Mujer
            <input type="submit" name="submitLogin" value="ENTRAR"/>
        </form>
    </body>
</html>
