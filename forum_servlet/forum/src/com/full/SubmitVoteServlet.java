package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.full.helper.SubmitVote;

public class SubmitVoteServlet extends HttpServlet {	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/plain");
		System.out.println("IAM IN SUBMIT VOTE SERVLET");
		String answerId=req.getParameter("ANSWERID");
		String questionId=req.getParameter("QUESTIONID");
		String vote=req.getParameter("VOTE");
		System.out.println(answerId);
		System.out.println(questionId);
		System.out.println(vote);
		SubmitVote submitVote=new SubmitVote();
		HttpSession session=req.getSession(false);
		String votedBy=(String)session.getAttribute("EMAIL");
		System.out.println(votedBy);
		String status=submitVote.addVote(questionId,answerId, vote,votedBy);
		System.out.println("STATUS IN SUBMIT VOTE SERVLET IS ="+status);
		resp.getWriter().print(status);
		
	}
}
