<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOG IN PAGE</title>
<style>
#forgotPassword{
 color: red;

}</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/scripts/login.js"></script>
<script src="/scripts/questions.js"></script>
<script>
$(document).ready(function() {
    $('#header').load('HomePage.html');
});
</script>
</head>
<body style="align:center">
<div id="header"></div>
<table align="center" style="padding:100px">
<tr><td>EMAIL:</td></tr><tr><td><input type="email" name="email" id="email"/></td></tr>
<tr><td>PASSWORD:<tr><td><input type="password" name="password" id="password"/></td></tr>
<tr><td></td><td><a href="forgotpassword.jsp" id="forgotPassword"><ul>forgotpassword</ul></a></td></tr>
<tr><td style="align:center"><input type="submit" id="logIN" value="LOG IN" onclick="return logIN('homepage1.jsp')"/></td></tr></table>


</body>
</html>