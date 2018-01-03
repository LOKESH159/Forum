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

import com.full.helper.AskQuestion;
@SuppressWarnings("serial")
@Controller
@RequestMapping("/AskQuestion")
public class AskQuestionServlet {
	private AskQuestion askQuestion;
	@Autowired
	public void setAskQuestion(AskQuestion askQuestion) {
		this.askQuestion = askQuestion;
	}

	@RequestMapping(value="/addQuestion",method=RequestMethod.POST)
	@ResponseBody
	public String addQuestion(HttpServletRequest req) {
		
		System.out.println("IAM IN ASKQUESTION SERVLET");
		String technology=req.getParameter("TECHNOLOGY");
		String question=req.getParameter("QUESTION");
		System.out.println(technology);	
		System.out.println(question);
		HttpSession session=req.getSession(false);
		String eMail=(String)session.getAttribute("EMAIL");
		System.out.println("EMAIL FROM SESSION IS="+eMail);
		askQuestion.askedQuestion(technology, question, eMail);
		return "QUESTION IS ADDED SUCCESSFULLY";
	}
}
