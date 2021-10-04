<?php
    function userPassMap(){
        $map = array();
        $fp = fopen("usuarios.txt", "r");
        while (!feof($fp)) {
            $linea = fgets($fp);
            $arrArt = explode("%", $linea);
            $map[trim($arrArt[0])]= trim($arrArt[1]);
        }
        return $map;
    }
    function validar($map){
        $error="";
        if(!key_exists($_POST["nombre"],$map)){
            $url = "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";
            $url = str_replace("validacion.php", "alta.php", $url);
            header('Location: ' . $url);
        }else{
            if($map[$_POST["nombre"]]!=$_POST["contra"]){
                $error= "?error=CONTRASEÑA ERRONEA PARA " . $_POST["nombre"];
                $url = "http://$_SERVER[HTTP_HOST]$_SERVER[REQUEST_URI]";
			    $url = str_replace("validacion.php", "login.php", $url);
			    $url .= $error;
			    header('Location: ' . $url);
            }
        }
        
    }
    $user = $_POST["nombre"];
    $pass = $_POST["contra"];
    $map = userPassMap();
    validar($map);

?>