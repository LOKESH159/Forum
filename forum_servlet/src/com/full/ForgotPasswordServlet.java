package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.full.dto.ForgotPasswordMail;

public class ForgotPasswordServlet  extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("text/plain");
		String eMail=req.getParameter("EMAIL");
		System.out.println("EMAIL IS ="+eMail);
		ForgotPasswordMail forgotPasswordMail=new ForgotPasswordMail();
		boolean isSended=forgotPasswordMail.sendMail(eMail);
if(isSended){
	resp.getWriter().print("true");
}
else{
	resp.getWriter().print("false");
}
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}

}
