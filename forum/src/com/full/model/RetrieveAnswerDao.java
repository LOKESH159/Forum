package com.full.model;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
@Repository
public class RetrieveAnswerDao {
	public static PreparedQuery retrieveAnswer(String questionId){
		System.out.println("IAM IN RETRIEVEANSWERDAO");
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	Filter keyFilter = new FilterPredicate("QUESTION_ID", FilterOperator.EQUAL,questionId);
	    Query q = new Query("ANSWER_DB").setFilter(keyFilter);
	    PreparedQuery pq=dataStore.prepare(q);
	    return pq;
	    
	}
	public static PreparedQuery retrieveComments(String questionId){
		System.out.println("IAM IN RETRIEVECOMMENT DAO");
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	Filter keyFilter = new FilterPredicate("QUESTION_ID", FilterOperator.EQUAL,questionId);
	    Query q = new Query("COMMENTS").setFilter(keyFilter);
	    PreparedQuery pq=dataStore.prepare(q);
	    return pq;
	}

}
