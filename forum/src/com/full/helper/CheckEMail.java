package com.full.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.full.model.CheckEMailDao;
@Service
public class CheckEMail {
	private CheckEMailDao checkEMailDao;
	@Autowired
	public void setCheckEMailDao(CheckEMailDao checkEMailDao) {
		this.checkEMailDao = checkEMailDao;
	}
	public  boolean isEMailExist(String eMail){
	
		boolean b=checkEMailDao.checkEMail(eMail);
	     return b;
	}

}
