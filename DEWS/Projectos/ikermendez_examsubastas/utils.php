<?php 
include 'cabecera.php';

function getSubastasVencidas()
{
    global $conn;
    $items = [];
    $catsql = "SELECT * FROM items";
    $catresult = mysqli_query($conn, $catsql);
    if (mysqli_errno($conn)) {
        return [];
    }
    while ($catrow = mysqli_fetch_assoc($catresult)) {
        if(!fechaMayor($catrow['fechafin'])){
            array_push($items, $catrow);
        }
    }
    return $items;
}

function getSubastasVigentes()
{
    global $conn;
    $items = [];
    $catsql = "SELECT * FROM items";
    $catresult = mysqli_query($conn, $catsql);
    if (mysqli_errno($conn)) {
        return [];
    }
    while ($catrow = mysqli_fetch_assoc($catresult)) {
        if(fechaMayor($catrow['fechafin'])){
            array_push($items, $catrow);
        }
    }
    return $items;
}

function fechaMayor($fecha1){
    $fecha1= strtotime($fecha1);
    $fecha2 = time();
    return $fecha1 > $fecha2;
}

function getCategoriaByID($id_cat)
{
    global $conn;
    $catsql = "SELECT * FROM categorias where id = $id_cat;";
    $catresult = mysqli_query($conn, $catsql);
    if (mysqli_errno($conn)) {
        return [];
    }
    $catrow = mysqli_fetch_assoc($catresult);
    return $catrow['categoria'];
}

function getUserByID($id)
{
    global $conn;
    $catsql = "SELECT * FROM usuarios where id = $id;";
    $catresult = mysqli_query($conn, $catsql);
    if (mysqli_errno($conn)) {
        return [];
    }
    $catrow = mysqli_fetch_assoc($catresult);
    return $catrow['nombre'];
}

function getUserUltimaPuja($id_item){
    global $conn;
    $catsql = "SELECT * FROM pujas where id_item = $id_item order by fecha desc;";
    $catresult = mysqli_query($conn, $catsql);
    if (mysqli_errno($conn)) {
        return [];
    }
    $catrow = mysqli_fetch_assoc($catresult);
    $userPujaItem = [$id_item, $catrow['cantidad'], $catrow['id_user']];
    return $userPujaItem;
}

function mesesYDias($fecha1){
	$fecha1=strtotime($fecha1);
	$fecha2=time();
		//Ya tenemos 2 fechas expresadas como timestamp (en segundos)
	return (int)(($fecha1-$fecha2)/(60*60*24));
}
?>