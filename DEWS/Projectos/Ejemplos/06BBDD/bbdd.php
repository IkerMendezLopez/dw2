<?php
    
    define('HOST', 'localhost');
    define('USER', 'root');
    define('PASS', '');
    define('BD', 'bdrestaurante');


    //Funcion para conecntarse a la bbbdd
    function conexion(){
        
        $con = mysqli_connect(HOST, USER, PASS, BD); //variable con la conexion
        
        if(mysqli_connect_errno($con)>0){ //devuelve el codigo de error de la ultima llamada, asi que si no devuelve nada se ha podido realizar correctamente la conexion
            return false;
        }
        else{
            return $con;
        }
    }
    
    
    //Funcion para añadir un alimento a la tabla
    function insertarAlimentoAhora($con, $nombre, $precio, $tipo){
        
        $sql = "insert into alimentos (nombre, precio, tipo, fecha)" . " values('$nombre', $precio, '$tipo', now())"; //now() devuelve la fecha de la base de datos
        //echo $sql;
        
        mysqli_query($con, $sql); //Para realizar consultas como insertar borrar seleccionar...
    }
    
    
    //Funcion que devuelve un array cuyo valor es cada fila de la consulta (EJEMP: clave-->0 valor-->(array asocitivo cuya clave es el nombre del campo (de la tabla nombre) y valor valor del campo(de la tabla pan)))
    function alimentos($con){
        $alims = array();
        $sql = "select id, nombre, precio, tipo, fecha from alimentos"; 
        
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_assoc($rs)){ //recorre todas las filas de la consulta y devuelve falso cuando llega al final
            
            $alims [] = $fila;
        } 
        
        return $alims;
    }
    
    
    //Function que devuelve el numero de filas al que le ha afectado la consulta
    function actualizarAlimAntiguos($con){
        
        $sql  = "update alimentos set fecha=now() where fecha<'2020/10/01'"; //si en el where la fecha la pasas entre guiones(-) tambien funciona
        mysqli_query($con, $sql);
        return mysqli_affected_rows($con); //Devuelve en numero de filas afectadas a dicha consulta   
    }
    
    
    //Funcion que devuelve un array con los distintos tipos de alimentos
    function tipos($con){
        
        $tipos=[];
        
        $sql = "select distinct tipo from alimentos";
        $rs = mysqli_query($con, $sql);
        
        while($fila  = mysqli_fetch_array($rs)){
            $tipos [] = $fila[0];
        }
      
        return $tipos;
    }
    
    
    //Funcion que hace lo mismo que la funcion alimentos, solo que comprueba si el alimento tiene tipo o no
    function alimentosTipo($con, $tipo=""){
        
        $alims = array();
        if($tipo==""){
            $sql = "select id, nombre, precio, tipo, fecha from alimentos"; 
        }
        else{
            $sql = "select id, nombre, precio, tipo, fecha from alimentos where tipo='$tipo'"; 
        }
        
        
        $rs = mysqli_query($con, $sql);
        while($fila = mysqli_fetch_assoc($rs)){ //recorre todas las filas de la consulta y devuelve falso cuando llega al final
            
            $alims [] = $fila;
        } 
        
        return $alims;
    }

    
    //Funcion que devuelve true o false dependiendo de si se ha podido registrar un cliente o no (para ello comprobamos si hay en la tabla un cliente con el mismo DNI pasado como parametro mediante la funcion mysqli_fetch_assoc() que devuelve false cuando llega al final de las filas, de forma que si no existe un cliente con el dni pasado no devuelve ninguna fila por lo que directamente es false)
    function registrar($con, $dni, $nombre){
        
        $sql = "select idcliente from clientes where idcliente='$dni'";
        $rsCliente = mysqli_query($con, $sql);
        
        /*
        if(mysqli_num_rows($rsCliente)==1){
            return false;
        }*/
        
        
        if($fila = mysqli_fetch_assoc($rsCliente)){
            return false;
        }
        else{
            $sqli2 = "insert into clientes(idcliente, nombre) values ('$dni', '$nombre')";
            mysqli_query($con, $sqli2);
            //mysqli_affected_rows($sqli2); //contendra 1 porque solo se inserta una fila
            return true;
        }
    }
    
    
    //Funcion que devuelve en un array asociativo los alimentos
    function alimentos2($con){
        
        $alims = array();
        $sql = "select id, nombre, precio, tipo, fecha from alimentos"; 
        
        $rs = mysqli_query($con, $sql);
        while($fila = mysqli_fetch_object($rs)){ 
            
            $alims [] = $fila;
        } 
        
        return $alims;
    }
    
    
    //Funcion que devuelve el precio del alimento cuyo id se le pase como parametro a la funcion
    function precioDe($con, $idalimento){
        
        $sql = "select precio from alimentos where id=$idalimento";
        $rs = mysqli_query($con, $sql);
        
        if($alimento = mysqli_fetch_object($rs)){ 
            return $alimento->precio;
        }
    }
    
    
    //Funcion que inserta una tupla en la tabla de pedidos en la cual inserta el idcliente y el precio total del pedido
    function nuevoPedido($con, $idcliente, $total){
        
        $sql = "insert into pedidos(idcliente, preciototal)" . "values('$idcliente', $total)";
        mysqli_query($con, $sql);
        
        if(mysqli_affected_rows($con)==1){
           return mysqli_insert_id($con);
        }
        else{
            return -1;
        }
    }
    
    
    //Funcion que devuelve en un array asociativo los alimentos y las imagenes
    function alimentos3($con){
        
        $alims = array();
        $sql = "select id, nombre, precio, tipo, fecha, imagen from alimentos"; 
        
        $rs = mysqli_query($con, $sql);
        while($fila = mysqli_fetch_object($rs)){ 
            
            $alims [] = $fila;
        } 
        
        return $alims;
    }
    
    
    //Funcion que actualiza la tupla del idalimento pasado como parametro estableciendole como valor del campo imagen el blob pasado
    function guardarImagen($con, $idalimento, $blobimg){
        
        $sql = "update alimentos set imagen='$blobimg' where id=$idalimento";
     
        mysqli_query($con, $sql);
    }
    
    
    //Funcion que devuelve un array asociativo de clientes
    function clientes($con){
        
        $clientes = array();
        
        $sql = "select idcliente, nombre from clientes";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_object($rs)){
            $clientes [] = $fila;
        }
        
        return $clientes;
    }
    
    
    //Funcion que devuelve el numero de pedidos del idcliente pasado como parametro
    function numPedidos($con, $idcliente){
         
        $sql = "select count(idcliente) as cant from pedidos where idcliente='$idcliente'";
        $rs = mysqli_query($con, $sql);
        
        if($fila = mysqli_fetch_object($rs)){
            return $fila->cant;
        }
        else{
            return 0;
        }
    }
    
    
    //Funcion que devuelve un array asociativo de los pedidos en forma de objeto
    function pedidos($con, $idcliente){
        
        $pedidos = array();
        
        $sql = "select idpedido, idcliente, preciototal from pedidos where idcliente='$idcliente'";
        $rs = mysqli_query($con, $sql);
        
        while($fila = mysqli_fetch_object($rs)){
            
            $pedidos [] = $fila;
        }
        
        return $pedidos;
    }
    

    
    
    
    