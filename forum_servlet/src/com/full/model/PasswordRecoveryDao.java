package com.full.model;

import org.apache.log4j.Logger;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class PasswordRecoveryDao {
	Logger logger=Logger.getLogger(PasswordRecoveryDao.class.getName());
	public  boolean updatingPassword(String customerId,String password){
		logger.info("IAM IN UPDATING PASSWORD DAO METHOD");
		System.out.println("IAM IN  checkingUser");
		System.out.println(customerId);
		System.out.println(password);
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		Filter keyFilter = new FilterPredicate("CUSTOMER_ID", FilterOperator.EQUAL,customerId);
	    Query q = new Query("USER_ACCOUNT").setFilter(keyFilter);
	    PreparedQuery pq=dataStore.prepare(q);
	try {
		for(Entity entity:pq.asIterable()){
			System.out.println("IAM IN PASSWORD RECOVERY DAO THE ENTITY IS ="+entity);
			logger.info("IAM IN PASSWORD RECOVERY DAO THE ENTITY IS ="+entity);
			entity.setProperty("PASSWORD",password);
			dataStore.put(entity);
			logger.info("I STORED THE VALUE IN DATASTORE");
			return true;
		}
	}
	catch(Exception e){
		logger.info("I GOT EXCEPTION IAM RETURNING FALSE");
	}
	return false;
		}
}
