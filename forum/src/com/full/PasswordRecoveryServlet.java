package com.full;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.dto.Mailer1;
import com.full.helper.PasswordRecovery;
@SuppressWarnings("serial")
@Controller
@RequestMapping("PasswordRecoveryServlet")

public class PasswordRecoveryServlet {
	private PasswordRecovery passwordRecovery;
	@Autowired
	
	public void setPasswordRecovery(PasswordRecovery passwordRecovery) {
		this.passwordRecovery = passwordRecovery;
	}

	public  Logger logger = Logger.getLogger(PasswordRecoveryServlet.class.getName()); 
	@RequestMapping("/passwordRecovery")
	@ResponseBody
	public String passwordRecovery(HttpServletRequest req) {
		System.out.println("IAM IN SignUp SERVLET");
		logger.info("IAM IN SignUp SERVLET");
		String customerId=req.getParameter("CUSTOMERID");
		String password=req.getParameter("PASSWORD");   	    
		System.out.println(customerId);	
		logger.info("CUSTOMER ID IS="+customerId);
		logger.info("PASSWORD IS="+password);
		System.out.println(password);
		PasswordRecovery passwordRecovery=new PasswordRecovery();
		boolean isPasswordUpdated=passwordRecovery.updatingPassword(customerId, password);
		if(isPasswordUpdated){
			logger.info("PASSWORD IS UPDATED SUCCESSFULLY");
			System.out.println("THE PASSWORD IS UPDATED SUCCESSFULLY");
			return "true";
		}
		else{
			logger.info("PASSWORD IS  NOT UPDATED SUCCESSFULLY");
			System.out.println("THE PASSWORD IS NOT UPDATED SUCCESSFULLY");
			return "false";
		}	
	}

}
