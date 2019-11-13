<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Input employee id</title>
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
		<script	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		<style type="text/css">
			.container { 
				width: 80% !important;
			}
		</style>
	</head>	
	<body>
	
		<div class="container">  
			<form style="width:auto">  
		
				<div class="row">
				  <div class="col-xs-12 col-sm-6 col-md-8">.col-xs-12 .col-sm-6 .col-md-8</div>
				  <div class="col-xs-6 col-md-4">.col-xs-6 .col-md-4</div>
				</div>
				<div class="row">
				  <div class="col-xs-6 col-sm-4">.col-xs-6 .col-sm-4</div>
				  <div class="col-xs-6 col-sm-4">.col-xs-6 .col-sm-4</div>
				  <!-- Optional: clear the XS cols if their content doesn't match in height -->
				  <div class="clearfix visible-xs-block"></div>
				  <div class="col-xs-6 col-sm-4">.col-xs-6 .col-sm-4</div>
				</div>
			
				<h3 id='message'></h3>
				<div>
					<input type="text" ></input>
				</div>
			</form>
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

