<?php
session_start();
$precioProd = ["prod1" => 10, "prod2" => 20, "prod3" => 10,"prod4" => 30];

if(!isset($_SESSION['unidades'])){
    $_SESSION['unidades'] = [];
}
if (isset($_POST['aniadir'])) {
        if (isset($_POST['checkProductos']) && count($_POST['checkProductos']) > 0) {
            foreach ($_POST['checkProductos'] as $value) {
                $cant = intval($_POST[$value]);
                if (!isset($_SESSION['unidades'][$value])){
                    $_SESSION['unidades'][$value] = 0;
                }else{
                    $_SESSION['unidades'][$value] += $cant;
                }
            }
        }
} else if (isset($_POST['formalizar'])) {
    
} else {
    session_unset();
}
    
?>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Carro</title>
    <style type="text/css">
        body{ 
            margin:0 auto;
            text-align:center;
        }
        table{
            margin: 0 auto;
            margin-bottom: 20px;
        }
        td, th{
            padding-right: 15px;
        }
        caption{
            font-size: 32;
            font-weight: bold;
            margin-bottom: 20px;
        }
    </style>
</head>

<body>
    <div>
        <div>
            <div>
                <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="POST">
                    <table>
                        <caption>PEDIDO</caption>
                        <tr>
                            <td></td>
                            <th>PRODUCTO</th>
                            <th>PRECIO</th>
                            <th>ELIJA CANTIDAD</th>
                            <th>PEDIDO ACTUAL</th>
                        </tr>
                        <?php 
                            foreach ($precioProd as $key => $value) {
                                echo "<tr>";
                                    echo "<td><input type='checkbox' name='checkProductos[]' id=". $key." value=". $key.">";
                                    echo "<td>" . $key . "</td>";
                                    echo "<td>" . $value . " €</td>";
                                    echo "<td><select name='".$key."'>";
                                for ($i = 0; $i <= 10; $i++){
                                    echo "<option value='$i'>$i</option>";
                                }
                                echo "</select></td>";
                                if(isset($_SESSION['unidades'][$key])){
                                    $unidades = $_SESSION['unidades'][$key];
                                }else{
                                    $unidades = 0; 
                                } 
                                    echo "<td>$unidades Unidades pedidas</td>";
                                echo "</tr>";
                            }
                            
                        ?>
                    </table>
                    <input type="submit" name="aniadir" value="Añadir Unidades">
                    <input type="submit" name="formalizar" value="Formalizar Pedido">
                    <input type="submit" name="vaciar" value="Vaciar Carro">
                </form>
            </div>
        </div>
    </div>
</body>
</html>