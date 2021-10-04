<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CHARLA</title>
</head>

<body>
    <form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
        <textarea></textarea><br>
        <label for="user">Usuario:
            <?php
            if (isset($_GET['user'])) {
                echo $_GET['user'];
            } else {
                echo $_POST['user'];
            }
            ?>
        </label>
        <input type="hidden" name="user" value="<?php
                                                if (isset($_GET['user'])) {
                                                    echo $_GET['user'];
                                                } else {
                                                    echo $_POST['user'];
                                                }
                                                ?>"><br>
        <label for="msg">Mensaje:</label>
        <input type="text" name="msg"><br>
        <input type="submit" name="enviar" value="Enviar">
    </form>
</body>

</html>