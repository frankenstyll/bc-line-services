<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>RM Alert</title>
	<link href="../style/css/bootstrap.min.css" rel="stylesheet" >
	<style type="text/css">
		.container { 
				width: 50% !important; 
				height: 20% !important;
		}
	</style>
</head>	
<body>
	<form id="registerForm" action="/register/register">
		<div class="container">  
			<span class="d-block p-2">
				<label for="empId"> 	กรุณาระบุรหัสพนักงาน </label>
			</span>
			<span class="d-block p-2">
				<input type="text" class="form-control" id="empId" ></input>
			</span>
			
			<div class="d-block p-2">
				<div class="col-sm-12"><br/> </div>
			</div>
			<div class="d-block p-2">
				<div class="col-sm-12"><br/> </div>
			</div>
			<div class="d-block p-2">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
				 <label for="empId"> 	กรุณาระบุรหัสพนักงาน </label>
				</div>
				<div class="col-sm-3"></div>
			</div>
			
			<div class="d-block p-2">
			  	<div class="col-sm-3"></div>
				<div class="col-xs-6 col-sm-6 col-md-6">
				  	<div class="form-group">
					  	<input type="text" class="form-control" id="empId" ></input>
				  	</div>
			  	</div>
			  	<div class="col-sm-3"></div>
			</div>
			
			<div class="d-block p-2">
			  	<div class="col-sm-3"></div>
				<div class="col-xs-6 col-sm-6 col-md-6">
				  	<div class="form-group">
					  	${register}
				  	</div>
			  	</div>
			  	<div class="col-sm-3"></div>
			</div>
			
			<div class="d-block p-2">
				<div class="col-sm-3"></div>
				<div class="col-sm-6" style="text-align: center;">
					<div class="form-group">
						<button type="button" class="btn btn-success" onclick="register()" id="registerButton">Register</button>
						<button type="button" class="btn btn-default" id="clearButton">Clear</button>
					</div>
				</div>
				<div class="col-sm-3"></div>
			</div>
			
			<div class="d-block p-2">
				<div class="col-sm-3"></div>
				<div class="col-sm-6">
				 <label for="otpNumber" id="otpNumberLabel" style="display: none;">OTP Number(Number 6)</label>
				</div>
				<div class="col-sm-3"></div>
			</div>
			<div class="d-block p-2">
				<div class="col-sm-3"></div>
				<div class="col-sm-6" style="text-align: center;">
					<div class="form-group">
						<input type="text" class="form-control" id="otpNumber" style="display: none;"></input>
					</div>
				</div>
				<div class="col-sm-3"></div>
			</div>
			<div class="d-block p-2">
				<div class="col-sm-3"></div>
				<div class="col-sm-6" style="text-align: center;">
					<div class="form-group">
						<button type="button" class="btn btn-success" style="display: none;"
							onclick="confirmOTP()" id="confirmOTPButton">ยืนยัน</button>
					</div>
				</div>
				<div class="col-sm-3"></div>
			</div>
						
			<span class="d-block p-2 bg-primary text-white">d-block</span>
			<span class="d-block p-2 bg-dark text-white">d-block</span>			
		</div>
	</form>	
		
		<script	src="../jquery/jquery-3.4.1.min.js"></script>
		<script	src="../style/js/bootstrap.min.js"></script>
		<script src="https://d.line-scdn.net/liff/1.0/sdk.js" /></script>
</body>
<!-- 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script> -->
<script>
	$( document ).ready(function() {
    	initPage();
    	
    	function initPage(){
    		console.log('init page');
    		setTimeout(function(){
   			 	$('#empId').focus();
   			},0);
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
   					 window.close();						 
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
   					 $("#registerForm #clearButton").hide();
   					 
   					 $("#registerForm #otpNumberLabel").show();
					 $("#registerForm #otpNumber").show();
						
					 $("#confirmOTPButton").show();
					 
   				 }else {
   					 alert("ไม่สามารถสมัครสมาชิกได้ รบกวนแจ้งผู้ดูแลระบบ");
   				 }	  
   			  }
   		});
   	}
	
</script>
</html>

