<?php
include "utils.php";
if(!isset($_SESSION['item'])){
    echo("sesion");
    $_SESSION['item']= []; 
    //$_SESSION['item']=["nombre" => '', "precio" => '']; 
}
if(isset($_POST["enviar"]) && $_POST["puja"]!=""){
    echo("enviar");
}
?>

<h1>Subastas Vigentes</h1>
<table>
    <form action="<?php echo $_SERVER['PHP_SELF']; ?>" method="POST">
        <tr>
            <th>ITEM</th>
            <th>ULTIMA PUJA</th>
            <th>QUEDAN</th>
            <th>PUJA FICTICIA</th>
        </tr>
        <?php
        $subastas = getSubastasVigentes();
        for ($i = 0; $i < count($subastas); $i++) {
            echo "<tr>";
            echo "<td>" . $subastas[$i]['nombre'] . "</td>";
            if (getUserUltimaPuja($subastas[$i]['id'])[1] == "") {
                echo "<td>Sin pujas</td>";
            } else {
                echo "<td>" . getUserUltimaPuja($subastas[$i]['id'])[1] . SITE_COIN . "</td>";
            }
            $meses = (int)(mesesYDias($subastas[$i]['fechafin']) / 30);
            $dias = (int)(mesesYDias($subastas[$i]['fechafin']) % 30);
            if ($meses == 0) {
                echo "<td>" . $dias . " dias" . "</td>";
            } else if ($meses == 1) {
                echo "<td>" . $meses . " mes " . $dias . " dias" . "</td>";
            } else {
                echo "<td>" . $meses . " meses " . $dias . " dias" . "</td>";
            }
            echo "<td><input type='text' name='puja'><input type='submit' name='enviar' value='Nueva Puja Ficticia'></td>";
            echo "</tr>";
        }




        ?>
</table>
</form>

<?php
include "pie.php";
?>