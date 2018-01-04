package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.full.helper.AskQuestion;
@SuppressWarnings("serial")
public class AskQuestionServlet  extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("text/plain");
		System.out.println("IAM IN SignUp SERVLET");
		String technology=req.getParameter("TECHNOLOGY");
		String question=req.getParameter("QUESTION");
		System.out.println(technology);	
		System.out.println(question);
		HttpSession session=req.getSession(false);
		String eMail=(String)session.getAttribute("EMAIL");
		System.out.println("EMAIL FROM SESSION IS="+eMail);
		AskQuestion askQuestion=new AskQuestion();
		askQuestion.askedQuestion(technology, question, eMail);
		resp.getWriter().print("QUESTION IS ADDED SUCCESSFULLY");
		
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}


}
