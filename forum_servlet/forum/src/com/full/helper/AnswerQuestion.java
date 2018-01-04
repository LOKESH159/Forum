package com.full.helper;

import com.full.model.AnswerQuestionDao;
import com.google.appengine.api.datastore.Entity;

public class AnswerQuestion {
	public String retrievingQuestion(String questionID){
		Entity entity=AnswerQuestionDao.retrievingQuestion(questionID);
		if(entity!=null){
			String question=(String)entity.getProperty("QUESTION");
			return question;
		}
		return "false";
	}

}
