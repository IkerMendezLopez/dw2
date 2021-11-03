<?php 
include "utils.php";
?>

<h1>Últimas subastas vencidas</h1>
<table>
    <tr>
        <th>FINALIZO EL</th>
        <th>CATEGORIA</th>
        <th>ITEM</th>
        <th>GANADOR</th>
    </tr>
    <?php 
        $subastas = getSubastasVencidas();
        for ($i=0; $i < count($subastas) ; $i++) { 
            echo "<tr>";
            //cambiar formato
            echo "<td>".$subastas[$i]['fechafin']."</td>";
            echo "<td>".getCategoriaByID($subastas[$i]['id_cat'])."</td>";
            echo "<td>".$subastas[$i]['nombre']."</td>";
            //posible error en getUser
            if(getUserUltimaPuja($subastas[$i]['id'])[2]==""){
                echo "<td>SIN PUJAS</td>";
            }else{
                echo "<td><a href=".$_SERVER['PHP_SELF']."?item=".getUserUltimaPuja($subastas[$i]['id'])[0]."&puja=".getUserUltimaPuja($subastas[$i]['id'])[1].">".getUserByID(getUserUltimaPuja($subastas[$i]['id'])[2])."</a><br>";
                if(isset($_GET['item'])){
                    if($_GET['item']==getUserUltimaPuja($subastas[$i]['id'])[0]){
                        echo "<br>Ganado por " . $_GET['puja'] . SITE_COIN. "<br>";
                        $precioPartida= $subastas[$i]['preciopartida'];
                        echo ((((int)$_GET['puja'])*100)/$precioPartida)-100 . "% superior al precio de partida(".$precioPartida. SITE_COIN.")"; 
                    }
                    echo "</td>";
                }else{
                    echo "</td>";
                }
            }

            echo "</tr>";
        }
        
    
    
    
    ?>
</table>


<?php 
include "pie.php";
?>