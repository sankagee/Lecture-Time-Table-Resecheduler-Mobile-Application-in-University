<?php
	class DBOperation {
		private $con;
		function __construct(){
			require_once dirname(__FILE__).'/DBConnect.php';  
			
			$db= new DBConnect();
		
			$this->con = $db->connect();
			
		}
		
		/*CRUD -> C -> Create*/
		
		
		
		public function eventSchedule ($table){

			$qury="create event if not exists `event_$table` on schedule every 1 hour starts date_format(now(),'%y-%m-%d- %H:00:00') do
			update $table inner join `temp_$table` on $table.day=`temp_$table`.day 
			set
			$table.`08.00-09.00`=`temp_$table`.`08.00-09.00`,
			$table.`09.00-10.00`=`temp_$table`.`09.00-10.00`,
			$table.`10.00-11.00`=`temp_$table`.`10.00-11.00`,
			$table.`11.00-12.00`=`temp_$table`.`11.00-12.00`,
			$table.`12.00-13.00`=`temp_$table`.`12.00-13.00`,
			$table.`13.00-14.00`=`temp_$table`.`13.00-14.00`,
			$table.`14.00-15.00`=`temp_$table`.`14.00-15.00`,
			$table.`15.00-16.00`=`temp_$table`.`15.00-16.00`,
			$table.`16.00-17.00`=`temp_$table`.`16.00-17.00`,
			$table.`17.00-18.00`=`temp_$table`.`17.00-18.00`,
			$table.reset_date=`temp_$table`.reset_date where $table.reset_date = CONVERT_TZ(now(),'+00:00','+05:30');";
			
		mysqli_query($this->con,$qury);
		
		}
		
		
		
		public function eventScheduleNonAcdmc ($table){
			
			if ($table=="Monday"||$table=="Tuesday"||$table=="Wednesday"||$table=="Thursday"||$table=="Friday"){
				$qury="create event if not exists `event_$table` on schedule every 1 hour starts date_format(now(),'%y-%m-%d- %H:00:00') do
				update $table inner join `temp_$table` on $table.place=`temp_$table`.place 
				set
				$table.`08.00-09.00`=`temp_$table`.`08.00-09.00`,
				$table.`09.00-10.00`=`temp_$table`.`09.00-10.00`,
				$table.`10.00-11.00`=`temp_$table`.`10.00-11.00`,
				$table.`11.00-12.00`=`temp_$table`.`11.00-12.00`,
				$table.`12.00-13.00`=`temp_$table`.`12.00-13.00`,
				$table.`13.00-14.00`=`temp_$table`.`13.00-14.00`,
				$table.`14.00-15.00`=`temp_$table`.`14.00-15.00`,
				$table.`15.00-16.00`=`temp_$table`.`15.00-16.00`,
				$table.`16.00-17.00`=`temp_$table`.`16.00-17.00`,
				$table.`17.00-18.00`=`temp_$table`.`17.00-18.00`,
				$table.reset_date=`temp_$table`.reset_date where $table.reset_date = CONVERT_TZ(now(),'+00:00','+05:30');";
			}else{
				$qury="create event if not exists `event_$table` on schedule every 1 hour starts date_format(now(),'%y-%m-%d- %H:00:00') do
				update $table inner join `temp_$table` on $table.day=`temp_$table`.day 
				set
				$table.`08.00-09.00`=`temp_$table`.`08.00-09.00`,
				$table.`09.00-10.00`=`temp_$table`.`09.00-10.00`,
				$table.`10.00-11.00`=`temp_$table`.`10.00-11.00`,
				$table.`11.00-12.00`=`temp_$table`.`11.00-12.00`,
				$table.`12.00-13.00`=`temp_$table`.`12.00-13.00`,
				$table.`13.00-14.00`=`temp_$table`.`13.00-14.00`,
				$table.`14.00-15.00`=`temp_$table`.`14.00-15.00`,
				$table.`15.00-16.00`=`temp_$table`.`15.00-16.00`,
				$table.`16.00-17.00`=`temp_$table`.`16.00-17.00`,
				$table.`17.00-18.00`=`temp_$table`.`17.00-18.00`,
				$table.reset_date=`temp_$table`.reset_date where $table.reset_date = CONVERT_TZ(now(),'+00:00','+05:30');";
			}
			/* $event = $this->con-> */mysqli_query($this->con,$qury);
			//$event->execute();
			
			public function eventScheduleLecturer($table){
				
				$qury="create event if not exists `event_$table` on schedule every 1 hour starts date_format(now(),'%y-%m-%d- %H:00:00') do
				update $table inner join `temp_$table` on $table.day=`temp_$table`.day 
				set
				$table.`08.00-09.00`=`temp_$table`.`08.00-09.00`,
				$table.`09.00-10.00`=`temp_$table`.`09.00-10.00`,
				$table.`10.00-11.00`=`temp_$table`.`10.00-11.00`,
				$table.`11.00-12.00`=`temp_$table`.`11.00-12.00`,
				$table.`12.00-13.00`=`temp_$table`.`12.00-13.00`,
				$table.`13.00-14.00`=`temp_$table`.`13.00-14.00`,
				$table.`14.00-15.00`=`temp_$table`.`14.00-15.00`,
				$table.`15.00-16.00`=`temp_$table`.`15.00-16.00`,
				$table.`16.00-17.00`=`temp_$table`.`16.00-17.00`,
				$table.`17.00-18.00`=`temp_$table`.`17.00-18.00`,
				$table.reset_date=`temp_$table`.reset_date where $table.reset_date = CONVERT_TZ(now(),'+00:00','+05:30');";
			
				mysqli_query($this->con,$qury);
		
			}
		}
		
		public function mainUpdate($day,$timeFrom,$timeTo,$dep,$batch,$subject,$date,$place,$lecturer){
			
			$r1=$this->updateStudent ($day,$timeFrom,$timeTo,$dep,$batch,$subject,$date,$place);
			$r2=$this->updateNonAcdmc ($day,$timeFrom,$timeTo,$subject,$date,$place);
			$r3=$this->updateLectcurer ($day,$timeFrom,$timeTo,$subject,$date,$place,$lecturer);
			if($r1==$r2){
				return $r1;
			}
		}
		
		public function updateStudent ($day,$timeFrom,$timeTo,$dep,$batch,$subject,$date,$place){
			$subject=$subject." ".$place;
			if($day=="Sunday" || $day=="Saturday"){
				return 6;
			}else if($timeFrom<=7 || $timeTo>=19){
					return 5;
				}else if($timeFrom!=12){
						if ($timeTo>$timeFrom){
							$table=$dep.$batch;
							$resetTime=$date." ".$timeTo;
							$stmt_t=$this->con->prepare("CREATE TABLE IF NOT EXISTS `temp_$table` as (select * from $table);");
							$stmt_t->execute();
							/* $object=new DBOperation();
							$object-> */
							$this->eventSchedule($table);

							if (($timeTo-$timeFrom)==1){
								$stmt = $this->con->prepare("UPDATE `$table` SET `$timeFrom-$timeTo`='$subject',`reset_date`='$resetTime' WHERE day='$day';");
							}else if (($timeTo-$timeFrom)==2){
								$timeMid=number_format($timeFrom+01.00,2);
								if ($timeMid<10){
									$timeMid="0".$timeMid;
								}
								$stmt = $this->con->prepare("UPDATE `$table` SET `$timeFrom-$timeMid`='$subject',`$timeMid-$timeTo`='$subject',`reset_date`='$resetTime' WHERE day='$day';");
								//echo $timeMid;
							}else if (($timeTo-$timeFrom)==3){
								$timeMid1=number_format($timeFrom+1.00,2);
								if ($timeMid1<10){
									$timeMid1="0".$timeMid1;
								}
								$timeMid2=number_format($timeFrom+2.00,2);
								if ($timeMid2<10){
									$timeMid2="0".$timeMid2;
								}
								$stmt = $this->con->prepare("UPDATE `$table` SET `$timeFrom-$timeMid1`='$subject',`$timeMid1-$timeMid2`='$subject',`$timeMid2-$timeTo`='$subject',`reset_date`='$resetTime' WHERE day='$day';");
							}
							//return $stmt->execute();
							
							if ($stmt->execute()){
								return 1;
							}else{
								return 2;
							}
						}else {
							return 3;
						}
					
					}else {
						return 4;
					}
			
		}
		
		public function updateLectcurer ($day,$timeFrom,$timeTo,$subject,$date,$place,$lecturer){
			$subject=$subject." ".$place;
			if($day=="Sunday" || $day=="Saturday"){
				return 6;
			}else if($timeFrom<=7 || $timeTo>=19){
					return 5;
				}else if($timeFrom!=12){
						if ($timeTo>$timeFrom){
							$table=$lecturer;
							$resetTime=$date." ".$timeTo;
							$stmt_t=$this->con->prepare("CREATE TABLE IF NOT EXISTS `temp_$table` as (select * from $table);");
							$stmt_t->execute();
							
							/* $object=new DBOperation();
							$object-> */
							$this->eventScheduleLecturer($table);
							


							if (($timeTo-$timeFrom)==1){
								$stmt = $this->con->prepare("UPDATE `$table` SET `$timeFrom-$timeTo`='$subject',`reset_date`='$resetTime' WHERE day='$day';");
							}else if (($timeTo-$timeFrom)==2){
								$timeMid=number_format($timeFrom+01.00,2);
								if ($timeMid<10){
									$timeMid="0".$timeMid;
								}
								$stmt = $this->con->prepare("UPDATE `$table` SET `$timeFrom-$timeMid`='$subject',`$timeMid-$timeTo`='$subject',`reset_date`='$resetTime' WHERE day='$day';");
								//echo $timeMid;
							}else if (($timeTo-$timeFrom)==3){
								$timeMid1=number_format($timeFrom+1.00,2);
								if ($timeMid1<10){
									$timeMid1="0".$timeMid1;
								}
								$timeMid2=number_format($timeFrom+2.00,2);
								if ($timeMid2<10){
									$timeMid2="0".$timeMid2;
								}
								$stmt = $this->con->prepare("UPDATE `$table` SET `$timeFrom-$timeMid1`='$subject',`$timeMid1-$timeMid2`='$subject',`$timeMid2-$timeTo`='$subject',`reset_date`='$resetTime' WHERE day='$day';");
							}
							//return $stmt->execute();
							
							if ($stmt->execute()){
								return 1;
							}else{
								return 2;
							}
						}else {
							return 3;
						}
					
					}else {
						return 4;
					}
			
		}
		
		
		public function updateNonAcdmc ($day,$timeFrom,$timeTo,$subject,$date,$place){
			if($day=="Sunday" || $day=="Saturday"){
				return 6;
			}else if($timeFrom<=7 || $timeTo>=19){
					return 5;
				}else if($timeFrom!=12){
						if ($timeTo>$timeFrom){
							$table=$day;
							$resetTime=$date." ".$timeTo;
							$stmt_t=$this->con->prepare("CREATE TABLE IF NOT EXISTS `temp_$table` as (select * from $table);");
							$stmt_t->execute();
							/* $object=new DBOperation();
							$object-> */
							$this->eventScheduleNonAcdmc($table);
							
							if (($timeTo-$timeFrom)==1){
								$stmt = $this->con->prepare("UPDATE `$table` SET `$timeFrom-$timeTo`='$subject',`reset_date`='$resetTime' WHERE place='$place';");
							}else if (($timeTo-$timeFrom)==2){
								$timeMid=number_format($timeFrom+01.00,2);
								if ($timeMid<10){
									$timeMid="0".$timeMid;
								}
								$stmt = $this->con->prepare("UPDATE `$table` SET `$timeFrom-$timeMid`='$subject',`$timeMid-$timeTo`='$subject',`reset_date`='$resetTime' WHERE place='$place';");
								//echo $timeMid;
							}else if (($timeTo-$timeFrom)==3){
								$timeMid1=number_format($timeFrom+1.00,2);
								if ($timeMid1<10){
									$timeMid1="0".$timeMid1;
								}
								$timeMid2=number_format($timeFrom+2.00,2);
								if ($timeMid2<10){
									$timeMid2="0".$timeMid2;
								}
								$stmt = $this->con->prepare("UPDATE `$table` SET `$timeFrom-$timeMid1`='$subject',`$timeMid1-$timeMid2`='$subject',`$timeMid2-$timeTo`='$subject',`reset_date`='$resetTime' WHERE place='$place';");
							}
							//return $stmt->execute();
							
							if ($stmt->execute()){
								return 1;
							}else{
								return 2;
							}
						}else {
							return 3;
						}
					
					}else {
						return 4;
					}
			
		}
		
	}
?>