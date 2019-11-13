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
	
		<div class="container">  
	
			<div class="row">
				<h3 id='message'></h3>
			</div>
			
			<div class="row">
			  <div class="col-xs-12 col-sm-12 col-md-12">
			  	<div class="form-group">
				  	<input type="text" class="form-control"></input>
			  	</div>
			  </div>
			</div>
			
			<div class="row">
				<div class="form-group">
					<button type="submit" class="btn btn-success">Register</button>
					<button type="submit" class="btn btn-default">Clear</button>
				</div>
			</div>
				
		</div>
		
	</body>
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> -->
	<script src="https://d.line-scdn.net/liff/1.0/sdk.js" /></script>
	<script>
		var waiting = 'กรุณารอสักครู่...';
	
		$( document ).ready(function() {
	    	$('#message').text('กรุณาระบุรหัสพนักงาน');
	    	
		});
	</script>
</html>

