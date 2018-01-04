package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.full.helper.AnswerQuestion;
public class AnswerQuestionServlet  extends HttpServlet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("text/plain");
		System.out.println("IAM IN AnswerQuestionServlet SERVLET");
		String questionId=req.getParameter("QUESTIONID");
		System.out.println(questionId);
		AnswerQuestion answerQuestion=new AnswerQuestion();
		String question=answerQuestion.retrievingQuestion(questionId);
		if(question.equals("false")){
			resp.getWriter().print("false");
		}
		else{
			resp.getWriter().print(question);
		}	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}

}
