<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SIGN UP</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/scripts/questions.js"></script>
<script>
$(document).ready(function() {
    $('#header').load('HomePage.html');
});
 function validateEMail(eMail){
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
     if (reg.test(eMail) == false) 
     {
         alert('PLEASE ENTER VALID EMAILID');
         return false;
     }
     else{
    	 return true;
     }
}

function validatePassword(password,confirmPassword){
	if(password!=confirmPassword){
		alert("PLEASE ENTER THE PASSWORD SAME");
		return false;
	}
	else{
		return true;
	}
} 
function sign(){
//	alert("LOKESH");
	var userName=$("#userName").val();
	var eMail=$("#email").val()
	var password=$("#password").val();
	var confirmPassword=$("#confirmPassword").val();
 	//alert(userName);
	//alert(eMail);
	//alert(password);
	//alert(confirmPassword);
	var validMail=validateEMail(eMail);

	//alert("all are valid");
	//alert("IAM CALLING AJAX CALL"); 
	if(validMail){
		var validPassword=validatePassword(password,confirmPassword);
		if(validPassword){
	$.ajax({
		type: "POST",
		url : 'CheckEMailServlet',
		data : {
			EMAIL : eMail
		},
		success : function(responseText) {			
			console.log("response "+responseText);
			//alert(responseText);
			if(responseText=="true"){
				alert("EMAIL ALREADY EXISTED");
				return false;
			}
			else{
				$.ajax({
					type: "POST",
					url : 'SignUp',
					data : {
						EMAIL : eMail,
						USERNAME:userName,
						PASSWORD:password,
						CONFIRMPASSWORD:confirmPassword
					},
					success : function(responseText) {	
						//alert(responseText);
						window.location = '/Login.jsp';
					}
				});
			  }
		}
});
		}
		else{
			return false;
		}
	}
	else{
		return false;
	}
}
	</script>
</head>

<body align="center">
<div id="header"></div>
<table align="center" style="padding:100px">
<tr><td>ENTER USERNAME:</td><td><input type="text" name="username" id="userName"/></td></tr>
<tr><td>ENTER EMAIL:</td><td><input type="email" name="email" id="email"/></td></tr>
<tr><td>ENTER PASSWORD:</td><td><input type="password" name="password" id="password"/></td></tr>
<tr><td>CONFIRM PASSWORD:</td><td><input type="password" name="confirmPassword" id="confirmPassword"/></td></tr>
<tr><td style="align:center"><input type="submit" id="signUp" value="SIGN UP" onclick="return sign()"/></td></tr>
</table>

</body>

</html>