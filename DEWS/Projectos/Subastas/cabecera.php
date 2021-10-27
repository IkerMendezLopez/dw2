<?php
include 'config.php';
$conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_DATABASE);
mysqli_set_charset($conn, 'utf8');
session_start();

?>
<html>

<head>
    <title><?php echo(SITE_TITLE) ?></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="./css/stylesheet.css" type="text/css" />
</head>

<body>
    <div id="header">
        <h1><?php echo(SITE_TITLE) ?></h1>
    </div>
    <div id="menu">
        <a href="index.php">Home</a>
        <?php
        if (isset($_SESSION['USERNAME']) == TRUE) {
            echo "<a href='logout.php'>Logout</a>";
        } else {
            echo "<a href='login.php'>Login</a>";
        }
        ?>
        <a href="newitem.php">New Item</a>
    </div>
    <div id="container">
        <div id="bar">
            <?php require("bar.php"); ?>
        </div>
        <div id="main">