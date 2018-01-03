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

import com.full.helper.CheckUser;
import com.full.helper.PostAnswer;
@SuppressWarnings("serial")
@Controller
@RequestMapping("/PostAnswer")
public class PostAnswerServlet {
	private PostAnswer postAnswer;
	@Autowired
	public void setPostAnswer(PostAnswer postAnswer) {
		this.postAnswer = postAnswer;
	}

	@RequestMapping(value="/addAnswer",method=RequestMethod.POST)
	@ResponseBody
	public String addAnswer(HttpServletRequest req){

		System.out.println("IAM IN PostAnswerServle SERVLET");
		String answer=req.getParameter("ANSWER");
		String questionId=req.getParameter("QUESTIONID");
		HttpSession session=req.getSession(false);
		if(session!=null){
			String answeredBy=(String)session.getAttribute("EMAIL");
		boolean isSameUser=postAnswer.checkingPostedUser(answeredBy,questionId);
		  if(isSameUser){
			return "TRUE";
		     }
		  else{
		     postAnswer.addAnswer(questionId, answer, answeredBy);
		     return "ADDED SUCCESSFULLY";
		  }
		}else{      
		return "error";
		}
	
	}


}
