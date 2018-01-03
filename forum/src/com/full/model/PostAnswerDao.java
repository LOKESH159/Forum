package com.full.model;

import org.springframework.stereotype.Repository;

import com.full.dto.PostAnswerDto;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
@Repository
public class PostAnswerDao {
public static void addAnswerInDb(PostAnswerDto postAnswerDto){
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	Entity entity=new Entity("ANSWER_DB", postAnswerDto.getAnswerId());
	entity.setProperty("QUESTION_ID",postAnswerDto.getQuestionId());
	entity.setProperty("ANSWER",postAnswerDto.getAnswer());
	entity.setProperty("ANSWERED_BY",postAnswerDto.getAnsweredBy());
	entity.setProperty("ANSWERED_DATE",postAnswerDto.getAnsweredDate());
	entity.setProperty("ANSWER_ID", postAnswerDto.getAnswerId());
	entity.setProperty("NOOFVOTES", 0);
	entity.setProperty("VALIDANSWER",false);
	System.out.println("IAM ADDING ANSWER");
    Key key= dataStore.put(entity);	
    System.out.println(key.getId());

}
public static Entity checkingUserMail(String questionId){
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	Key employeeKey = KeyFactory.createKey("QUESTIONDB",questionId);
	Entity entity=null;
	try {
		 entity= dataStore.get(employeeKey);
		//System.out.println(entity.getProperty("USERNAME"));
		return entity;
	} catch (EntityNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("I GOT EXCEPTION");
		
	}
	return entity;
}
}
