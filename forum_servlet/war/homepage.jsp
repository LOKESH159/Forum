<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<%
System.out.print("IAM IN HOMEPAGE1.jsp");
HttpSession session=request.getSession(false);
if(session==null){	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME PAGE</title>
<style type="text/css">
a {
    text-decoration: none;
}</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="/scripts/questions.js"></script>
<script>
function askQuestion(){
	alert("YOU MUST BE LOGGED IN FIRST");
	
}
$(document).ready(function(e) {
    $('#header').load('HomePage.html');
});

</script>

</head>
<body onload="retrieveQuestions()">
<div id="header"></div>
<div id="questions">
</div>
</body>
</html>
<%
}
else{
	response.sendRedirect("/homepage1.jsp");
}
%>