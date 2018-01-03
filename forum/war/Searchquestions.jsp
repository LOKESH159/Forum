<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SEARCH QUESTIONS</title>
<link rel="stylesheet"  href="/css/Modal.css" type="text/css"/>
<style type="text/css">
a {
    text-decoration: none;
}</style>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="/scripts/questions.js"></script>
<script type="text/javascript">
function retrieveTechnology(){
	var technologyUrl=window.location.href;
	var url = new URL(technologyUrl);
	var technology=url.searchParams.get("technology");
	$.ajax({
		type: "GET",
		url : 'checkLogin',
		dataType:"json",
		success : function(responseText) {	
			if(responseText.loggedStatus){
				$('#header').load('HomePage1.html');
			}else{
               $('#header').load('HomePage.html');
			}
		}
	});
	$.ajax({
		type: "GET",
		url : 'searchQuestion',
		
		data : {
		TECHNOLOGY : technology
		},
		dataType:"json",
		success : function(responseText) {			
			console.log("response "+responseText);
			//alert(responseText);
			//alert(responseText.length);
			if(responseText==''){
				alert("THERE ARE NO QUESTIONS RELATED TO THE TECHNOLOGY");
				   $("#questions").html("THERE ARE NO QUESTIONS RELATED TO THE TECHNOLOGY");
				return false;
			}
			else{
				 var table='<table id="tblQuestions">';
				 table=table+'<tbody>';
				 for (var i = 0; i < responseText.length; ++i) {
				     var questionObject = responseText[i];
				 //  alert(questionObject.questionId);
				  // alert(questionObject.question);	
				    // var question = questionObject.question.replace(/\s/g, "-");
				     var url='Answers.jsp?questionId='+questionObject.questionId;
				 //   alert(url);
				     table=table+"<tr><td><input type='hidden' name='id' value="+questionObject.questionId+"/></td><td><a href="+url+">"+questionObject.question+"</a></td></tr>";
				 }
				    table=table+="</tbody></table>";
				     // alert(table);
				         $("#questions").html(table);
			}
		}
	});
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
				return true;
			}
		});
}
function askQuestion(){
	var modal = document.getElementById('myModal');
	 modal.style.display = "block";
}
function closeModal(){
	//alert("CLOSING MODAL");
	$("#myModal").css('display','none');
}
function questionLength(){
	var questionLength=$("#question").val().length;
//	alert(questionLength);
	if(questionLength>150){
		alert("QUESTION MUST BE WITHIN 150 CHARACTERS");
		return false;
	}
}</script>
</head>
<body onload="retrieveTechnology()">
<div id="header"></div>
<div id ="questions"></div>
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