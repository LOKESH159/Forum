package com.full;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.full.dto.Mailer1;
import com.full.helper.SignUpConfirmation;
@SuppressWarnings("serial")
@Controller
@RequestMapping("")
public class SignUpConfirmationServlet {
	private SignUpConfirmation signUpConfirmation;
	@Autowired
	public void setSignUpConfirmation(SignUpConfirmation signUpConfirmation) {
		this.signUpConfirmation = signUpConfirmation;
	}
	public  Logger logger = Logger.getLogger(SignUpConfirmationServlet.class.getName()); 
	public String userConfirmation(HttpServletRequest req) {
		logger.info("IAM IN SIGN UP CONFIRMATION SERVLET");
		String customerID=req.getParameter("customerId");
		logger.info("THE CUSTOMER ID VALUE IS ="+customerID);
		boolean alreadyExist=signUpConfirmation.userConfirmation(customerID);
		if(alreadyExist){
			return "";
		}
		else{
			return "Login";
		}
	}
}
