<?php
	require_once '../DBQuery/DBOperation.php';
	
	
	//associative array
	$response = array();
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//check if user has given all required values
		//no need order only thing is match with tabble head
		if(isset($_POST['message'])and
			isset($_POST['date'])and
			isset($_POST['dep_batch'])and
			isset($_POST['lecturer']))
			{
			//operate the data further 
			$db=new DBOperation();
			$result=$db->sendMessages($_POST['message'],$_POST['date'],$_POST['dep_batch'],$_POST['lecturer']);
			
			if($result==1){
			$response['error']=false;
			$response['message']="Send Message successfully";
			}if($result==2){
				$response['error']=true;
				$response['message']="Some error occured try again";
			}
		}else{ // if user not provided all the required values
			$response['error']=true;
			$response['message']="Required Fields are Missing";
		}
	}else{
		$response['error']=true;
		$response['message']="Invalid Requset";
	}
	
	echo json_encode($response);
?>