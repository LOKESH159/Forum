package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.helper.SubmitComment;

@SuppressWarnings("serial")
@Controller
@RequestMapping("SubmitComment")
public class SubmitCommentServlet {
	private SubmitComment submitComment;
	@Autowired
	public void setSubmitComment(SubmitComment submitComment) {
		this.submitComment = submitComment;
	}
	@RequestMapping(value="/addComment",method=RequestMethod.POST)
	@ResponseBody
	public String addComment(HttpServletRequest req) {
		String answerId=req.getParameter("ANSWERID");
		String questionId=req.getParameter("QUESTIONID");
		String comment=req.getParameter("COMMENT");
		System.out.println(answerId);
		System.out.println(questionId);
		System.out.println(comment);
		HttpSession session=req.getSession(false);
		String commentedBy=(String)session.getAttribute("EMAIL");
		System.out.println(commentedBy);
		submitComment.addComment(questionId,answerId, comment,commentedBy);
		return "YOUR COMMENT IS SUCCESSFULLY ADDED";
	}
}
