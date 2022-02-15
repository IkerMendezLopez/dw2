<?php
    require 'config.php';
    
    
    function conexion(){
        
        $con = mysqli_connect(HOST, USER, PASS, BD);
        
        if(mysqli_connect_errno($con)>0){ //saca un error del metodo
            return false;
        }
        else{
            return $con;
        }
    }
    
    
    function subastasVencidas($con){
        
        $subastasVencidas = array();
        
        $sql = "select id, id_cat, id_user, nombre, preciopartida, descripcion, fechafin from items where fechafin<now()";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_assoc($rs)){
            
            $subastasVencidas [] = $fila;
        }
        
        return $subastasVencidas;
    }
    
    
    function categorias($con, $idCat){
        
        $categorias = array();
        
        $sql = "select id, categoria from categorias where id=$idCat";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_object($rs)){
            $categorias [$fila->id] = $fila->categoria;
        }
        
        return $categorias;
    }
    
    
    function userMaxPuja ($con, $idItem){
        
        $usuarios = array();
        $sql = "select max(cantidad) cant, id_user from pujas where id_item=$idItem group by id desc";
        $rs = mysqli_query($con,$sql);
        
        while($fila = mysqli_fetch_object($rs)){
            $usuarios [$fila->id_user] = $fila->cant;
        }   
        
        
        if(empty($usuarios)){
            return 0;
        }
        else{
            $idMaxCantUser = "";
            $cantMaxuser = "";
            $maxCant = -1;
            foreach($usuarios as $idUser => $cant){
                if($cant>$maxCant){
                    $maxCant = $cant;
                    $idMaxCantUser = $idUser;
                    $cantMaxuser = $cant;
                }
            }
            
            
            
            $nombre = array();
            
            $sql = "select nombre from usuarios where id=$idUser";
            $rs = mysqli_query($con, $sql);

            while($fila = mysqli_fetch_array($rs)){
                $nombre []  = $idMaxCantUser . "/" . $fila[0] . "/" . $cantMaxuser;
            }

            return $nombre;
        }  
    }
    
    function subastasVigentes($con){
        
        $subastasVigentes = array();
        
        $sql = "select id, id_cat, id_user, nombre, preciopartida, descripcion, fechafin from items where fechafin>now()";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_assoc($rs)){
            
            $subastasVigentes [] = $fila;
        }
        
        return $subastasVigentes;
    }
    
    
    function ultimaPuja($con, $idItem){
        
        $ultimaPuja = array();
        
        $sql = "select id_item, cantidad, fecha from pujas where id_item=$idItem order by fecha desc";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_object($rs)){
            $ultimaPuja [] = $fila;
        }
        
        if(empty($ultimaPuja)){
            return 0;
        }
        else{
            return $ultimaPuja;
        }
    }
    
    /*
    //Funcion que devuelve un array con las categorias
    function categorias($con){

        $categorias = array();
        
        $sql = "select id, categoria from categorias";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_object($rs)){
            
            $categorias [] = $fila;
        }
        
        return $categorias;
    }
    
    
    //Funcion que comprueba si un usuario existe en la bbdd
    function compruebaUser($con, $user, $pass=""){
      
        if($pass==""){
            
            $sql = "select username from usuarios where username='$user'"; //AÑADIR QUE COMPARE TAMBIEN  EL MAIL YA QUE NO DEBERIA DE PODER DEJAR REGISTRAR DOS CUENTAS DE USUARIOS LOS CUALES TENGAN EL MISMO EMAIL (pasar el email como parametro optativo al metodo)
            $rs = mysqli_query($con, $sql);
            
            if($fila = mysqli_fetch_assoc($rs)){
                return 2;
            }
        }
        else{
            $sql = "select username from usuarios where username='$user' and password='$pass'";
            $rs = mysqli_query($con, $sql);

            if($fila = mysqli_fetch_assoc($rs)){

                $sql = "select username from usuarios where username='$user' and password='$pass' and activo=1";
                $rs = mysqli_query($con, $sql);

                if($fila = mysqli_fetch_assoc($rs)){
                    return 1;
                }
                else{
                    return 0;
                }
            }   
            else{
                return -1;
            }            
        }
    }
    
    
    //Funcion que devuelve un String con 16 caracteres aleatorios
    function generaCadenaAleatoria(){
        
        $strAleatoria="";
        $carPermitidos = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ@!?¿*%/";

        for($i=0; $i<16; $i++){
            
            $numAleatorio = rand(0, strlen($carPermitidos));
            $strAleatoria = $strAleatoria . $carPermitidos [$numAleatorio-1];
        }
        
        return $strAleatoria;
    }
    
    
    //Funcion que añade un nuevo usuario a la bbdd
    function nuevoUser($con, $user, $pass, $mail, $nombreApellidos, $cadVerificacion){
        
        $sql = "insert into usuarios(username, nombre, password, email, cadenaverificacion, activo)" . "values('$user', '$nombreApellidos', '$pass', '$mail', '$cadVerificacion', 0)";
        
        mysqli_query($con, $sql);
    }
    
    
    //Funcion que manda un correo con un enlace a "verificacion.php" pasando por GET la cadena de verificacion y el email
    function enviarMail($mailRegistrado, $nombreApellidos, $cadVerificacion){
        
        require 'class.phpmailer.php';

        $mail = new PHPMailer();
        $mail->IsSMTP();
        $mail->SMTPAuth = true;
        
        //datos servidor de correo gmail
        $mail->Host = "smtp.zoho.eu";
        $mail->Port = 587;
        $mail->SMTPSecure = "tls";
        $mail->Username = "anderdews@zohomail.eu";
        $mail->Password = "ander@DEWS";
        
        //datos del mensaje
        $mail->From = "anderdews@zohomail.eu";
        $mail->FromName = "Web de Subastas";
        $mail->AddAddress($mailRegistrado, $nombreApellidos); 
        $mail->Subject = "Tu cuenta de Subastas Ciudad Jardin: verificion de su cuenta de correo";  
        
        //envio mensaje html
        $mail->IsHTML(true);
            $enlace = RUTA_APP . "verificacion.php?cadena=" . urlencode($cadVerificacion) . "&mail=" . urlencode($mailRegistrado) ;
            $msg = "<p>Hola <strong>$nombreApellidos</strong>:<br><br> Necesitamos verificar la direccion de correo electronico para poder completar el registro</p><a href='$enlace'>Verificar correo electronico</a>";
        $mail->MsgHTML($msg);

        if($mail->Send()){
            return true;
        }
        else{
           return false;
        }   
    }
    
    
    //Funcion que devuelve un array con los campos del usuario pasado como parametro
    function userLogueado($con, $user, $pass){
        
        $userLogueado = array();
        
        $sql = "select id,username,nombre,password,email,cadenaverificacion from usuarios where username='$user' and password='$pass'";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_object($rs)){
            $userLogueado [] = $fila;
        }
        
        return $userLogueado;
    }
    
    
    //Funcion que activa un usuario si la cadena de verificacion es similar a la que tenga guardada el usuario que tenga el mismo mail que el pasado como parametro
    function activaUser($con, $cadVerificacion, $mailRegistrado){
        
        $sql = "update usuarios set activo=1 where email='$mailRegistrado' and cadenaverificacion='$cadVerificacion'";
        mysqli_query($con, $sql);
                
        return mysqli_affected_rows($con);
    }
    
    
    //Funcion que devuelve un array de objetos item
    function devuelveItems($con, $categoriaSeleccionada="", $idItem=""){ //REVISAR ESTE METODO
        
        $items = array();
        
        if($categoriaSeleccionada==""){
            $sql = "select id, id_cat, id_user, nombre, preciopartida, descripcion, fechafin from items";
        }
        else{
            if($idItem==""){
                $sql = "select id, id_cat, id_user, nombre, preciopartida, descripcion, fechafin from items where id_cat='$categoriaSeleccionada'";
            }
            else{
                $sql = "select id, id_cat, id_user, nombre, preciopartida, descripcion, fechafin from items where id=$idItem";
            }
        }

        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_object($rs)){
            $items [] = $fila;
        }
        
        return $items;
    }
    
    
    //Funcion que devuelve la cantidad de pujas del producto cuyo id se pase como parametro o bien la cantidad de pujas realizadas por el usuario en el dia de hoy mediante su id y la fecha
    function cantPujas($con, $id, $idUser="", $fecha=""){
        
        $cantPujas=0;
        if($idUser=="" && $fecha==""){
            $sql = "select count(id) from pujas where id_item=$id";
        }
        else{
            $sql = "select count(id_user) from pujas where id_user=$idUser and fecha='$fecha'";
        }
        
        $rs = mysqli_query($con, $sql);
        
        if($fila = mysqli_fetch_array($rs)){
            $cantPujas = $fila [0];
        }
        
        return $cantPujas;
    }
    
    
    //Funcion que devuelve el precio de partida en caso de no existir ninguna puja, o el precio de la puja mas alta
    function precioActual($con, $id, $precioPartida){
        
        $precioActual = $precioPartida;
        
        $sql = "select max(cantidad) from pujas where id_item=$id and cantidad>$precioActual";
        $rs = mysqli_query($con, $sql);
        
        if($fila = mysqli_fetch_array($rs)){
            $precioActual = $fila [0];
        }
        
        if($precioActual==null){
            $precioActual = $precioPartida;
        }
        
        return $precioActual;
    }

    
    //Funcion que devuelve la imagen cuyo id de item le corresponda
    function imagenes($con, $id){
        
        $imagen="";
        
        $sql = "select id, id_item, imagen from imagenes where id_item=$id";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_array($rs)){
            $imagen = $fila[2];
        }
        
        return $imagen;
    }
    
    
    //Funcion que realiza una puja insertando la tupla en la tabla pujas
    function pujarItem($con, $idItem, $idUser, $cant){
        
        $sql = "insert into pujas(id_item, id_user, cantidad, fecha) values($idItem, $idUser, $cant, now())";
        mysqli_query($con, $sql);
        
        return mysqli_affected_rows($con);
    }
    
    
    //Funcion que devuelve un array con los nombres de las personas que han pujado por un objeto y la cantidada pujada por dicho objeto
    function historialPujas($con, $idItem){
        
        $historialPuja = array();
        
        $sql = "select nombre, cantidad from usuarios, pujas where id_item=$idItem and usuarios.id=pujas.id_user";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_object($rs)){
            $historialPuja [] = $fila;
        }
        
        return $historialPuja;
    }
    
    
    //Funcion que devuelve true o false dependiendo de si la fecha del momento en el que se esta realizando la puja es mayor a la fecha de finalizacion para la puja de dicho item
    function comprobarFechaPuja($fechaFin, $fechaAct){
        
        if($fechaAct>$fechaFin){
            return false;
        }
        else{
            return true;
        }
    }
    
    
    //Funcion que registra un item en la base de datos
    function registrarItem($con, $idCategoria, $idUser, $nombre, $precio, $descripcion, $fecha){
                
        $sql = "insert into items(id_cat, id_user, nombre, preciopartida, descripcion, fechafin) values($idCategoria, $idUser, '$nombre', $precio, '$descripcion', '$fecha')";

        mysqli_query($con, $sql);
        
        return mysqli_affected_rows($con);
    }
    
    
    //Funcion que devuelve un array con un objeto item, el mismo del nombre que se le pase como parametro
    function devuelveItems2($con, $nombre){
        
        $item = array();
        
        $sql = "select id, id_cat, id_user, nombre, preciopartida, descripcion, fechafin from items where nombre='$nombre' order by fechafin asc";
        
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_assoc($rs)){
            
            $item [] = $fila;
        }
        
        return $item;
    }
    
    
    //Funcion que actualiza el precio subiendolo o bajandolo en la cantidad indicado del item cuyo id se haya pasado por parametro dependiendo de la opcion que se le pase
    function actualizaPrecio($con, $idItem, $opcion, $cant){
        
        $accion = false;
        
        switch($opcion){
            case '0':
            case '1' : 
                        if($opcion==0){
                            $sql = "update items set preciopartida=$cant where id=$idItem";
                        }
                        else{
                            $sql = "update items set preciopartida=$cant where id=$idItem";
                            $accion  = true;
                        }
                            
                        mysqli_query($con, $sql);
                        
                        if($accion){
                            return 1;
                        }
                        else{
                            return 0;
                        }
                break;
            case '2' : return 2;
                break;
        }   
    }
    
    
    //Funcion que pospone la fecha de fin de puja en 1 hora o 1 dia dependiendo de el parametro estado pasado como parametro
    function posponerHora($con,  $idItem, $estado, $fecha){
        
        if(!$estado){
            $newFecha = $fecha+86400;
        }
        else{
            $newFecha = $fecha+3600;
        }
        
        $newFecha = date('Y-m-d g:i:00', $newFecha);
        
        $sql = "update items set fechaFin='$newFecha' where id=$idItem";

        mysqli_query($con, $sql);
        
        return $newFecha;
    }
    */
    

