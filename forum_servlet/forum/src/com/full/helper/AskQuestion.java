package com.full.helper;

import com.full.dto.AskQuestionDto;
import com.full.dto.GenerateUUID;
import com.full.model.AskQuestionDao;

public class AskQuestion {
	public  void askedQuestion(String technology,String question,String eMail){
	    String UUID=GenerateUUID.generateUUID();
	    AskQuestionDto askQuestionDto=new AskQuestionDto();
	    askQuestionDto.seteMail(eMail);
	    askQuestionDto.setUUID(UUID);
	    askQuestionDto.setTechnology(technology);
	    askQuestionDto.setQuestion(question);
	    askQuestionDto.setQuestionedDate(new java.util.Date());
	    AskQuestionDao askQuestionDao=new AskQuestionDao();
	    askQuestionDao.askedQuestion(askQuestionDto);
	
	}

}
