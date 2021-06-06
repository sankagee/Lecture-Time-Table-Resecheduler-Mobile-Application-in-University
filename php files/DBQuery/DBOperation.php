<?php
	class DBOperation {
		private $con;
		function __construct(){
			require_once dirname(__FILE__).'/DBConnect.php';  
			
			$db= new DBConnect();
		
			$this->con = $db->connect();
			
		}
		
		/*CRUD -> C -> Create*/
		
		public function createStudent($name,$batch,$dep,$email,$username,$password){
			if($this->isLectureExist($username,$email)){
				return 0;
			}else if($this->isStudentExist($username,$email)){
				return 0;
			}else if($this->isNonAcadamicExist($username,$email)){
				return 0;
			}else{
				$pass = md5($password); 
				$stmt = $this->con->prepare ("INSERT INTO `student` (`name`, `batch`, `dep`, `email`, `user_name`, `password`) VALUES (?,?,?,?,?,?);");
				$stmt->bind_param("sissss",$name,$batch,$dep,$email,$username,$pass);
				
				if($stmt->execute()){
					return 1;
				}else{
					return 2;
				} 
			}
		}
		
		public function createLecture($name,$index_no,$dep,$email,$username,$password){
			if($this->isStudentExist($username,$email)){
				return 0;
			}else if($this->isLectureExist($username,$email)){
				return 0;
			}else if($this->isNonAcadamicExist($username,$email)){
				return 0;
			}else if(!$this->ifValidLecture($index_no)){
				return 3;
			}else{ 
				$pass = md5($password); 
				$stmt = $this->con->prepare ("INSERT INTO `lecture` (`name`, `index_no`, `dep`, `email`, `user_name`, `password`) VALUES (?,?,?,?,?,?);");
				
				$stmt->bind_param("ssssss",$name,$index_no,$dep,$email,$username,$pass);
				
				if($stmt->execute()){
					return 1;
				}else{
					return 2;
				}
			}
		}
		
		public function createNonAcadamic($name,$index_no,$email,$username,$password){
			if($this->isStudentExist($username,$email)){
				return 0;
			}else if($this->isLectureExist($username,$email)){
				return 0;
			}else if($this->isNonAcadamicExist($username,$email)){
				return 0;
			} else{ 
				$pass = md5($password); 
				$stmt = $this->con->prepare ("INSERT INTO `non_acadamic` (`name`, `employee_no`, `email`, `user_name`, `password`) VALUES (?,?,?,?,?);");
				
				$stmt->bind_param("sssss",$name,$index_no,$email,$username,$pass);
				
				if($stmt->execute()){
					return 1;
				}else{
					return 2;
				}
			}
		}
		
		private function isStudentExist($username,$email){
			$stmt=$this->con->prepare ("SELECT user_name FROM student WHERE user_name = ? OR email = ?");
			$stmt->bind_param("ss",$username,$email);
			$stmt->execute();
			$stmt->store_result();
			//this line is return 0 when user does not is exist
			return $stmt->num_rows;
		}
		
		private function isLectureExist($username,$email){
			$stmt=$this->con->prepare ("SELECT index_no FROM lecture WHERE user_name = ? OR email = ?");
			$stmt->bind_param("ss",$username,$email);
			$stmt->execute();
			$stmt->store_result();
			//this line is return 0 when user does not is exist
			return $stmt->num_rows;
		}
		
		private function ifValidLecture($emp_no){
			$stmt=$this->con->prepare ("SELECT employee_no FROM lecture_details WHERE employee_no = ?");
			$stmt->bind_param("s",$emp_no);
			$stmt->execute();
			$stmt->store_result();
			//this line is return 0 when user does not is exist
			return $stmt->num_rows;
		}
		
		private function isNonAcadamicExist($username,$email){
			$stmt=$this->con->prepare ("SELECT employee_no FROM non_acadamic WHERE user_name = ? OR email = ?");
			$stmt->bind_param("ss",$username,$email);
			$stmt->execute();
			$stmt->store_result();
			//this line is return 0 when user does not is exist
			return $stmt->num_rows;
		}
		
		public function lectureLogin($username,$passw){
			// the query will execute acording to order of the parameter
			//$pwd=md5($password);
			$passw=md5($passw);
			$stmt=$this->con->prepare("SELECT user_name FROM lecture WHERE user_name = ? AND password = ?" );
			$stmt->bind_param("ss",$username,$passw);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows>0;
		}
		
		public function studentLogin($username,$passw){
			// the query will execute acording to order of the parameter
			//$pwd=md5($password);
			$passw=md5($passw);
			$stmt=$this->con->prepare("SELECT user_name FROM student WHERE user_name = ? AND password = ?" );
			$stmt->bind_param("ss",$username,$passw);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows>0;
		}
		public function nonAcadamicLogin($username,$passw){
			// the query will execute acording to order of the parameter
			//$pwd=md5($password);
			$passw=md5($passw);
			$stmt=$this->con->prepare("SELECT user_name FROM non_acadamic WHERE user_name = ? AND password = ?" );
			$stmt->bind_param("ss",$username,$passw);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows>0;
		}
		
		public function adminLogin($username,$passw){
			$passw=md5($passw);
			$stmt=$this->con->prepare("SELECT user_name FROM admin WHERE user_name = ? AND password = ?" );
			$stmt->bind_param("ss",$username,$passw);
			$stmt->execute();
			$stmt->store_result();
			return $stmt->num_rows>0;
		}
		
		public function getLectureByUsername($username){
			//$stmt = $this->con->prepare("SELECT name,concat(dep,index_no) FROM lecture WHERE user_name = ?");
			$stmt = $this->con->prepare("SELECT name,dep,index_no FROM lecture WHERE user_name = ?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}	
		
		public function getStudentByUsername($username){
			$stmt = $this->con->prepare("SELECT name,concat(dep,batch) FROM student WHERE user_name = ?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}
		public function getNonAcadamicUsername($username){
			$stmt = $this->con->prepare("SELECT name FROM non_acadamic WHERE user_name = ?");
			$stmt->bind_param("s",$username);
			$stmt->execute();
			return $stmt->get_result()->fetch_assoc();
		}

		public function getTimeTable($timeTable){
			$stmt = $this->con->prepare("SELECT * FROM `$timeTable`");
			$stmt->execute();
			//$stmt->bind_result();
			return $stmt->get_result()->fetch_assoc();
		}
		
		
		public function sendMessages ($message,$date,$dep_batch,$lecturer){
			$stmt = $this->con->prepare ("INSERT INTO `special_notice` (`message`, `message_date`,`dep_batch`,`lecturer`) VALUES (?,?,?,?);");
			$stmt->bind_param("ssss",$message,$date,$dep_batch,$lecturer);
			if($stmt->execute()){
				return 1;
			}else{
				return 2;
			}
			
		}
		
	}
?>