package com.full.model;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
@Repository
public class SearchQuestionsDao {
	public PreparedQuery retrieveSearchQuestions(String technology){
		System.out.println("IAM IN RETRIEVEQUESTIONDAO");
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	  PreparedQuery pq=null;
	if(technology!=""){
	Filter keyFilter = new FilterPredicate("TECHNOLOGY", FilterOperator.EQUAL, technology);
	    Query q = new Query("QUESTIONDB").setFilter(keyFilter);
 pq=dataStore.prepare(q);
	      
	}
	 return pq;
	}

}
