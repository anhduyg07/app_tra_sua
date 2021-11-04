<?php
    $nameserver = "localhost";
    $username = "root";
    $password = "";
    $database = "trasua";
    $conn = mysqli_connect($nameserver,$username,$password,$database);
    mysqli_query($conn,"SET NAMES 'utf8'");
?>