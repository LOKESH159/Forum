package com.full.helper;

import com.full.model.CheckUserDao;

public class CheckUser {
public  boolean checkUser(String eMail,String password){
	CheckUserDao checkUserDao=new CheckUserDao();
return checkUserDao.checkingUser(eMail, password);
}

}
