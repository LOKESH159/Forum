package com.full.helper;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.full.dto.RetrieveQuestionDto;
import com.full.model.SearchQuestionsDao;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.gson.Gson;

public class SearchQuestions {
	Logger logger=Logger.getLogger(SearchQuestions.class.getName());
	public String  retrieveSearchQuestion(String technology){
		SearchQuestionsDao searchQuestionsDao=new SearchQuestionsDao();
		PreparedQuery pq=searchQuestionsDao.retrieveSearchQuestions(technology);
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

}
