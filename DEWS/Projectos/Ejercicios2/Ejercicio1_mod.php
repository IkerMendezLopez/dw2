<!DOCTYPE html>

<head>
    <title>TITULO</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
    <?php 
        $textoAntes = "";
        if(isset($_POST["cesar"]) || isset($_POST["sust"])){
            if($_POST["texto"]==""){
                
            }else{
                $textoAntes = $_POST["texto"];
            }
            if(isset($_POST["cesar"]) && !isset($_POST["botones"])){
                
            }
        }
        
        function ficheros(){
            $dir = opendir("./ficheros");
            while ($elemento = readdir($dir)) {
                if (!is_dir($path . $elemento)) {
                    echo "<option>" . $elemento ."</option>";

                }
            }
        } 
        
    ?>
    <form action="<?php echo $_SERVER['PHP_SELF'];?>" method="post">
        <label for="texto">Texto a cifrar</label>
        <input type="text" name="texto" id="texto" value="<?php echo $textoAntes ?>"><br><br>
        <label>Desplazamiento</label><br>
        <input type="radio" id="rd3" name="botones" value="3">
        <label for="rd3">3</label><br>
        <input type="radio" id="rd5" name="botones" value="5">
        <label for="rd5">5</label><br>
        <input type="radio" id="rd10" name="botones" value="10">
        <label for="rd10">10</label><br>
        <input type="submit" name="cesar" value="CIFRADO CESAR">
        <?php 
            if(isset($_POST["cesar"]) && !isset($_POST["botones"])){
                echo '*Debes indicar un desplazamiento*';
                $error=true;
            }
        ?>
        <br><br>
        <label for="seleccion">Fichero de clave</label>
        <select id="seleccion">
            <?php ficheros() ?>
        </select><br>
        <input type="submit" name="sust" value="CIFRADO POR SUSTITUCION">
    </form>    
</body>
</html>


