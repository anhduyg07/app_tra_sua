<?php
	include("connect.php");
	$taikhoan=$_POST['taikhoan'];
	$matkhau=md5($_POST['matkhau']);
	$sql="SELECT * FROM taikhoan WHERE Email = '$taikhoan' AND MatKhau = '$matkhau' ";
	$Dta=$conn->query($sql);
	$row=$Dta->fetch_array();
	if($row['MaTaiKhoan']!=null){
		echo $row['MaTaiKhoan'];
	}else{
		echo "error";
	}
?>