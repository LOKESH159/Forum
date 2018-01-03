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

import com.full.helper.SubmitVote;
@Controller
@RequestMapping("SubmitVote")
public class SubmitVoteServlet {	
	private SubmitVote submitVote;
	@Autowired
	public void setSubmitVote(SubmitVote submitVote) {
		this.submitVote = submitVote;
	}
@RequestMapping(value="/addVote",method=RequestMethod.POST)
@ResponseBody
	public String addVote(HttpServletRequest req){
		System.out.println("IAM IN SUBMIT VOTE SERVLET");
		String answerId=req.getParameter("ANSWERID");
		String questionId=req.getParameter("QUESTIONID");
		String vote=req.getParameter("VOTE");
		System.out.println(answerId);
		System.out.println(questionId);
		System.out.println(vote);
		HttpSession session=req.getSession(false);
		String votedBy=(String)session.getAttribute("EMAIL");
		System.out.println(votedBy);
		String status=submitVote.addVote(questionId,answerId, vote,votedBy);
		System.out.println("STATUS IN SUBMIT VOTE SERVLET IS ="+status);
		return status;
		
	}
}
