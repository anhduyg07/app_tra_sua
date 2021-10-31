<?php
    $servername = "localhost";
    $username = "root";
    $password = "";
    $database = "trasua";
    $conn = mysqli_connect($servername,$username,$password,$database);
    mysqli_query($conn,"SET NAMES 'utf8'");
?>