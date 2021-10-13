 <!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
    </head>
    <body>
        <?php
            session_start();
            session_destroy(); //Si tengo mas de una sesion abierta, al destruirla y volver a crearla, me guarda solo los datos de cada sesion por independiente, sino me guardaria los valores de la primera sesion iniciada para todas las demas
            session_start();

            if(!isset($_POST['submitLogin'])){
                header("location:index.php");
            }
            else{
                if($_POST['login']=="admin"){
                    //Admin debe entrar con pass=123

                    if($_POST['pass']!="123"){
                        header("location:index.php?error");
                    }
                    else{
                        $_SESSION['login']="administrador";
                        header("location:admin.php");
                    }   
                }
                else{
                    //Usuario no admin, pass=(su nombre)
                    if($_POST['pass']!=$_POST['login']){
                        header("location:index.php?error");
                    }
                    else{
                        $_SESSION['login']=$_POST['login'];
                        
                        if(isset($_POST['sexo'])){
                            //Crear una cookie con el sexo
                            setcookie("sexo" , $_POST['sexo'], time()+3600);
                        }
                        header("location:encuesta.php");
                    }
                }
            }
         ?>
    </body>
</html>

