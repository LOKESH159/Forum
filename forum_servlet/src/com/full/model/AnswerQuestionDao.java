package com.full.model;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class AnswerQuestionDao {
public static Entity retrievingQuestion(String questionId){
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	Key employeeKey = KeyFactory.createKey("QUESTIONDB",questionId);
	Entity entity=null;
	try {
		 entity = dataStore.get(employeeKey);
	} catch (EntityNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("I GOT EXCEPTION");
		
	}
	return entity;
}
}
