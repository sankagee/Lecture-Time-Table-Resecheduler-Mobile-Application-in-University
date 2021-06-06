<?php

	require_once '../DBQuery/DBOperation.php';
	//associative array
	$response = array();
	//to storing data we use the php post method_exists
	//check if there is post method or not
	//"$_SERVER" is predefind variable in php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		if(isset($_POST['name'])and
			isset($_POST['index'])and
			isset($_POST['email'])and
			isset($_POST['username'])and
			isset($_POST['password']) )
			{
			$db=new DBOperation();
			$result=$db->createNonAcadamic($_POST['name'],$_POST['index'] ,$_POST['email'],$_POST['username'],$_POST['password']);
			
			if($result==1){
				$response['error']=false;
				$response['message']="User registered successfully";
			}else if($result==2){
				$response['error']=true;
				$response['message']="Some error occured try again";
			}else if($result==0){
				$response['error']=true;
				$response['message']="index or user name already exists";
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