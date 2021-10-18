<?php 
    include 'libmenu.php';
    if(isset($_POST['acInv'])){
        session_start();
	    $_SESSION['sesion'] =  ["usuario" => 'anon', "descuento" => 0, "comanda" => []];
        header('Location:pedido.php');
    }
    if(isset($_POST['acSoc'])){
        if((isset($_POST['user']) && !empty($_POST['user'])) && (isset($_POST['pass']) && !empty($_POST['pass']))){
            if(autentica($_POST['user'], $_POST['pass'])){
                session_start();
			    $_SESSION['sesion'] =  ["usuario" => $_POST['user'], "descuento" => dameDcto($_POST['user']), "comanda" => []];
                header('Location:pedido.php');
            }else{
                header('Location:entrada.php?error');
            }
        }else{
            header('Location:entrada.php?error');
        }
    }


?>