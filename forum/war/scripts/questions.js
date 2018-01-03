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
	//var span = document.getElementsByClassName("close")[0];
	 modal.style.display = "block";
}
function closeModal(){
	//alert("CLOSING MODAL");
	$("#myModal").css('display','none');
}
function submitQuestion(){
	alert("IAM IN SUBMIT QUESTION");
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
				//alert(responseText);
				$("#myModal").css('display','none');
				retrieveQuestions();
				return true;
			}
		});
}
function retrieveQuestions(){
	$.ajax({
		type: "Get",
		url : 'retrieveQuestions',
		dataType:"json",
		success : function(responseText) {	
         //  alert(responseText);
		 var table='<table id="tblQuestions">';
		 table=table+'<tbody>';
		// alert(responseText.length);
		 for (var i = 0; i < responseText.length; ++i) {
		     var questionObject = responseText[i];
		  //  alert(questionObject.questionId);
		  //  alert(questionObject.question);	
		     var question = questionObject.question.replace(/\s/g, "-");
		     var url='Answers.jsp?questionId='+questionObject.questionId;
		 //   alert(url);
		     table=table+"<tr><td><input type='hidden' name='id' value="+questionObject.questionId+"/></td><td><a href="+url+">"+questionObject.question+"</a></td></tr>";
		 }
		    table=table+="</tbody></table>";
		     // alert(table);
		         $("#questions").html(table);
		}
	});
}
function search(event){
	var tech=document.getElementById("mySearch").value;
	//alert(technology);
	if(event.keyCode==13){
		var technology=tech.toUpperCase();
		//alert(technology);
		window.location="/Searchquestions.jsp?technology="+technology;
		
	} 
}