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
	<script src="https://www.google.com/recaptcha/api.js?onload=onloadCallback&hl=th" async defer></script>
</head>	
<body>
	<form id="registerForm" action="/register/validateRegister" method="post">
		<input type="hidden" name="userId" value="${userId }"/>
		<div class="container-fluid text-center" id="register-container">
			<br/>
			<h5>RM สมัครสมาชิก</h5>

			<p>กรุณาระบุรหัสพนักงาน <font color="red">*</font></p>
			
			<c:if test="${message ne null }">
				<div class="alert alert-danger">
					${message }
				</div>
			</c:if>
			
			<div align="center" >
				<input type="text" class="form-control width-element" id="employeeId" name="employeeId" maxlength="6"></input>
			</div>
			
			<div align="center" class="padding-top">
				<div class="g-recaptcha" data-sitekey="6LfIgcMUAAAAAJfZq3mDg2j9Hi3TQv0mxGa0BnrR" expired-callback=""></div>
			</div>
			
			<div align="center" class="padding-top">
				<button type="button" class="btn btn-primary" onclick="validateRegister()" id="registerButton">Send OTP</button>
<!-- 				<button type="button" class="btn btn-default" id="clearButton">Clear</button> -->
			</div>
			
		</div>
		
		<button type="button" class="btn btn-info btn-lg" id="showModal"
			data-toggle="modal" data-target="#dialogalert" style="display: none"  
		></button>
		
		<div id="dialogalert" class="modal fade bd-example-modal-sm" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
		    <div class="modal-dialog modal-sm">
		        <div class="modal-content">
		            <div class="modal-body">
		            	กรุณาระบุรหัสพนักงานและยืนยันตัวตน Captcha
		            </div>
		        </div>
		    </div>
		</div>
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
    		//6LfIgcMUAAAAAJfZq3mDg2j9Hi3TQv0mxGa0BnrR#"blank"")
    		//$('#smallModal').modal('show');
    	}
    	
	});
	
	//onload
 	var onloadCallback = function() {
	};
	
	function validateRegister(){
		
		var cap = $("[name='g-recaptcha-response']").val();
		var empId = $("#registerForm #employeeId").val();
		
		if( empId == "" || empId.length == 0 || null == empId ){
			$("#modal-content-text").val("กรุณาระบุรหัสพนักงาน");	
			$("#showModal").click();
			return false;
		}
		else if( cap == "" || cap.length == 0 || null == cap ){
			$("#modal-content-text").val("กรุณายืนยันตัวตน");	
			$("#showModal").click();
			return false;
		}
		else{
			
			$("#registerForm").submit();
			
			/*
	   		var dataObj = {
	   				"userId" : "${register}",
	   				"employeeId" : empId,
	   				"captcha" : cap
	   		}
	   		$.ajax({
	   			  type: "POST",
	   			  url: "/register/validateRegister",
	   			  data: dataObj,
	   			  success: function(response){
	   				 console.log(response);
	   				 if("success" == response.status){
	   					 
	   					 alert("ส่งรหัส OTP ไปยัง EMAIL ของท่านแล้ว");
	   					 
	   					// $("#registerForm #registerButton").hide();
	   					 $("#registerForm #empId").attr("readonly","readonly");
	   					 
	   					 $(".hide-otp").show();
							
	   				 }else {
	   					 alert("ไม่สามารถสมัครสมาชิกได้ รบกวนแจ้งผู้ดูแลระบบ");
	   				 }	  
	   			  }
	   		});
			*/
		}
   	}
	
</script>
</html>

