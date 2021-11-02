<?php 
include "utils.php";
?>
<h1>Items disponibles</h1>
<table>
    <tr>
        <th>IMAGEN</th>
        <th>ITEM</th>
        <th>PUJAS</th>
        <th>PRECIO</th>
        <th>PUJAS HASTA</th>
    </tr>
    <?php
        if (isset($_GET['id'])) {
            $catsql = "SELECT * FROM item where id=". $_GET['id']." ORDER BY nombre ASC;";
        }else{
            $catsql = "SELECT * FROM item ORDER BY nombre ASC;";
        }
        $catresult = mysqli_query($conn, $catsql);
        while($catrow = mysqli_fetch_assoc($catresult)) {
            echo "<tr>";
            if(count(getImgByID($catrow['id']))==0){
                echo "<td>NO IMAGEN</td>";
            }else{
                $img = getImgByID($catrow['id'])[0];
                echo "<td><img src='./img/".$img."' alt=".$catrow['id']." width='100' height='100'></td>";
            }
            echo "<td>".getItemByID($catrow['id'])[0]['nombre']."</td>";
            echo "<td>".count(getPujasByID($catrow['id']))."</td>";
            if(count(getPujasByID($catrow['id']))!=0){
                echo "<td>".getPujasByID($catrow['id'])[0]['cantidad']."</td>";
            }else{
                echo "<td>".getItemByID($catrow['id'])[0]['preciopartida']."</td>";
            }
            $fecha = strtotime(getItemByID($catrow['id'])[0]['fechafin']);
            echo "<td>".date('d/M/y h.i A',$fecha)."</td>";
        }
        echo "</tr>";
        
    ?>
</table>

<?php 
include "pie.php";
?>