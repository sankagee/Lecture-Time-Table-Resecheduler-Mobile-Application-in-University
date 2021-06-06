<?php

	require_once '../DBQuery/DBOperation.php';
	//associative array
	$response = array();
	//to storing data we use thhp post method_exists
	//check if there is post method or not
	//"$_SERVER" is predefind variable in php
	if($_SERVER['REQUEST_METHOD']=='POST'){
		//here check the feilds are empty or not
		if(isset($_POST['username']) and isset($_POST['password'])){
			//************************** invoke the database from here***********************
			$db=new DBOperation();
			if($db->studentLogin($_POST['username'],$_POST['password'])){
				$user=$db->getStudentByUsername($_POST['username']);
				$response['error']=false;
				$response['name']=$user['name'];
				$response['time table']=$user['concat(dep,batch)'];
			}else{
				$response['error']=true;
				$response['message']="Invalid username or password";
			}
		}else{
			$response['error']=true;
			$response['message']="Required Fields are Missing";
		}	
	}else{
		$response['error']=true;
		$response['message']="Invalid Request Method";
	}
	//the JSON is the data interchange format to communicate with anouther divice
	//display this array in json format & it is automatically encode array to json format & display to inside the browser 
	echo json_encode($response);
?>