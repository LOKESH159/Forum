<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PASSWORD RECOVERY</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
function checkPassword(){
	var password=$("#password").val();
	var confirmPassword=$("#confirmPassword").val();
	//alert(password);
	//alert(confirmPassword);
	if(password==""||confirmPassword==""){
		alert("PLEASE ENTER THE PASSWORD");
		return false;
	}
	var isSame=validatePassword(password,confirmPassword);
	if(isSame){
		var url_Browser=window.location.href;
	//	alert(url_Browser);
		var url = new URL(url_Browser);
	//	alert(url);	
		var customerId = url.searchParams.get("customerId");
		console.log(customerId);
		//alert(customerId);
		$.ajax({
			type: "POST",
			url : 'passwordRecovery',
			data : {
				CUSTOMERID : customerId,
				PASSWORD:password
			},
			dataType:"json",
			success : function(responseText) {			
				console.log("response "+responseText.updatePasswordStatus);
			//	alert(responseText);
				if(responseText.updatePasswordStatus){
					alert("YOU HAVE SUCCESSFULLY CHANGED YOUR PASSWORD");
					window.location ='/Login.jsp';
					return true;
				
				}
				else{
					alert("SORRY!SOME ERROR OCCURED PLEASE TRY AGAIN");
					//alert(url);
					window.location =url;
					return false;	
				}
	        }
	})
	}
	else{
		alert("PLEASE ENTER THE PASSWORD SAME");
		return false;
	}
}
function validatePassword(password,confirmPassword){
	if(password!=confirmPassword){
		
		return false;
	}
	else{
		return true;
	}
} 
	</script>
</head>
<body align="center">
<h1>PASSWORD RECOVERY</h1>
<table align="center">
<tr><td>ENTER PASSWORD:</td><td><input type="password" name="password" id="password"/></td></tr>
<tr><td>CONFIRM PASSWORD:</td><td><input type="password" name="confirmPassword" id="confirmPassword"/></td></tr></table>
<input type="submit" id="submit" value="SUBMIT" onclick="return checkPassword()">
</body>
</html>