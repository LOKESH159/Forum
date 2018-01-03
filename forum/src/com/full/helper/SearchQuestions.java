package com.full.helper;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.full.dto.RetrieveQuestionDto;
import com.full.model.SearchQuestionsDao;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.gson.Gson;
@Service
public class SearchQuestions {
	private SearchQuestionsDao searchQuestionsDao;
	@Autowired
	public void setSearchQuestionsDao(SearchQuestionsDao searchQuestionsDao) {
		this.searchQuestionsDao = searchQuestionsDao;
	}
	Logger logger=Logger.getLogger(SearchQuestions.class.getName());
	public String  retrieveSearchQuestion(String technology){
		PreparedQuery pq=searchQuestionsDao.retrieveSearchQuestions(technology);
		List<RetrieveQuestionDto> questionList=new LinkedList<RetrieveQuestionDto>();
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
