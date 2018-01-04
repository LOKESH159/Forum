package com.full.model;

import com.full.dto.AskQuestionDto;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class AskQuestionDao {
	public  void askedQuestion(AskQuestionDto askQuestionDto){
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		Entity entity=new Entity("QUESTIONDB", askQuestionDto.getUUID());
		entity.setProperty("QUESTIONID",askQuestionDto.getUUID());
		entity.setProperty("EMAIL", askQuestionDto.geteMail());
		entity.setProperty("QUESTION",askQuestionDto.getQuestion());
		entity.setProperty("TECHNOLOGY", askQuestionDto.getTechnology());
		entity.setProperty("QUESTIONED_DATE",askQuestionDto.getQuestionedDate());
		System.out.println("IAM ADDING QUESTION");
	     dataStore.put(entity);				
	}

}
