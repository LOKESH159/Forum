package com.full.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.full.dto.AskQuestionDto;
import com.full.dto.GenerateUUID;
import com.full.model.AskQuestionDao;
@Service
public class AskQuestion {
	@Autowired
	private AskQuestionDto askQuestionDto;
	@Autowired
	private AskQuestionDao askQuestionDao;
	
	public void setAskQuestionDao(AskQuestionDao askQuestionDao) {
		this.askQuestionDao = askQuestionDao;
	}

	public void setAskQuestionDto(AskQuestionDto askQuestionDto) {
		this.askQuestionDto = askQuestionDto;
	}

	public  void askedQuestion(String technology,String question,String eMail){
	    String UUID=GenerateUUID.generateUUID();
	    askQuestionDto.seteMail(eMail);
	    askQuestionDto.setUUID(UUID);
	    askQuestionDto.setTechnology(technology);
	    askQuestionDto.setQuestion(question);
	    askQuestionDto.setQuestionedDate(new java.util.Date());
	    AskQuestionDao askQuestionDao=new AskQuestionDao();
	    askQuestionDao.askedQuestion(askQuestionDto);
	
	}

}
