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
<title>USER ASKED QUESTIONS</title>
<link rel="stylesheet"  href="/css/Modal.css" type="text/css" />
<style>
a {
    text-decoration: none;
}
#questionBox{
padding:5px 100px;
}
</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/scripts/questions.js"></script>
<script>
$(document).ready(function() {
    $('#header').load('HomePage1.html');
      retrieveQuestions();
});

function questionLength(){
	var questionLength=$("#question").val().length;
//	alert(questionLength);
	if(questionLength>150){
		alert("QUESTION MUST BE WITHIN 150 CHARACTERS");
		return false;
	}
}
function askQuestion(){
	var modal = document.getElementById('myModal');
	 modal.style.display = "block";
}
function closeModal(){
	//alert("CLOSING MODAL");
	$("#myModal").css('display','none');
}
function submitQuestion(){
//	alert("IAM IN SUBMIT QUESTION");
	var technology=$("#technology").val();
	var question=$("#question").val();
	if(technology=="DEFAULT"){
		alert("PLEASE SELECT THE TECHNOLOGY");
		return false;
	}
	if(question==""||question.length>150){
		alert("PLEASE ENTER THE QUESTION");
		return false;
	}

		$.ajax({
			type: "POST",
			url : 'addQuestion',
			data : {
			QUESTION:question,
			TECHNOLOGY:technology
			},
			success : function(responseText) {	
			//	alert(responseText);
				$("#myModal").css('display','none');
				retrieveQuestions();
				return true;
			}
		});
}
function retrieveQuestions(){
	var questionUrl=window.location.href;
	var url = new URL(questionUrl);
	//alert(url);	
var  value = url.searchParams.get("required");
//alert("VALUE IS="+value);
	$.ajax({
		type: "Get",
		url : 'user/retrieveQuestions',
		data:{
			 QUESTIONS:"USER",
			 REQUIRED: value
			},
			dataType:"json",
		success : function(responseText) {	
	//	alert(responseText);
		/*var response=JSON.parse(responseText);
		alert("response ="+response);
		for (var i = 0; i < response.length; ++i) {
		     var questionObject = responseText[i];
		    alert(questionObject.questionId);
		    alert(questionObject.question);	
		 } */
		 var table='<table id="tblQuestions">';
		 table=table+'<tbody>';
		 for (var i = 0; i < responseText.length; ++i) {
		     var questionObject = responseText[i];
		   //  alert(questionObject.questionId);
		   //  alert(questionObject.question);	
		     var url='Answers.jsp?questionId='+questionObject.questionId;
		  //  alert(url);
		     table=table+"<tr><td><input type='hidden' name='id' value="+questionObject.questionId+"/></td><td><a href="+url+">"+questionObject.question+"</a></td></tr>";
		 }
		    table=table+="</tbody></table>";
		     // alert(table);
		         $("#questions").html(table);
		}
	});
}
</script>
</head>
<body>
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
