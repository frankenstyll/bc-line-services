<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>RM Alert</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<style type="text/css">
		.width-element{
			width: 61% !important;
		}
		.padding-top{
			padding-top: 20px;
		}
		.hide-otp{
			display: none;
		}
		
	</style>
	<script src="https://d.line-scdn.net/liff/1.0/sdk.js" /></script>
</head>	
<body>
	<form id="registerForm" >
		<div class="container-fluid text-center" id="register-container">
			
			<br/>
			<h5>RM สมัครสมาชิก</h5>
			
			<p>กรุณาระบุ OTP <font color="red">*</font></p>
			
			<div class="alert alert-danger" style="display: none;" id="alert-danger">
				
			</div>
			
			<div align="center" class="form-group">
				 <input type="text" class="form-control width-element" id="otpNumber" ></input>
			</div>
			
			<div align="center" class="form-group">
				 <button type="button" class="btn btn-primary" onclick="confirmOTP()" id="confirmOTPButton">Confirm</button>
				 <button type="button" class="btn btn-success" onclick="resetOTP()" id="resetOTPButton">ส่ง OTP อีกครั้ง</button> 
			</div>
			
		</div>
		
		<button type="button" class="btn btn-info btn-lg" id="showModal" 
			data-toggle="modal" data-target="#dialogalert" style="display: none"></button>
		
		<div id="dialogalert" class="modal fade bd-example-modal-sm" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		    <div class="modal-dialog modal-sm">
		        <div class="modal-content">
		            <div class="modal-body" id="modal-body">
		            	Reset OTP เรียบร้อยแล้ว
		            </div>
		        </div>
		    </div>
		</div>
		
		<div class="container-fluid text-center" id="success-container" style="display: none;">
			<br/>
			<h5>Register success...</h5>
		</div>
		
		<input type="hidden" name="refNumber" id="refNumber" value="${ref_number }"/>
		<input type="hidden" name="employeeId" id="employeeId" value="${employee_id }"/>
		<input type="text" name="userId" id="userId"/>
		
	</form>	
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
	<script	src="../jquery/jquery-3.4.1.min.js"></script>
<!-- 		<script src="https://d.line-scdn.net/liff/1.0/sdk.js" /></script> -->
</body>
<script>
	$( document ).ready(function() {
    	initPage();
    	
    	function initPage(){
    		//captcha sit key
    		//6LfIgcMUAAAAAJfZq3mDg2j9Hi3TQv0mxGa0BnrR
    	}
    	
    	liff.init( data => {
		    // Now you can call LIFF API
		    const userId = data.context.userId;
		    $("#userId").val(userId);
		  },
		  err => {
		    // LIFF initialization failed
		  }
		);
    	
	});
	
	function resetOTP(){
		var dataObj = {
				"employeeId" : $("#registerForm #employeeId").val(),
   				"refNumber" : $("#registerForm #refNumber").val()
   		}
   		$.ajax({
   			  type: "POST",
   			  url: "/register/resetOTP",
   			  data: dataObj,
   			  success: function(response){
   				 console.log(response);
   				 if("0" == response.status){
   					 
   					$("#registerForm #refNumber").val(response.ref_number);
   					
   					$("#modal-body").text("Reset OTP เรียบร้อยแล้ว");
   					$("#showModal").click();
   				 }else {
   					$("#modal-body").text("ไม่สามารถทำรายการได้ รบกวนแจ้งผู้ดูแลระบบ");
   					$("#showModal").click();
   				 }	  
   			  }
   		});
		
	}
	
	function confirmOTP() {
		console.log("confirm otp for register : " + "${register}");
		var dataObj = {
   				"userId" : $("#registerForm #userId").val(),
   				"employeeId" : $("#registerForm #employeeId").val(),
   				"otp" : $("#registerForm #otpNumber").val(),
   				"refNumber" : $("#registerForm #refNumber").val()
   		}
   		$.ajax({
   			  type: "POST",
   			  url: "/register/confirmOTP",
   			  data: dataObj,
   			  success: function(response){
   				 console.log(response);
   				 if("0" == response.status){
   					$("#register-container").hide(); 
   					$("#success-container").show();
   				 }else {
   					 
   					$("#registerForm #alert-danger").text(response.message);
   					$("#registerForm #alert-danger").show();
   				 }	  
   			  }
   		});
	
   	}
	
</script>
</html>

