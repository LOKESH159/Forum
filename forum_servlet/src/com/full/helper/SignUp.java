package com.full.helper;

import com.full.dto.MailServices;
import com.full.dto.Mailer1;
import com.full.dto.SignUpDto;
import com.full.model.SignUpDao;

public class SignUp {
	public  void addUser(SignUpDto signUpDto){
		SignUpDao signUpDao=new SignUpDao();
		signUpDao.addUserInDb(signUpDto);
		MailServices mailer=new MailServices();
		mailer.sendSimpleMail(signUpDto.geteMail(),signUpDto.getCustomerId());
	}

}
