<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Answers Page</title>
<style type="text/css">
#question {
	font-family: Arial;
	font-size: 24px;
	font-weight: bold;
	color: black;
}
#questionBox{
padding:5px 100px;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/scripts/login.js"></script>
<script src="/scripts/questions.js"></script>
<link rel="stylesheet" href="/css/Modal.css" type="text/css" />
<style type="text/css">
.arrow-up {
	width: 0;
	height: 0;
	border-left: 20px solid transparent;
	border-right: 20px solid transparent;
	cursor: pointer;
	border-bottom: 20px solid gray;
}

.arrow-down {
	width: 0;
	height: 0;
	border-left: 20px solid transparent;
	border-right: 20px solid transparent;
	cursor: pointer;
	border-top: 20px solid gray;
}

td.bordered {
	border-bottom: 1px solid #000;
	border-collapse: collapse;
}
.commentsBox{
padding-left:100px;
}
</style>
<script type="text/javascript">
var questionId,answerId,userLogged;
$(document).ready(function() {
	$.ajax({
		type: "GET",
		url : 'checkLogin',
		dataType:"json",
		success : function(responseText) {	
			//alert(responseText.trim());
			//alert(responseText.length);
			//var response=responseText.trim();
			if(responseText.loggedStatus){
				userLogged=true;
				$('#header').load('HomePage1.html');
			}else{
				userLogged=false;
               $('#header').load('HomePage.html');
			}
		}
	});
});
function retrieveQuestionId(){
	$("#submitAnswer").css('display','none');
	//alert("ANSWERS PAGE");
	var questionUrl=window.location.href;
	var url = new URL(questionUrl);
//	alert(url);	
 questionId = url.searchParams.get("questionId");
//	var question= url.searchParams.get("question");
//	alert(question);
//	var questionReplace=question.replace(/-/g, " ");
	console.log(questionId);
	//alert(questionId);
	$.ajax({
		type: "GET",
		url : 'retrieveIdQuestion',
		data : {
		QUESTIONID:questionId 
		},
		dataType:"json",
		success : function(responseText) {	
			//alert(responseText);
			if(responseText.idQuestion=='false'){
				$("#question").html("THERE ARE NO QUESTIONS");
			}
			else{
				$("#question").html(responseText.idQuestion);
				retrieveAnswers();
				$("#submitAnswer").css('display','block');
			}
			//$("#myModal").css('display','none');
			//return true;
		}
	});

	
}
function retrieveAnswers(){
	$.ajax({
		type: "Get",
		url : 'retrieveAnswers',
		cache: false,
		data:{
			QUESTIONID:questionId
		},
		dataType:"json",
		success : function(responseText) {	
		//alert(responseText);
     	var table="<hr/>";
		    var value = responseText["ANSWERS"];		   
		//  alert(value);
		  if(value!=null || value!=''){
			  table=table+"<h3>Answers</h3>";
		  }
		    var val=JSON.parse(value);
		//   alert(val);
		    for(var y in val){
		    	var answerObject=val[y];
		    //	alert("Y VALUE IS ="+y);
		    	//alert("Z VALUE IS ="+answerObject);
		    	//alert(answerObject.answeredBy);
		    	//alert(answerObject.answer);
		    	//alert(answerObject.answerId);
		    	var comments=responseText["COMMENTS"];
		    //	alert("COMMENTS VALUE IS="+comments);
		    	var comment=JSON.parse(comments);
		    //	alert(comment);
		     if(answerObject.validVote){
			    	
			    		table=table+"<img src='/images/check.png' alt='' width='2%' height='2%'/>";
				    }  
		       table=table+"<table><tr><td><div class='arrow' ><a class='arrow-up' onclick='votes(\""+answerObject.answerId+"\",1)'></a></div></td></tr><tr><td><div style='padding:17px' id="+answerObject.answerId+">"+answerObject.noOfVotes+"</td><td>"+answerObject.answer+"</div></td></tr><tr><td><div><a class='arrow-down' onclick='votes(\""+answerObject.answerId+"\",0)'></a></div></td></tr><tr><td></td><td></td><td><div style='color:green'>AnswerdBy</div></td></tr><tr><td></td><td></td><td><div style='color:blue'>"+answerObject.answeredBy+"</div></td></div></tr></table><hr width='80%'/>";	    
		       if(comments!=null || comments!=''){
		    	   table=table+"<h4 style='padding-left:100px'>Comments</h4>";
		       }
		    	for(var answer in comment){
		    		//alert(answer);
		    		if(answerObject.answerId==answer){
		    			//alert("IAM SAME");
		    			var commentAns=comment[answer];
		    			//alert(commentAns);
		    			
		    			for(a1 in commentAns){		    			
		    				var mainComment=commentAns[a1];
		    			//	alert(mainComment.commentedBy);
		    			//	alert(mainComment.comment);	    				
		    			    table=table+"<div style='padding-left:100px'>"+mainComment.comment+"<a style='color:blue'>-"+mainComment.commentedBy+"</a></div>"; 				 
			    			}
		    		
		    			  //   alert(table);
		    		   break;   				    			
		    		}
		    		else{
		    			//("IAM NOT SAME");
		    		}
		    	} 
		    	 var answerID=answerObject.answerId+"123";
		    	//alert(answerID);
		    	 
		  	  table=table+"<div  id='commentAnswer'><div class='commentsBox' id="+answerID+"><textarea rows='4' cols='40' name='comment' onkeyup='return commentLength()' id='comment' placeholder='Write Your Comment'></textarea><input type='submit' value='Submit Comment' onclick='return submitComment()'/></div><a style='color:blue;padding-left:900px' onclick='addComment(\""+answerObject.answerId+"\")'>add a coment</a></div>";
			     table=table+"<hr/>"
			    // alert(table);
		    }
		    $("#answers").html(table);
		    $(".commentsBox").hide();

		}
	});
}
function addComment(answer){
	//alert(answer);
	answerId=answer;
	var answerID="#"+answerId+"123";
	if(userLogged){
$(answerID).show();

	return false;
	}
	else{
		alert("YOU NEED TO LOGIN FIRST");
		$("#loginModal").css("display","block");
		return false;
	}
}
function submitComment(){
	var comment=$("#comment").val();
	if(comment.length==0){
		alert("PLEASE ENTER THE COMMENT");
		return false;
	}
	
if(comment.length<10||comment.length>150){
	alert("COMMNENT MUST BE BETWEEN 10 AND 150 CHARACTERS");
	return false;
}

	$.ajax({
		type: "Post",
		url : 'addComment',
		cache:false,
		data:{
			ANSWERID:answerId,
			QUESTIONID:questionId,
			COMMENT:comment
			
		},
		dataType:"json",
		success : function(responseText) {	
	       alert(responseText.commentStatus);
	       $("#commentModal").css("display","none");
	       $("#comment").val("");
	       retrieveAnswers();
		}
	});
}

function submitAnswer(){
	//alert("IAM IN SUBMIT ANSWER");
	var answer=$("#answer").val();
	if(answer==""){
		alert("PLEASE ENTER THE ANSWER");
		return false;
	}
		if(userLogged){
				$.ajax({
					type: "Post",
					url : 'addAnswer',
					cache:false,
					data:{
						ANSWER:answer,
						QUESTIONID:questionId
					},
					dataType:"json",
					success : function(responseText) {						  
					if(responseText.answerStatus=='TRUE'){
						alert("YOU CANNOT ANSWER YOUR QUESTION");
						return false;
					}
					else{
						alert(responseText.answerStatus);
						$("#answer").val("");
					//	alert("IAM calling retrieve answers");
					    retrieveAnswers();
					}
					}
				});
			}
			else{
				alert("You need to Login First");
				$("#loginModal").css('display','block');
				return false;
			}

}

function answerLength(){
	var answerLength=$("#answer").val().length;
//	alert(questionLength);
	if(answerLength>150){
		alert("ANSWER MUST BE WITHIN 150 CHARACTERS");
		return false;
	}
}
function commentLength(){
	var commentLength=$("#comment").val().length;
	if(commentLength>150){
		alert("COMMENT MUST BE WITHIN 150 CHARACTERS");
		return false;
	}
}
function askQuestion(){
	var modal = document.getElementById('myModal');
	//var span = document.getElementsByClassName("close")[0];
	 modal.style.display = "block";
}
function closeModal(){
	//alert("CLOSING MODAL");
	$("#myModal").css('display','none');
	$("#loginModal").css('display','none');
	$("#commentModal").css("display","none");
}
function votes(answerId,rate){
	//alert(answerId);
	//alert(rate);
	//var id=document.getElementById(answerId).innerHTML;
	//alert(id);

	if(rate==1){
	
	$(".arrow-up").css("border-bottom", "20px solid green");
	}
	else{
	
	$(".arrow-down").css("border-top", "20px solid red");
	}
	if(userLogged){
		$.ajax({
			type: "Post",
			url : 'addVote',
			data:{
				ANSWERID:answerId,
				QUESTIONID:questionId,
				VOTE:rate
			},
			dataType:"json",
			cache:false,
			success : function(responseText) {	
			//alert(responseText);
			 var str=responseText.voteStatus;
			 var res = str.split("@");
			 $(".arrow-up").css("border-bottom"," 20px solid gray");
			 $(".arrow-down").css("border-top"," 20px solid gray");
			// alert(res.length)
			 if(res.length>1){
				 alert("THANKS FOR VOTING");
				
				document.getElementById(answerId).innerHTML=res[1];
				 
			 }
			 else{
				 alert(responseText.voteStatus);
			 }
			}
		});
	}
	else{
		 $(".arrow-up").css("border-bottom"," 20px solid gray");
		 $(".arrow-down").css("border-top"," 20px solid gray");
		alert("You need to Login First");
		$("#loginModal").css('display','block');
		return false;
	}
}
function logIn(){
	logIN(window.location.href);
}
</script>
</head>
<body onload="retrieveQuestionId()">
	<div id="header"></div>
	<div id="question"></div>
	<div id="answers"></div>
	<div id="submitAnswer">
		<textarea rows="4" cols="50" name="answer"
			onkeyup="return answerLength()" id="answer"
			placeholder="Write Your Answer"></textarea>
		<center>
			<input type="submit" value="Post Your Answer"
				onclick="submitAnswer()" />
		</center>
	</div>
	<div id="loginModal" class="modal">
		<div class="modal-content" style="width: 40%; height: 30%">
			<div class="modal-header">
				<span id="close1" onclick="closeModal()">&times;</span>
			</div>
			<div class="modal-body">
				<table align="center">
					<tr>
						<td>Enter Your MailID:</td>
						<td><input type="text" id="email" /></td>
					</tr>
					<tr>
						<td>Enter Your Password:</td>
						<td><input type="password" id="password"></td>
					</tr>
				</table>
				<center>
					<input type="submit" value="Log In" onclick="return logIn()" />
				</center>
			</div>
		</div>
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