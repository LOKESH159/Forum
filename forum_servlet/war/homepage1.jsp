<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<%
System.out.print("IAM IN HOMEPAGE1.jsp");
HttpSession session=request.getSession(false);
if(session!=null){	
	System.out.print("USERNAME VALUE IS="+(String)session.getAttribute("EMAIL"));
	System.out.print("PASSWORD VALUE IS="+(String)session.getAttribute("PASSWORD"));
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME PAGE</title>
<link rel="stylesheet" href="/css/Modal.css" type="text/css" />
<style>

a {
    text-decoration: none;
}
#questionBox{
padding:5px 100px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="/scripts/questions.js"></script>
<script>
$(document).ready(function() {
    $('#header').load('HomePage1.html');
});
function logOut(){
	//alert("IAM IN LOGOUT FUNCTION");
    if (confirm("ARE YOU SURE TO LOGOUT") == true) {
        return true;
    } else {
        return false;
    }
}

</script>
</head>
<body onload="retrieveQuestions()">
<div id="header"></div>
<div id="questions">
</div>
<div id="myModal" class="modal">
  <div class="modal-content">
    <div class="modal-header">
      <span id="close1" onclick="closeModal()">&times;</span>
     <center><h5>ASK A QUESTION</h5></center> 
    </div>
    <div class="modal-body">
    <table>
    <tr> <td> TECHNOLOGY RELATED TO:</td><td><select id="technology">
     <option value="DEFAULT">default</option>
  <option value="HTML">HTML</option>
  <option value="CSS">CSS</option>
  <option value="JAVASCRIPT">JAVASCRIPT</option>
  <option value="JAVA">JAVA</option>
</select></td></tr>
 <tr id="questionBox"><td></td><td><textarea rows="4" cols="50" name="question" onkeyup="return questionLength()" id="question" placeholder="Write Your Question"></textarea></td></tr></table>
   <center><input type="submit" value="Submit Question" onclick="submitQuestion()"/></center>
    </div>
  </div>
</div>

</body>
</html>
<%
}
else{
	response.sendRedirect("/Login.jsp");
}
%>
