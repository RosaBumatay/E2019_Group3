
<?php

require 'DatabaseConfig.php';
$con = mysqli_connect($HostName,$HostUser,$HostPass,$DatabaseName);

$password = $_POST['password'];
$email = $_POST['email'];

$sqlinsert = "UPDATE `patients` SET `user_password`='".$password."' where `user_email` ='".$email."'";
$result=mysqli_query($con,$sqlinsert);


if($result){

    echo'("message":"Password Updated Succesfully !..")';

}else{

echo'("message":"Unable to update Password !..")';

}



?>