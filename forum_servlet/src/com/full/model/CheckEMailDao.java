package com.full.model;



import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class CheckEMailDao {
public  boolean  checkEMail(String eMail){
	System.out.println("IAM IN  checkEMail");
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
Key employeeKey = KeyFactory.createKey("USER_ACCOUNT", eMail);
System.out.println(employeeKey);
try {
	Entity e = dataStore.get(employeeKey);
	System.out.println(e.getProperty("USERNAME"));
	return true;
} catch (EntityNotFoundException e) {
	// TODO Auto-generated catch block
	System.out.println("I GOT EXCEPTION");
	
}
return false;

}
}
