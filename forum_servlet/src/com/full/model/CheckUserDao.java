package com.full.model;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class CheckUserDao {
	public  boolean checkingUser(String eMail,String password){
	System.out.println("IAM IN  checkingUser");
	System.out.println(eMail);
	System.out.println(password);
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
Key employeeKey = KeyFactory.createKey("USER_ACCOUNT", eMail);
System.out.println("KEY VALUE IS ="+employeeKey);
try {
	Entity e = dataStore.get(employeeKey);
	String passwordDb=(String)e.getProperty("PASSWORD");
	System.out.println("PASSWORD DB VALUE IS="+passwordDb);
	if(passwordDb.equals(password)){
		System.out.println("PASSWORD IS VALID FOR THE USER");
	    return true;
	}
	else{
		System.out.println("PASSWORD IS NOT VALID FOR THE USER");
		return false;
	}
} catch (EntityNotFoundException e) {
	// TODO Auto-generated catch block
	System.out.println("I GOT EXCEPTION");
	
}
return false;
	}

}
