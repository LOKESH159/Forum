package com.full.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.full.dto.MailServices;
import com.full.dto.Mailer1;
import com.full.dto.SignUpDto;
import com.full.model.SignUpDao;
@Service
public class SignUp {
	@Autowired
	private SignUpDao signUpDao;
	public void setSignUpDao(SignUpDao signUpDao) {
		this.signUpDao = signUpDao;
	}
	public  void addUser(SignUpDto signUpDto){
		//SignUpDao signUpDao=new SignUpDao();
		signUpDao.addUserInDb(signUpDto);
		MailServices mailer=new MailServices();
		mailer.sendSimpleMail(signUpDto.getEMAIL(),signUpDto.getCustomerId());
	}

}
