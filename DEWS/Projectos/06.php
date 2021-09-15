<!DOCTYPE html>
<html>
    <head>
        <title>TITULO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            table, td, tr, th{
                border: 1px solid black;
                border-collapse: collapse;
            }
            
            
            
        </style>
    </head>
    <body>
        <?php
        Function peliculas($arrDias, $hComienzo, $hFin, $intervalo) {
           $horaC = new DateTime($hComienzo);
           $horaF = new DateTime($hFin);
           $mins = $horaC->diff($horaF)->h * 60;
           $inter = $mins/$intervalo;
           echo "<table><tr>";
           echo "<th></th>";
           foreach ($arrDias as $value) {
               echo "<th>". $value ."</th>";
           }
           echo "</tr><tr>";
           for ($index = 0; $index <= $inter; $index++) {
               echo "<td>". $horaC->format("H:i") ."</td>";
               for ($index1 = 0; $index1 < count($arrDias); $index1++) {
                  echo "<td></td>"; 
               }
               if($index%2!=0){
                   echo "</tr><tr>";
               }else{
                   echo "</tr><tr style=\"background-color:gray;\">";
               }
               $horaC = $horaC->modify("+{$intervalo} minutes");
           }
           echo "</tr></table>";
            
        }
        
        $arr = ["Lunes", "Martes", "Miercoles", "Jueves", "Viernes"];
        peliculas($arr, "8:00", "15:00", 45);
        ?>

    </body>
</html>

