package com.full.model;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class RetrieveQUestionDao {
	public static PreparedQuery retrieveQuestion(String eMail){
		System.out.println("IAM IN RETRIEVEQUESTIONDAO");
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	if(eMail!=""){
	Filter keyFilter = new FilterPredicate("EMAIL", FilterOperator.EQUAL, eMail);
	    Query q = new Query("QUESTIONDB").setFilter(keyFilter);
	    PreparedQuery pq=dataStore.prepare(q);
	    return pq;   
	}
	else{
		   Query q = new Query("QUESTIONDB");
		   PreparedQuery pq=dataStore.prepare(q);
		   return pq; 
	    }
	}
	public static PreparedQuery retrieveAnswerQuestionDao(String eMail){
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		 PreparedQuery pq=null;
		if(eMail!=""){
		Filter keyFilter = new FilterPredicate("ANSWERED_BY", FilterOperator.EQUAL, eMail);
		    Query q = new Query("ANSWER_DB").setFilter(keyFilter);
		     pq=dataStore.prepare(q);
		     
		}
		return pq;  
	}
	public static PreparedQuery retrieveAnswerQuestionDao(List<Filter> list){
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		 PreparedQuery pq=null;
		Filter filter = CompositeFilterOperator.or(list);
		 Query q = new Query("QUESTIONDB").setFilter(filter);
		 pq=dataStore.prepare(q);
		  return pq;  
	}
}
