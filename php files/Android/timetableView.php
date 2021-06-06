<?php


	$user_namee="Danushka";
	define('DB_HOST','localhost');
	define('DB_USER','root');
	define('DB_PASS','');
	define('DB_NAME','lecture_scheduling');
	
	$con=new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME);
	if(mysqli_connect_errno()){
		die('Unable to connect database'.mysqli_connect_errno()); // "dei" for stop further execution
	}
	

$data=array();
if ($_SERVER['REQUEST_METHOD']=='POST'){
	
	if(isset($_POST["timetable"])){
	
		$index=$_POST["timetable"];

		
		$stmt = $con->prepare("select * from $index");
		$stmt->execute();
		$stmt->bind_result($t0,$t1,$t2,$t3,$t4,$t5,$t6,$t7,$t8,$t9,$t10,$t11); 
		
		
		while($stmt->fetch()){
			$temp=array();
		
			$temp['day']=$t0;
			$temp['t1']=$t1;
			$temp['t2']=$t2;
			$temp['t3']=$t3;
			$temp['t4']=$t4;
			$temp['t5']=$t5;
			$temp['t6']=$t6;
			$temp['t7']=$t7;
			$temp['t8']=$t8;
			$temp['t9']=$t9;
			$temp['t10']=$t10;
			
			array_push($data,$temp);
		}
	
	}else{
		$data['error']=true;
		$data['message']="Required Fields are Missing";
	}
}else{
	$data['error']=true;
	$data['message']="Invalid Requset";
}

echo json_encode($data);
	
?>