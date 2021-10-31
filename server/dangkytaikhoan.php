<?php
	include("connect.php");
	$hoten=$_POST['hoten'];
	$email=$_POST['email'];
	$matkhau=md5($_POST['matkhau']);
	$kiemtra="SELECT * FROM taikhoan WHERE Email='$email'";
	$tontai=$conn->query($kiemtra);
	if($tontai->num_rows >= 1){
		echo "Tài Khoản Đã Tồn Tại";
	}else{
		$sql="INSERT INTO taikhoan VALUES(null,'$hoten','$email','$matkhau')";
		$Dta=$conn->query($sql);
		if($Dta){
		echo "success";
		}else{
		echo "error";
		}
	}
	
?>