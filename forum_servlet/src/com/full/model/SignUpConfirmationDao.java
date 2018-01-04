package com.full.model;

import java.util.logging.Logger;

import com.full.SignUpConfirmationServlet;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class SignUpConfirmationDao {
	public  Logger logger = Logger.getLogger(SignUpConfirmationDao.class.getName());
public  PreparedQuery confirmUser(String customerId){
	logger.info("IAM IN SIGNUP CONFIRMATION DAO AND HE CUSTOMER ID VALUE IS "+customerId);
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	Filter keyFilter = new FilterPredicate("CUSTOMER_ID", FilterOperator.EQUAL,customerId);
    Query q = new Query("USER_ACCOUNT").setFilter(keyFilter);
    System.out.println("QUERY VALUE IS ="+q);
    PreparedQuery pq=dataStore.prepare(q);
    System.out.println("PQ AS SINGLE ENTITY IS="+pq.asSingleEntity());
    return pq;

}
public void updatingUserStatus(Entity entity){
	logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN updatingUserStatus");
	DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
	    entity.setProperty("CONFIRMATION", "YES");
	    dataStore.put(entity);
	    logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN STORED THE ENTITY");
}
}
