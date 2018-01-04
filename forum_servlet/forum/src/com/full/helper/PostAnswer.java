package com.full.helper;

import java.util.Date;

import com.full.dto.GenerateUUID;
import com.full.dto.PostAnswerDto;
import com.full.model.PostAnswerDao;
import com.google.appengine.api.datastore.Entity;

public class PostAnswer {
	public void addAnswer(String questionId,String answer,String answeredBy){
		PostAnswerDto postAnswerDto=new PostAnswerDto();
		postAnswerDto.setQuestionId(questionId);
		postAnswerDto.setAnswerId(GenerateUUID.generateUUID());
		postAnswerDto.setAnswer(answer);
		postAnswerDto.setAnsweredBy(answeredBy);
		postAnswerDto.setAnsweredDate(new Date());
		PostAnswerDao.addAnswerInDb(postAnswerDto);
	}
	public boolean checkingPostedUser(String eMail,String questionId){
		Entity entity=PostAnswerDao.checkingUserMail(questionId);
		if(entity!=null){
			String questionedMail=(String)entity.getProperty("EMAIL");
			if(questionedMail.equals(eMail)){
				return true;
			}
			
		}
		return false;
	}
}
