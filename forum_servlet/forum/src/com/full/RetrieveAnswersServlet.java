package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.full.helper.RetrieveAnswer;
import com.google.gson.JsonObject;
@SuppressWarnings("serial")
public class RetrieveAnswersServlet  extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("application/json");
		System.out.println("IAM IN RetrieveAnswers SERVLET");
		String questionId=req.getParameter("QUESTIONID");
		RetrieveAnswer retrieveAnswer=new RetrieveAnswer();
	   JsonObject jsonData=	retrieveAnswer.retrieveAnswers(questionId);
		resp.getWriter().println(jsonData);

		
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}


}
