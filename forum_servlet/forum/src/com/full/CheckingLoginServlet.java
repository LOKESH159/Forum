package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@SuppressWarnings("serial")
public class CheckingLoginServlet  extends HttpServlet {


	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("text/plain");
		System.out.println("IAM IN CheckingLoginServlet SERVLET");
		HttpSession session=req.getSession(false);
		if(session!=null){
			resp.getWriter().print("valid");
		}
		else{
		resp.getWriter().print("Invalid");
		}
		
	
	}



}
