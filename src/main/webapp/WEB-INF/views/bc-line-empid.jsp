<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>RM Alert</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<style type="text/css">
		.width-element{
			width: 60% !important;
		}
		.padding-top{
			padding-top: 20px;
		}
		.hide-otp{
			display: none;
		}
		
	</style>
</head>	
<body>
	<form id="registerForm" action="/register/register">
		<div class="container-fluid text-center" id="register-container">
			<br/>
			<h5>RM Register</h5>
			<p>Please input you employee id</p>
			
			<div align="center" >
				<input type="text" class="form-control width-element" id="empId"></input>
			</div>
			
			<div align="center" class="padding-top">
				<button type="button" class="btn btn-success" onclick="register()" id="registerButton">Register</button>
<!-- 				<button type="button" class="btn btn-default" id="clearButton">Clear</button> -->
			</div>
			
			<div align="center" class="form-group padding-top hide-otp">
				 <label for="otpNumber" id="otpNumberLabel">OTP Number(Number 6)</label>
				 <input type="text" class="form-control width-element" id="otpNumber" ></input>
			</div>
			
			<div align="center" class="form-group hide-otp">
				 <button type="button" class="btn btn-success" onclick="confirmOTP()" id="confirmOTPButton">Confirm</button> 
			</div>
			
		</div>
		
		<div class="container-fluid text-center" id="success-container" style="display: none;">
			<br/>
			<h5>Register success...</h5>
		</div>
	</form>	
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script	src="../jquery/jquery-3.4.1.min.js"></script>
<!-- 		<script	src="../style/js/bootstrap.min.js"></script> -->
<!-- 		<script src="https://d.line-scdn.net/liff/1.0/sdk.js" /></script> -->
</body>
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> -->
<script>
	$( document ).ready(function() {
    	initPage();
    	
    	function initPage(){
    		//captcha sit key
    		//6LfIgcMUAAAAAJfZq3mDg2j9Hi3TQv0mxGa0BnrR
    	}
	});
		
	function confirmOTP() {
		console.log("confirm otp for register : " + "${register}");
		var dataObj = {
   				"userId" : "${register}",
   				"employeeId" : $("#registerForm #empId").val(),
   				"otpNumber" : $("#registerForm #otpNumber").val()
   		}
   		$.ajax({
   			  type: "POST",
   			  url: "/register/confirmOTP",
   			  data: dataObj,
   			  success: function(response){
   				 console.log(response);
   				 if("success" == response.status){
   					$("#register-container").hide(); 
   					$("#success-container").show();
   				 }else {
   					 alert("ไม่สามารถสมัครสมาชิกได้ รบกวนแจ้งผู้ดูแลระบบ");
   				 }	  
   			  }
   		});
	}
	
	function register(){
   		var dataObj = {
   				"userId" : "${register}",
   				"employeeId" : $("#registerForm #empId").val()
   		}
   		$.ajax({
   			  type: "POST",
   			  url: "/register/register",
   			  data: dataObj,
   			  success: function(response){
   				 console.log(response);
   				 if("success" == response.status){
   					 
   					 alert("ส่งรหัส OTP ไปยัง EMAIL ของท่านแล้ว");
   					 
   					 $("#registerForm #registerButton").hide();
   					 $("#registerForm #empId").attr("readonly","readonly");
   					 
   					 $(".hide-otp").show();
						
   				 }else {
   					 alert("ไม่สามารถสมัครสมาชิกได้ รบกวนแจ้งผู้ดูแลระบบ");
   				 }	  
   			  }
   		});
   	}
	
</script>
</html>

