<?php
include "cabecera.php";

function getImgByID($id_item){
    global $conn;
    $imagenes = [];
    $catsql = "SELECT * FROM imagen where id_item = $id_item;";
    $catresult = mysqli_query($conn, $catsql);
    if (mysqli_errno($conn)){
        return [];
    } 
    while ($catrow = mysqli_fetch_assoc($catresult)) {
        array_push($imagenes, $catrow['imagen']);
    }
    return $imagenes;
}

function getItemByID($id_item){
    global $conn;
    $catsql = "SELECT * FROM item where id = $id_item;";
    $catresult = mysqli_query($conn, $catsql);
    if (mysqli_errno($conn)){
        return [];
    } 
    $catrow = mysqli_fetch_all($catresult, MYSQLI_ASSOC);
    return $catrow;
}

function getPujasByID($id_item){
    global $conn;
    $catsql = "SELECT * FROM puja WHERE id_item =".$id_item." ORDER BY cantidad DESC";
    $result = mysqli_query($conn, $catsql);
    if (mysqli_errno($conn)){
        return [];
    } 
    return mysqli_fetch_all($result, MYSQLI_ASSOC);
}

function concatenar($arr){
    $txt = "";
    for ($i=0; $i < count($arr) ; $i++) { 
        $txt .= $arr[$i];
        if ($i == count($arr) - 2) {
            $txt .= " y ";
        } else if ($i < count($arr) - 2) {
            $txt .= " , ";
        }
    }
    return $txt;
}

function getUsers(){
    global $conn;
    $catsql = "SELECT * FROM usuario";
    $result = mysqli_query($conn, $catsql);
    if (mysqli_errno($conn)){
        return [];
    }
    return mysqli_fetch_all($result, MYSQLI_ASSOC);
}
function existsUser($user){
    $usuarios = getUsers();
    for ($i=0; $i < count($usuarios) ; $i++) { 
        if($user== $usuarios[0]['username']){
            return true;
        }
    }
    return false;
}