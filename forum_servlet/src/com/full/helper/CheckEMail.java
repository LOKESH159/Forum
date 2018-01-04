package com.full.helper;

import com.full.model.CheckEMailDao;

public class CheckEMail {
	public  boolean isEMailExist(String eMail){
		CheckEMailDao checkEMailDao=new CheckEMailDao();
	
		boolean b=checkEMailDao.checkEMail(eMail);
	     return b;
	}

}
