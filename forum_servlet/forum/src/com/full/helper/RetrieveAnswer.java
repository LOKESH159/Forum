package com.full.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import com.full.dto.Mailer;
import com.full.dto.RetrieveAnswerDto;
import com.full.dto.RetrieveCommentsDto;
import com.full.model.RetrieveAnswerDao;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class RetrieveAnswer {
	public  Logger logger = Logger.getLogger(RetrieveAnswer.class.getName()); 
public JsonObject retrieveAnswers(String questionID){
	PreparedQuery pq=RetrieveAnswerDao.retrieveAnswer(questionID);
	System.out.println("IAM IN RETRIEVEQUESTION");
	List<RetrieveAnswerDto> answerList=new ArrayList<RetrieveAnswerDto>();
	for(Entity entity:pq.asIterable()){
		System.out.println("I GOT THE VALUES IN RETRIEVE QUESTION");
		//System.out.println(entity);
		String answeredBy=(String)entity.getProperty("ANSWERED_BY");
		String answer=(String)entity.getProperty("ANSWER");
		String answerId=(String)entity.getProperty("ANSWER_ID");
		Long noOfVotes=(Long)entity.getProperty("NOOFVOTES");
		Boolean  validAnswer=new Boolean(false);
		try{		
	     validAnswer=(Boolean)entity.getProperty("VALIDANSWER");
	     logger.info("I DIDN't got null pointer exception");
		}
		catch(NullPointerException e){
			logger.info("I GOT NULL POINTER EXCEPTION");
		    
		}
		System.out.println(answeredBy);
		System.out.println(answer);
		System.out.println(answerId);
		System.out.println(noOfVotes);
		System.out.println(validAnswer);
		RetrieveAnswerDto retrieveAnswerDto =new RetrieveAnswerDto();
		retrieveAnswerDto.setAnsweredBy(answeredBy);
		retrieveAnswerDto.setAnswer(answer);
		retrieveAnswerDto.setAnswerId(answerId);
		retrieveAnswerDto.setNoOfVotes(noOfVotes);
		logger.info("THE VALID VOTE IAM SETTING ="+validAnswer);
		retrieveAnswerDto.setValidVote(validAnswer);
		answerList.add(retrieveAnswerDto);

	}  
	 pq=RetrieveAnswerDao.retrieveComments(questionID);
		HashMap<String,List<RetrieveCommentsDto>> hm=new HashMap<>();
		for(Entity entity:pq.asIterable()){
			System.out.println("I GOT THE VALUES IN RETRIEVE QUESTION");
		    System.out.println(entity);
			String commentedBy=(String)entity.getProperty("COMMENTED_By");
			String comment=(String)entity.getProperty("COMMENT");
			String answerId=(String)entity.getProperty("ANSWER_ID");
			System.out.println(commentedBy);
			System.out.println(comment);
			System.out.println(answerId);	
			if(hm.containsKey(answerId)){
				System.out.println("I ALREADY HAVE ANSWER LIST");
		     List<RetrieveCommentsDto> li=(List<RetrieveCommentsDto>)hm.get(answerId);
		     RetrieveCommentsDto retrieveCommentsDto=new RetrieveCommentsDto();
		     retrieveCommentsDto.setComment(comment);
		     retrieveCommentsDto.setCommentedBy(commentedBy);
		     li.add(retrieveCommentsDto);
	     	 hm.put(answerId,li);
			}
			else{
				System.out.println("I ADDING NEW ANSWER LIST");
			List<RetrieveCommentsDto> li=new ArrayList<RetrieveCommentsDto>();
			RetrieveCommentsDto retrieveCommentsDto=new RetrieveCommentsDto();
		     retrieveCommentsDto.setComment(comment);
		     retrieveCommentsDto.setCommentedBy(commentedBy);
		     li.add(retrieveCommentsDto);
	     	 hm.put(answerId,li);
			}
		

		} 
	Gson gson=new Gson();
String gsonAnswers=	gson.toJson(answerList);
String gsonComments=gson.toJson(hm);
System.out.println(gsonComments);
	System.out.println(gsonAnswers);
	JsonObject jsonObject=new JsonObject();
	jsonObject.addProperty("ANSWERS",gsonAnswers);
	jsonObject.addProperty("COMMENTS",gsonComments);
		return jsonObject;
	}

}
