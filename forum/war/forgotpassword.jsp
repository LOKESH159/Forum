<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FORGOT PASSWORD</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function() {
    $('#header').load('HomePage.html');
});
function checkEMail(){
	var eMail=$("#email").val();
	alert(eMail);
	if(eMail==""){
		alert("PLEASE ENTER EMAILID");
		return false;
	}
	var validEMail=validateEMail(eMail);
	if(validEMail){
		$.ajax({
			type: "POST",
			url : 'forgotPassword',
			data : {
				EMAIL : eMail
			},
			success : function(responseText) {			
				console.log("response "+responseText.mailStatus);
				//alert(responseText);
				if(responseText.mailStatus=='false'){
					alert("SORRY!AN ERROR OCCURED PLEASE TRY AGAIN");
					window.location ='/forgotpassword.jsp';
					return false;
									
				}
				else{
					alert("EMAIL WAS SENT TO YOUR MAIL ID");
					window.location ='/homepage.jsp';
					return true;	
				}
			}
		});
	}
	else{
		  alert('PLEASE ENTER VALID EMAILID');
		  return false;
	}
}
function validateEMail(eMail){
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
    if (reg.test(eMail) == false) 
    {
      
        return false;
    }
    else{
   	 return true;
    }
}
</script>
</head>
<body align="center">
<div id="header"></div>
<div id="forgot" style="padding:70px">
ENTER YOUR EMAIL:<input type="email" name="email" id="email"/><input type="submit" id="submit" value="SUBMIT" onclick="return checkEMail()">

</div></html>