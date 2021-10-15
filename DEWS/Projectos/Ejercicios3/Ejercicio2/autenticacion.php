<?php 
    include 'libmenu.php';
    if(isset($_POST['acInv'])){
        header('Location:pedido.php');
    }
    if(isset($_POST['acSoc'])){
        if((isset($_POST['user']) && !empty($_POST['user'])) && (isset($_POST['pass']) && !empty($_POST['pass']))){
            if(autentica($_POST['user'], $_POST['pass'])){
                header('Location:pedido.php?' . $_POST['user'] . "?" . dameDcto($_POST['user']));
            }else{
                header('Location:entrada.php?error');
            }
        }else{
            header('Location:entrada.php?error');
        }
    }


?>