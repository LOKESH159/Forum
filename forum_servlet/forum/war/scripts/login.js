function logIN(page){
	//alert("IAM IN LOGIN FUNCTION");
	var eMail=$("#email").val();
	var password=$("#password").val();
	//alert(eMail);
	//alert(password);
	if(eMail==""){
		alert("PLEASE ENTER EMAILID");
		return false;
	}
	if(password==""){
		alert("PLEASE ENTER PASSWORD");
		return false;
	}

	var validEMail=validateEMail(eMail);
	if(validEMail){
			$.ajax({
				type: "POST",
				url : 'CheckUserServlet',
				data : {
					EMAIL : eMail,
					PASSWORD:password
				},
				success : function(responseText) {			
					console.log("response "+responseText);
					//alert(responseText);
					var response=responseText.trim();
				/* 	for(var i=0;i&lt;responseText.length;responseText++){
						console.log()
					} */
				console.log(response.length);
					if(response=="false"){
						
						alert("PLEASE ENTER VALID EMAIL/PASSWORD");
						return false;
										
					}
					else{
						window.location =page;
						return true;	
					}
				}
			});
	}
	else{
		 alert('PLEASE ENTER VALID EMAIL');
		return false;
	}	
}
 function validateEMail(eMail){
	 var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z_\-\.])+\.([A-Za-z]{2,4})$/;
     if (reg.test(eMail) == false) 
     {       
         return false;
     }
     else{
    	 return true;
     }
}