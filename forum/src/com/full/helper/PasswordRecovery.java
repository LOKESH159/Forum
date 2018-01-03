package com.full.helper;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.full.model.PasswordRecoveryDao;
@Service
public class PasswordRecovery {
	private PasswordRecoveryDao passwordRecoveryDao;
	@Autowired
 public void setPasswordRecoveryDao(PasswordRecoveryDao passwordRecoveryDao) {
		this.passwordRecoveryDao = passwordRecoveryDao;
	}
Logger logger=Logger.getLogger(PasswordRecovery.class.getName());
	public  boolean updatingPassword(String customerId,String password){
		logger.info("IAM IN UPDATING PASSWORD METHOD");
		return passwordRecoveryDao.updatingPassword(customerId, password);
	}

}
