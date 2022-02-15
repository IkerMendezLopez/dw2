<?php
    require 'cabecera.php';
    
    
    
    echo "<h2>Ultimas subastas vencidas</h2>";
    echo "<table>";
        echo "<tr>";
            echo "<th>FINALIZO EL</th>";
            echo "<th>CATEGORIA</th>";
            echo "<th>ITEM</th>";
            echo "<th>GANADOR</th>";
        echo "</tr>";
        
        if(!isset($_SESSION['userSelect'])){
            $_SESSION['userSelect'] = "";
        }
    
        
        $subastasVencidas = subastasVencidas($con);
        foreach($subastasVencidas as $subasta){
            
            $idItem = $subasta['id'];
            $fecha = date('d-M-y g:i', strtotime($subasta['fechafin']));
            $idCategoria  = $subasta['id_cat'];
            $nomCategoria = categorias($con, $idCategoria);
            $nombreItem = $subasta['nombre'];
            $ganador = userMaxPuja($con, $idItem);
            
            
            $plus = "";
            $nomGanador = "";
            if($ganador!=0){
                $partes = $ganador[0];
                $partes = explode("/", $partes);
                $nombreGanador = $partes[1];
                $cantidadGanador = $partes[2];
                
                $nomGanador = $nombreGanador;
                $plus = $nomGanador;
                if(isset($_GET['nomSeleccionado'])){
                    $_SESSION ['userSelect'] = $_GET['nomSeleccionado'];
                    $precioProporcional = 1;
                    $plus .= "<table><tr><td>Ganado por $cantidadGanador €</td></tr> <tr><td>$precioProporcional % superior al precio de partida " . $subasta['preciopartida'] . ")</td></tr></table>";
                }
            }
            else{
                $nomGanador = "SIN PUJAS";
            }
                   
            echo "<tr>";
                echo "<td>$fecha</td>";
            
                echo "<td>$nomCategoria[$idCategoria]</td>";
                
                echo "<td>$nombreItem</td>";
               
                
                if($ganador!=0){
                    echo "<form method='post' action='" . $_SERVER['PHP_SELF'] . "?nomSeleccionado=$nombreGanador'>";
                        if($nomGanador==$_SESSION['userSelect']){
                            echo "<td><a href='subastas_vencidas?nomSeleccionado=$nombreGanador'>$plus</a></td>";
                        }
                        else{
                            echo "<td><a href='subastas_vencidas?nomSeleccionado=$nombreGanador'>$nomGanador</a></td>";
                        }
                    echo "</form>";
                }
                else{
                    echo "<td>$nomGanador</td>";
                }
            echo "</tr>";
            
        }
    
    
    
    echo "</table>";
    
    
    
    
    
    
    
    
    
    
    require 'pie.php';