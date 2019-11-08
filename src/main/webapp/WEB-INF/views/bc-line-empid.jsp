<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>Input employee id</title>
	</head>	
	<body>
		<h3 id='message'></h3>
		<div>
			<input type="text" ></input>
		</div>
	</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
	<script src="https://d.line-scdn.net/liff/1.0/sdk.js" /></script>
	<script>
		var waiting = 'กรุณารอสักครู่...';
	
		$( document ).ready(function() {
	    	$('#message').text('กรุณาระบุรหัสพนักงาน');
	    	
		});
	</script>
</html>

