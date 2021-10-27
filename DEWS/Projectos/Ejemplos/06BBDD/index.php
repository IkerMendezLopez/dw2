<!DOCTYPE html>
<?php
    require 'bbdd.php'; //Como el include
    
    $con = conexion(); //Devuelve true si se ha podido crear la conexion, por lo contrario devuelve false
    
    if(!$con){
        die("<br>Conexion erronea</br>"); //Como un exit pero con mensaje
    }
    
    
    
    if(isset($_POST['submitinsert'])){
        
        $nombre = $_POST['nombre'];
        $precio = $_POST['precio'];
        $tipo = $_POST['tipo'];
        
        insertarAlimentoAhora($con, $nombre, $precio, $tipo);
    }
    
    
    $numAct=0;
    if(isset($_GET['actualizar'])){
        $numAct = actualizarAlimAntiguos($con); //antes de probar el metodo cambiar a mano desde phpMyAdmin la fecha de los productos, ya que si los añadimos desde "index.php" los añade con la fecha de hoy
    }
    
    
    if(isset($_POST['submittipo'])){
        $alimsTipo = alimentosTipo($con, $_POST['tipo']);
    }

?>


<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <h2><a href="<?php echo $_SERVER['PHP_SELF'] . "?actualizar"?>">ACTUALIZAR ALIMENTOS ANTIGUOS</a>
            
            <?php
                if(isset($_GET['actualizar'])){
                    echo "<p>$numAct alimentos actualizados a fecha de hoy</p>";
                }
            ?>
        </h2>
        
        
        
        <h2>AÑADIR NUEVO ALIMENTO</h2>
        <form action="<?php echo $_SERVER['PHP_SELF']?>" method="post">
            Nombre:<input type="text" name="nombre"/>
            Precio: <input type="text" name="precio"/>
            Tipo: 
            <select name="tipo">
                <option value="PRIMERO">PRIMERO</option>
                <option value="SEGUNDO">SEGUNDO</option>
                <option value="POSTRE">POSTRE</option>
            </select>
            
            <input type="submit" name="submitinsert" value="AÑADIR"/>   
        </form>
        
        
        
        <h2>ALIMENTOS</h2>
        <?php
            $alims = alimentos($con);
            //print_r($alims);
            echo "<table>";
                foreach ($alims as $alim){
                    echo "<tr>";
                        echo "<td>" . $alim['id'] . "</td>";
                        echo "<td>" . $alim['nombre'] . "</td>";
                        echo "<td>" . $alim['precio'] . "</td>";
                        echo "<td>" . $alim['tipo'] . "</td>";
                        
                        $fechaEsp = date('d-M-y', strtotime($alim['fecha']));
                        echo "<td>" . $fechaEsp . "</td>";
                    echo "</tr>";
                }
            echo "</table>"; 
        ?>
        
        
        
        <h2>VER ALIMENTOS POR TIPO</h2>
        <?php
            $tipos = tipos($con);
            
            echo "<form action='" . $_SERVER['PHP_SELF'] . "' method='post'>";
                foreach($tipos as $tipo){
                    echo "<input type='radio' name='tipo' value='$tipo'/>$tipo";
                }
                
                echo "<input type='submit' name='submittipo' value='VER ALIMENTOS'/>";
            echo "</form>";
            
            
            if(isset($alimsTipo)){
                echo "<table>";
                foreach ($alimsTipo as $alim){
                    echo "<tr>";
                        echo "<td>" . $alim['id'] . "</td>";
                        echo "<td>" . $alim['nombre'] . "</td>";
                        echo "<td>" . $alim['precio'] . "</td>";
                        echo "<td>" . $alim['tipo'] . "</td>";
                        
                        $fechaEsp = date('d-M-y', strtotime($alim['fecha']));
                        echo "<td>" . $fechaEsp . "</td>";
                    echo "</tr>";
                }
            echo "</table>"; 
            }
        ?>
        
        
    </body>
</html>
