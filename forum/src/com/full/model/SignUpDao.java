package com.full.model;

import org.springframework.stereotype.Repository;

import com.full.dto.SignUpDto;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
@Repository
public class SignUpDao {
	public   void addUserInDb(SignUpDto signUpDto){
		System.out.println("IAM IN addUserInDb");
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		Entity entity=new Entity("USER_ACCOUNT", signUpDto.getEMAIL());
		entity.setProperty("USERNAME", signUpDto.getUSERNAME());
		entity.setProperty("PASSWORD",signUpDto.getPASSWORD());
		entity.setProperty("CONFIRMATION","NO");
		entity.setProperty("CUSTOMER_ID",signUpDto.getCustomerId());
		System.out.println("IAM ADDING USER");
	    Key key= dataStore.put(entity);	
	    System.out.println(key.getId());
	}

}
