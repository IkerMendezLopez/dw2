<!DOCTYPE html>
<?php
    require 'funciones.php';
    
    $con = conexion();
    if(!$con){
        die("<br><strong>CONEXION ERRONEA!</strong></br>");
    }
    mysqli_set_charset($con, "utf8");

    session_start();
    session_destroy();
    session_start();
?>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
            $catSeleccionada = "";
            $dibujaTabla = false;
            $error = "";
            $errorPrecio = "";
            $totSeleccionada = false;
            $cursosActualizados = "";
            
            
            if(isset($_POST['submitBajar']) || isset($_POST['submitSubir'])){
                $dibujaTabla = true;
                $catSeleccionada = $_POST['catSeleccionadaOculta'];
                
                if(empty($_POST['tema'])){
                        $errorPrecio = "*Debes seleccionar un minimo de un tema!";
                }
                else{
                    $porcentaje = $_POST['selectPorcentaje'];
                    $arrTemas = $_POST['tema'];
                    
                    if(isset($_POST['submitBajar'])){
                        $porcentaje = (100-$porcentaje)/100;
                    }
                    else{
                        $porcentaje = (100+$porcentaje)/100;
                    }
                    
                    $cont = 0;
                    foreach($arrTemas as $tema){
                        $cursosCorrespondientes = cursosCorrespondientes($con, $tema);

                        foreach($cursosCorrespondientes as $curso){
                            $precioFicticio = $curso->precio*$porcentaje;
                            if($precioFicticio>=9 && $precioFicticio<=150){
                                actualizaPrecio($con, $curso->idcurso, $precioFicticio);
                                $cont++;
                            } 
                        }
                    }
                    
                    $cursosActualizados = $cont;
                    $dibujaTabla = false;
                }
            }
            
            

            
            if(isset($_POST['submitVerCursos'])){
                if(empty($_POST['categoria'])){
                    $error = "*Seleccione una opcion porfavor!";
                }
                else{
                    $catSeleccionada = $_POST['categoria'];
                    $dibujaTabla = true;
                    
                    if($catSeleccionada=='TODAS LAS CATEGORIAS'){
                        $totSeleccionada = true;
                    }
                }
            }
        
            $categorias = categoriasTemas($con);
            echo "<form method='post' action='" . $_SERVER['PHP_SELF'] ."'>";
                echo "<p style='color: red'><strong>$error</strong></p>";
                echo "<h3>Elija categoria de cursos</h3>";
            
                foreach($categorias as $categoria){
                    $chekea = "";
                    if(!$totSeleccionada && $categoria->categoria==$catSeleccionada){
                        $chekea = "checked";
                    }
                    
                    echo "<input type='radio' name='categoria' value='$categoria->categoria' $chekea/>$categoria->categoria<br>";
                }
                
                $chekea = "";
                if($totSeleccionada){
                    $chekea = "checked";
                }
                echo "<input type='radio' name='categoria' value='TODAS LAS CATEGORIAS' $chekea/>TODAS LAS CATEGORIAS<br>";
                
                echo "<input type='submit' name='submitVerCursos' value='Ver cursos'/>";
            echo "</form>";
            
            
            
            
            
            if($dibujaTabla){
                if($totSeleccionada){
                    $temas = temas($con);
                }
                else{
                    $temas = temas($con, $catSeleccionada);
                }
                
                echo "<form method='post' action='" . $_SERVER['PHP_SELF'] ."'>";
                    echo "<h3>Imparticiones de cursos de cateogorias " . strtoupper($catSeleccionada) . "</h3>";
                    echo "<p style='color: red'><strong>$errorPrecio</strong></p>";
                    
                    echo "<table>";
                        echo "<tr>";
                            echo "<th>SELECCIONAR</th>";
                            echo "<th>TEMA</th>";
                            echo "<th>CANTIDAD DE CURSOS</th>";
                        echo "</tr>";

                        foreach($temas as $tema){
                            echo "<tr>";
                                echo "<td><input type='checkbox' name='tema[]' value='$tema->IDTEMA'/></td>";
                                echo "<td>" . $tema->TEMA . "</td>";
                                echo "<td>" . cantCursos($con, $tema->IDTEMA) . " cursos</td>";
                            echo "</tr>";
                        }
                    echo "</table>";
                    
                    echo "<input type='submit' name='submitBajar' value='BAJAR PRECIO'/>";
                    
                    echo "<select name='selectPorcentaje'>";
                        for($i=5; $i<=50; $i+=5){
                            echo "<option value=$i>$i%</option>";
                        }
                    echo "</select>";
                    
                    echo "<input type='submit' name='submitSubir' value='SUBIR PRECIO'/>";
                    
                    echo "<input type='hidden' name='catSeleccionadaOculta' value='$catSeleccionada'/>";
                echo "</form>";
            }
            else{
                echo "<p>Se ha cambiado el precio de $cursosActualizados cursos</p>";
            }
        
        
        ?>
    </body>
</html>
