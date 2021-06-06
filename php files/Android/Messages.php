<?php
	/* //class DBOperation{
		private $con;
		private $c;
		function __construct(){
			require_once dirname(__FILE__).'/DBConnect.php';  
			
			$db= new DBConnect();
		
			$this->con = $db->connect();
			
			$aa= new DBOperation();
			$th->c=$aa->viewMessage();
			
		//}
		
	//public function viewMessage (){ */
	
	define('DB_HOST','localhost');
	define('DB_USER','root');
	define('DB_PASS','Root123');
	define('DB_NAME','lecture_scheduling');
	
	$con=new mysqli(DB_HOST,DB_USER,DB_PASS,DB_NAME);
	if(mysqli_connect_errno()){
		die('Unable to connect database'.mysqli_connect_errno()); // "dei" for stop further execution
	}else{
		if ($_SERVER['REQUEST_METHOD']=='POST'){
			if(isset($_POST["dep_batch"])){
				$dep_batch=$_POST["dep_batch"];
				//$lecturer=$_POST["lecturer"];
				
				//$stmt = $con->prepare("select message from special_notice where dep_batch=$dep_batch or lecturer=$lecturer");
				$stmt = $con->prepare("select message from special_notice where dep_batch=? or lecturer=?");
				$stmt->bind_param("ss",$dep_batch,$dep_batch);
				$stmt->execute();
				$stmt->bind_result($t0); 
				//mysqli_query($this->con,$sql)
				
				$data=array();
				while($stmt->fetch()){
					$temp=array();
					$temp['messages']=$t0;
					array_push($data,$temp);
				}
			
			}else{
				$data['error']=true;
				$data['message']="Required fields are Missing";
			}
		}else{
				$data['error']=true;
				$data['message']="Invalid Requset";
			}
	}
		
	//}
//}
	echo json_encode($data);

?>