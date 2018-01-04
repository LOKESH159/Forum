package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.full.helper.CheckUser;
import com.full.helper.PostAnswer;
@SuppressWarnings("serial")
public class PostAnswerServlet  extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("text/plain");
		System.out.println("IAM IN PostAnswerServle SERVLET");
		String answer=req.getParameter("ANSWER");
		String questionId=req.getParameter("QUESTIONID");
		HttpSession session=req.getSession(false);
		if(session!=null){
			 PostAnswer postAnswer=new PostAnswer();
			String answeredBy=(String)session.getAttribute("EMAIL");
		boolean isSameUser=postAnswer.checkingPostedUser(answeredBy,questionId);
		  if(isSameUser){
			resp.getWriter().print("TRUE");
		     }
		  else{
		     postAnswer.addAnswer(questionId, answer, answeredBy);
		     resp.getWriter().print("ADDED SUCCESSFULLY");
		  }
		}else{      
		resp.getWriter().print("error");
		}
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}


}
