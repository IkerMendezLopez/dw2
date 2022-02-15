<?php
    require 'cabecera.php';
    
    
    echo "<table>";
        echo "<tr>";
            echo "<th>ITEM</th>";
            echo "<th>ULTIMA PUJA</th>";
            echo "<th>QUEDAN</th>";
            echo "<th>PUJA FICTICIA</th>";
        echo "</tr>";
        
        
        $pujasVigentes = subastasVigentes($con);
        foreach($pujasVigentes as $subasta){
            
            if(!isset($_SESSION['nuevasPujas'])){
                $_SESSION['nuevasPujas'] = array();
            }
            
            $idItem = $subasta['id'];
            $nombreItem = $subasta['nombre'];
            $precioUltimaPuja = ultimaPuja($con, $idItem);
            $precio = $subasta['preciopartida'];
            if($precioUltimaPuja!=0){
                $precio = $precioUltimaPuja [0]->cantidad;
                $fechaFin = strtotime($precioUltimaPuja [0]->fecha);
                $fechaActual = strtotime(date("Y-m-d"));
            }
            
            
            $errorCant = "";
            $okPuja = true; 
            $precioFin = "";
            if(isset($_POST['submitPuja'])){
                if($_POST['newCant']=="" || !is_numeric($_POST['newCant'])){
                    $errorCant = "Intentelo de nuevo!";
                    $okPuja = false;
                }
                else{
                    if($_POST['newCant']<$_POST['precio']){
                        $okPuja = false;
                        $errorCant = "El precio introducido es menor al precio de la ultima puja";       
                    }
                    else{
                        $precioFin = $_POST['newCant'];
                        $_SESSION ['nuevasPujas'] [$_REQUEST['nomItem']] = $_POST['newCant'];
                    }
                }
            }
            
            
            echo "<form method='post' action='" . $_SERVER['PHP_SELF'] . "?nomItem=$nombreItem&precio=$precio'>";
                echo "<tr>";
                    echo "<td>$nombreItem</td>";
                    echo "<input type='hidden' name='nombre' value='$nombreItem'/>";
                    

                    if($precioUltimaPuja==0){
                        echo "<td>Sin pujas</td>";
                    }
                    else{
                        echo "<td>$precio €</td>";
                        echo "<input type='hidden' name='precio' value='$precio'/>";

                        $tiempoRestante = $fechaFin - $fechaActual;
                        $tiempoRestante = date("d",$tiempoRestante);
                        if($tiempoRestante>31 || $tiempoRestante>30){
                            $tiempoFinal = "";
                            $tiempoFinal = $tiempoRestante . "mes ";
                            $tiempoRestante = date("d",$tiempoRestante);
                            $tiempoFinal .=  $tiempoRestante;
                        }
                        else{
                            echo "<td>$tiempoRestante dias</td>";
                        }

                        
                        echo "<td><input type='text' name='newCant' value='$precioFin'/><input type='submit' name='submitPuja' value='Nueva Puja Ficitia'/>$errorCant</td>";
                    }
                echo "</tr>";
            echo "</form>";
        }
    echo "</table>";
    
    if(count($_SESSION['nuevasPujas'])>0){
        
        echo "<h3>POSIBLES PUJAS FICTICIAS</h3>";
        
        echo "<ul>";
            foreach($_SESSION['nuevasPujas'] as $nombre => $cant){
                echo "<li>$nombre, $cant €</li>";
            }
        echo "</ul>";
    }
    
    
    require 'pie.php';

