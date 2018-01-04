package com.full;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.full.dto.Mailer;
import com.full.helper.RetriveQuestion;

@SuppressWarnings("serial")

public class RetriveQuestionsServlet  extends HttpServlet {
	public  Logger logger = Logger.getLogger(RetriveQuestionsServlet.class.getName()); 
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("application/json");
		System.out.println("IAM IN RetrieveQuestions SERVLET");
		String question=req.getParameter("QUESTIONS");
		String value=req.getParameter("REQUIRED");
		System.out.println("VALUE IS ="+value);
		HttpSession session=req.getSession(false);
		if(session!=null && "USER".equalsIgnoreCase(question)){
			if("Questions".equals(value)){
		String eMail=(String)session.getAttribute("EMAIL");
		RetriveQuestion retrieveQuestion=new RetriveQuestion();
	   String gsonQuestion=	retrieveQuestion.retrieveQuestion(eMail);
		resp.getWriter().println(gsonQuestion);	
			}
			else{
				System.out.println("IAM IN RETRIEVE ANSWERQUESTION METHOD");
				String eMail=(String)session.getAttribute("EMAIL");
				RetriveQuestion retrieveQuestion=new RetriveQuestion();
			   String gsonQuestion=	retrieveQuestion.retrieveAnswerQuestion(eMail);
				resp.getWriter().println(gsonQuestion);	
			}
		}
		else{
			RetriveQuestion retrieveQuestion=new RetriveQuestion();
			String gsonQuestion=retrieveQuestion.retrieveQuestion("");
			logger.info("IAM NOT GETTING ERROR BEFORE"+gsonQuestion);
			resp.getWriter().println(gsonQuestion);	
			logger.info("IAM NOT GETTING ERROR AFTER"+gsonQuestion);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}


}
