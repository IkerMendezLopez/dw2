<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <meta http-equiv="refresh" content="2">
</head>
<body>
<?php
    function convertirArray($filePath)
    {
        $content = [];
        $handle = fopen($filePath, "r");
        while (!feof($handle)) {
            $line = fgets($handle);
            $content[] = $line;
        }
        fclose($handle);
        return $content;
    }
    $arr = convertirArray("charla.txt");
    for ($i=0; $i < count($arr) ; $i++) { 
        echo "<p>". $arr[$i] ."</p>";
    }
?>
</body>
</html>
