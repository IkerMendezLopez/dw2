<?php
include "libmenu.php";
session_start();
if (!isset($_SESSION['sesion'])) {
    header('Location: ./entrada.php');
}else if (isset($_GET['tipo'])) {
    $tipo = $_GET['tipo'];
}
?>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PedidoPlato</title>
</head>

<body>

    <form method="get" action="pedido.php">
        <?php
        if (isset($_SESSION['sesion']['comanda'][$_GET['tipo']])) {
            $plato_antes = $_SESSION['sesion']['comanda'][$_GET['tipo']];
            echo "Va a cambiar su eleccion de $plato_antes por:<br>";
        }
        ?>
        <select name="plato" id="plato">
            <?php
            $platos = tipoPlatos($tipo);
            foreach ($platos as $key => $value) {
                $title = $key . ' - ' . $value['precio'] . '€';
                echo "<option value=$key>$title</option>";
            }

            ?>
        </select>
        <input type="hidden" name="tipo" value="<?php echo $tipo ?>">
        <input type="submit" value="<?php echo 'Elegir ' . $tipo ?>">


    </form>
</body>

</html>