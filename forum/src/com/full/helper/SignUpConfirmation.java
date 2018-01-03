package com.full.helper;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.full.SignUpConfirmationServlet;
import com.full.model.SignUpConfirmationDao;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
@Service
public class SignUpConfirmation {
	private SignUpConfirmationDao signUpConfirmationDao;
	@Autowired
	public void setSignUpConfirmationDao(SignUpConfirmationDao signUpConfirmationDao) {
		this.signUpConfirmationDao = signUpConfirmationDao;
	}
	public  Logger logger = Logger.getLogger(SignUpConfirmationServlet.class.getName());
public  boolean userConfirmation(String customerId){
	logger.info("IAM IN SIGN UP CONFIRMATION AND IAM CALLING DAO CLASS");
	PreparedQuery pq= signUpConfirmationDao.confirmUser(customerId);
	logger.info("IAM IN SIGN UP CONFIRMATION AND I GOT PQ");
	for(Entity entity:pq.asIterable()){
		logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN FOR EACH LOOP");
		if(entity!=null){
			logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN ENTITY IS NOT NULL");
			String confirmation=(String)entity.getProperty("CONFIRMATION");
			if(confirmation.equalsIgnoreCase("YES")){
				logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN STATUS IS YES");
				return true;
			}
			else{
				logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN STATUS IS NO");
				signUpConfirmationDao.updatingUserStatus(entity);
				return false;
			}
		}
	}
	return true;
}

}
