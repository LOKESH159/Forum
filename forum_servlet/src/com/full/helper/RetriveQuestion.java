package com.full.helper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import com.full.dto.RetrieveQuestionDto;
import com.full.model.RetrieveQUestionDao;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;



public class RetriveQuestion {
	Logger logger=Logger.getLogger(RetriveQuestion.class.getName());
	public String retrieveQuestion(String eMail){
	PreparedQuery pq=RetrieveQUestionDao.retrieveQuestion(eMail);
	System.out.println("IAM IN RETRIEVEQUESTION");
	List<RetrieveQuestionDto> questionList=new ArrayList<RetrieveQuestionDto>();
	for(Entity entity:pq.asIterable()){
		System.out.println("I GOT THE VALUES IN RETRIEVE QUESTION");
		//System.out.println(entity);
		String question=(String)entity.getProperty("QUESTION");
		String uuid=(String)entity.getProperty("QUESTIONID");
		System.out.println(uuid);
		System.out.println(question);
		RetrieveQuestionDto retrieveQuestionDto =new RetrieveQuestionDto();
		retrieveQuestionDto.setQuestionId(uuid);
		retrieveQuestionDto.setQuestion(question);
		questionList.add(retrieveQuestionDto);
	}  
	logger.info("IAM IN RETRIEVE QUESTION HELPER BEFORE");
	Gson gson=new Gson();
	logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION ");
    String gsonQuestion=gson.toJson(questionList);
    logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION JSON");
	System.out.println(gsonQuestion);
	return gsonQuestion;
	}
	
	
	
	public String retrieveAnswerQuestion(String eMail){
		PreparedQuery pq=RetrieveQUestionDao.retrieveAnswerQuestionDao(eMail);
		Set<String> set=new HashSet<String>();
		 String gsonQuestion=null;
		if(pq!=null){
			System.out.println("I GOT ANSWERS FROM DB");
		  for(Entity entity:pq.asIterable()){
			System.out.println("I GOT THE VALUES IN RETRIEVE ANSWER QUESTION");
			//System.out.println(entity);
			String uuid=(String)entity.getProperty("QUESTION_ID");
			System.out.println(uuid);
			set.add(uuid);
		}  
		System.out.println("SET VALUE IS :"+set);
		Iterator iterator=set.iterator();
	List<Filter> list=new ArrayList<Filter>();
		while(iterator.hasNext()){
		String uuid=(String)iterator.next();
		Filter keyFilter = new FilterPredicate("QUESTIONID",FilterOperator.EQUAL, uuid);
		list.add(keyFilter);
		}
         pq=RetrieveQUestionDao.retrieveAnswerQuestionDao(list);
         if(pq!=null){
        	 System.out.println("I GOT VALUES THROUGH OR CONDITION ALSO");
         List<RetrieveQuestionDto> questionList=new ArrayList<RetrieveQuestionDto>();
     	for(Entity entity:pq.asIterable()){
     		System.out.println("I GOT THE VALUES IN RETRIEVE QUESTION");
     		//System.out.println(entity);
     		String question=(String)entity.getProperty("QUESTION");
     		String uuid=(String)entity.getProperty("QUESTIONID");
     		System.out.println(uuid);
     		System.out.println(question);
     		RetrieveQuestionDto retrieveQuestionDto =new RetrieveQuestionDto();
     		retrieveQuestionDto.setQuestionId(uuid);
     		retrieveQuestionDto.setQuestion(question);
     		questionList.add(retrieveQuestionDto);
     	}  
     	logger.info("IAM IN RETRIEVE QUESTION HELPER BEFORE");
     	Gson gson=new Gson();
     	logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION ");
         gsonQuestion=gson.toJson(questionList);
         logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION JSON");
     	System.out.println(gsonQuestion);
     	
		}
		}
		return gsonQuestion;
		
	}
}
