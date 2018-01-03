package com.full.model;

import org.springframework.stereotype.Repository;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
@Repository
public class ForgotPasswordMailDao {
	String customerId;
	public String retrieveUUID(String eMail){
		
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	Key employeeKey = KeyFactory.createKey("USER_ACCOUNT", eMail);
	try {
		Entity entity = dataStore.get(employeeKey);
		String customerId=(String)entity.getProperty("CUSTOMER_ID");
	return customerId;
		
	} catch (EntityNotFoundException e) {
		// TODO Auto-generated catch block
		System.out.println("I GOT EXCEPTION");	
	}
	return customerId;
	}
}
