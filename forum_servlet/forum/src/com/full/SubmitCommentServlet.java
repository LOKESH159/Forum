package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.full.helper.SubmitComment;

@SuppressWarnings("serial")
public class SubmitCommentServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("text/plain");

		resp.getWriter().print("YOUR COMMENT IS SUCCESSFULLY ADDED");
	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		String answerId=req.getParameter("ANSWERID");
		String questionId=req.getParameter("QUESTIONID");
		String comment=req.getParameter("COMMENT");
		System.out.println(answerId);
		System.out.println(questionId);
		System.out.println(comment);
		SubmitComment submitComment=new SubmitComment();
		HttpSession session=req.getSession(false);
		String commentedBy=(String)session.getAttribute("EMAIL");
		System.out.println(commentedBy);
		submitComment.addComment(questionId,answerId, comment,commentedBy);
		doGet(req, resp);
	}
}
