package com.full.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.full.model.CheckUserDao;
@Service
public class CheckUser {
	private CheckUserDao checkUserDao;
	@Autowired
public void setCheckUserDao(CheckUserDao checkUserDao) {
		this.checkUserDao = checkUserDao;
	}
public  boolean checkUser(String eMail,String password){
	//CheckUserDao checkUserDao=new CheckUserDao();
	System.out.println("IAM IN CHECKUSER");
return checkUserDao.checkingUser(eMail, password);
}

}
