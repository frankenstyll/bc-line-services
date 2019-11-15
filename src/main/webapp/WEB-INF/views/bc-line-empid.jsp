<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Input employee id</title>

<!-- 		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"> -->
<!-- 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script> -->
<!-- 		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script> -->
		
		<link rel="stylesheet" href="../style/css/bootstrap.min.css">
		<script	src="../script/jquery-3.4.1.min.js"></script>
		<script	src="../style/js/bootstrap.min.js"></script>
		
		<style type="text/css">
			.container { 
				width: 80% !important;
			}
		</style>
	</head>	
	<body>
		<form id="registerForm" action="/bcbot/register">
		<div class="container">  
			<div class="row">
				<div class="col-sm-12"><br/> </div>
			</div>
			<div class="row">
				<div class="col-sm-12"><br/> </div>
			</div>
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
				 <label for="empId"> 	กรุณาระบุรหัสพนักงาน </label>
				</div>
				<div class="col-sm-3"></div>
			</div>
			
			<div class="row">
			  	<div class="col-sm-3"></div>
				<div class="col-xs-6 col-sm-6 col-md-6">
				  	<div class="form-group">
					  	<input type="text" class="form-control" id="empId" autofocus ></input>
				  	</div>
			  	</div>
			  	<div class="col-sm-3"></div>
			</div>
			
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
				 <label for="empId"> E-MAIL </label>
				</div>
				<div class="col-sm-3"></div>
			</div>
			<div class="row">
			  	<div class="col-sm-3"></div>
				<div class="col-xs-6 col-sm-6 col-md-6">
				  	<div class="form-group">
					  	<input type="text" class="form-control" id="emailId" ></input>
				  	</div>
			  	</div>
			  	<div class="col-sm-3"></div>
			</div>
			
			<div class="row">
			  	<div class="col-sm-3"></div>
				<div class="col-xs-6 col-sm-6 col-md-6">
				  	<div class="form-group">
					  	${register}
				  	</div>
			  	</div>
			  	<div class="col-sm-3"></div>
			</div>
			
			<div class="row">
				<div class="col-sm-3"></div>
				<div class="col-sm-6" style="text-align: center;">
					<div class="form-group">
						<button type="button" class="btn btn-success">Register</button>
						<button type="button" class="btn btn-default">Clear</button>
					</div>
				</div>
				<div class="col-sm-3"></div>
			</div>
				
		</div>
		</form>
	</body>
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> -->
	<script src="https://d.line-scdn.net/liff/1.0/sdk.js" /></script>
	<script>
		var waiting = 'กรุณารอสักครู่...';
	
		$( document ).ready(function() {
	    	$('#message').text('กรุณาระบุรหัสพนักงาน');

	    	document.getElementById("empId").focus();
	    	initPage();
	    	
	    	function initPage(){
	    		//$("#registerForm #empId").focus();
	    		//document.getElementById("empId").focus();
	    	}
	    	
		});
		
	</script>
</html>

