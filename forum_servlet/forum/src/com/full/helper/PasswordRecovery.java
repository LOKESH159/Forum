package com.full.helper;

import org.apache.log4j.Logger;

import com.full.model.PasswordRecoveryDao;

public class PasswordRecovery {
 Logger logger=Logger.getLogger(PasswordRecovery.class.getName());
	public  boolean updatingPassword(String customerId,String password){
		logger.info("IAM IN UPDATING PASSWORD METHOD");
		PasswordRecoveryDao passwordRecoveryDao=new PasswordRecoveryDao();
		return passwordRecoveryDao.updatingPassword(customerId, password);
	}

}
