<?php

function autentica($user, $pass)
{
	return usuarios()[$user]['pass'] == $pass;
}


function tipoPlatos($tipo)
{
	$platos = damePlatos();
	$arr = [];
	foreach ($platos as $key => $value) if($value['tipo'] == $tipo) $arr[$key] = $value;
	return $arr;
}

function dameDcto($user)
{
	$users = usuarios();
	return $users[$user]['discount'] ?? false;
}

function usuarios()
{
	$filePath = './socios.txt';
	$file = fopen($filePath, "r");
	$users = [];
	while (!feof($file)) {
		$line = fgets($file);
		$line = explode(' ', $line);
		$users[$line[0]] = ["username" => $line[0], "pass" => $line[1], "discount" => $line[2]];
	}

	return $users;
}

function damePlatos()
{
	$filePath = './platos.txt';
	$file = fopen($filePath, "r");
	$platos = [];
	while (!feof($file)) {
		$line = fgets($file);
		$line = explode(' ', $line);
		$platos[$line[0]] =  ["tipo" => $line[1], "precio" => $line[2]];
	}

	return $platos;
}

function damePrecio($plato)
{
	return damePlatos()[$plato]['precio'];
}
