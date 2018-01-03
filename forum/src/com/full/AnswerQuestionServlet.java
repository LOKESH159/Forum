package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.helper.AnswerQuestion;
@Controller
@RequestMapping("/RetrieveAnswerQuestion")
public class AnswerQuestionServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AnswerQuestion answerQuestion;
	
	@Autowired
	public void setAnswerQuestion(AnswerQuestion answerQuestion) {
		this.answerQuestion = answerQuestion;
		
	}
	
	@ResponseBody
	@RequestMapping(value="retrieveQuestion",method=RequestMethod.GET)
	public String  retrieveQuestion(HttpServletRequest req) {
		
		System.out.println("IAM IN AnswerQuestionServlet SERVLET");
		
		String questionId=req.getParameter("QUESTIONID");
		System.out.println(questionId);
		
		String question=answerQuestion.retrievingQuestion(questionId);
		
		if(question.equals("false")){
			return "false";
		}
		else{
			return question;
		}	
	}


}
