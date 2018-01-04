package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.full.helper.CheckUser;
@SuppressWarnings("serial")
public class CheckUserServlet  extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("text/plain");
		String eMail=req.getParameter("EMAIL");
		String password=req.getParameter("PASSWORD");
		System.out.println(eMail);
		System.out.println(password);
		CheckUser checkUser=new CheckUser();
		boolean userExists=checkUser.checkUser(eMail, password);
		if(userExists){
			System.out.println("USER EXISTS ");
			HttpSession session=req.getSession(true);
			session.setAttribute("EMAIL",eMail);
			session.setAttribute("PASSWORD",password);
			session.setMaxInactiveInterval(120);
			resp.getWriter().println("true");
		}
		else{
			System.out.println("USER  NOT EXISTS ");
			resp.getWriter().println("false");
		}
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}

}
