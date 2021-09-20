<!DOCTYPE html>
<html>
    <head>
        <title>TITULO</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            table, td, tr, th{
                border: 1px solid black;
                border-collapse: collapse;
            }



        </style>
    </head>
    <body>
        <?php
        $handle = fopen("./archivos/urls.txt", "r");
        while (!feof($handle)) {
            $line = fgets($handle);
            $line = explode("   ", $line);
            echo "<a href=\"{$line[0]}\">{$line[1]}</a> <br>";
        }
        fclose($handle);
        ?>

    </body>
</html>

