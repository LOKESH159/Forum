package com.full;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.full.helper.CheckEMail;

@SuppressWarnings("serial")
public class CheckEMailServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("text/plain");
		System.out.println("IAM IN CHECK EMAIL SERVLET");
		String eMail=req.getParameter("EMAIL");
		System.out.println(eMail);
		CheckEMail checkEMail=new CheckEMail();
	boolean isExist=checkEMail.isEMailExist(eMail);
	
	System.out.println(isExist);
	
	if(isExist){
		resp.getWriter().print("true");
	}
	else{
		resp.getWriter().print("false");
	}
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doGet(req, resp);
	}

}
