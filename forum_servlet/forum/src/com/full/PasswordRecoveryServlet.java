package com.full;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.full.dto.Mailer1;
import com.full.helper.PasswordRecovery;
@SuppressWarnings("serial")
public class PasswordRecoveryServlet  extends HttpServlet {
	public  Logger logger = Logger.getLogger(PasswordRecoveryServlet.class.getName()); 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("text/plain");
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
			resp.getWriter().println("true");
		}
		else{
			logger.info("PASSWORD IS  NOT UPDATED SUCCESSFULLY");
			System.out.println("THE PASSWORD IS NOT UPDATED SUCCESSFULLY");
			resp.getWriter().println("false");
		}
		
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}


}
