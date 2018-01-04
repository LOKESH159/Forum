package com.full;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.full.dto.GenerateUUID;
import com.full.dto.SignUpDto;
import com.full.helper.SignUp;
@SuppressWarnings("serial")
public class SignUpServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("text/plain");
		System.out.println("IAM IN SignUp SERVLET");
		String userName=req.getParameter("USERNAME");
		String eMail=req.getParameter("EMAIL");
		String password=req.getParameter("PASSWORD");
		String confirmPassword=req.getParameter("CONFIRMPASSWORD");
		SignUpDto signUpDto=new SignUpDto();
		signUpDto.setUserName(userName);
		signUpDto.seteMail(eMail);
		signUpDto.setPassword(password);
	    signUpDto.setConfirmPassword(confirmPassword); 
	    signUpDto.setCustomerId(GenerateUUID.generateUUID());
	    
		System.out.println(eMail);	
		System.out.println(userName);
		System.out.println(password);
		System.out.println(confirmPassword);
		SignUp signUp=new SignUp();
		signUp.addUser(signUpDto);
		resp.getWriter().print("CALLING LOGIN.jsp");
		
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}

}
