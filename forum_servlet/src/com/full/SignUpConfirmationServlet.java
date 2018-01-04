package com.full;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.full.dto.Mailer1;
import com.full.helper.SignUpConfirmation;
@SuppressWarnings("serial")

public class SignUpConfirmationServlet extends HttpServlet {
	public  Logger logger = Logger.getLogger(SignUpConfirmationServlet.class.getName()); 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		logger.info("IAM IN SIGN UP CONFIRMATION SERVLET");
		String customerID=req.getParameter("customerId");
		logger.info("THE CUSTOMER ID VALUE IS ="+customerID);
		SignUpConfirmation signUpConfirmation=new SignUpConfirmation();
		boolean alreadyExist=signUpConfirmation.userConfirmation(customerID);
		if(alreadyExist){
			resp.sendRedirect("");
		}
		else{
			resp.sendRedirect("Login.jsp");
		}
	}
}
