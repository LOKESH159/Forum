package com.full.dto;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

public class DataStoreConnection {
	public  static DatastoreService dataBaseConnection(){
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		return dataStore;
	}

}
