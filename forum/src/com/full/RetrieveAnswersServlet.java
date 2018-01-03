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

import com.full.helper.RetrieveAnswer;
import com.google.gson.JsonObject;
@SuppressWarnings("serial")
@Controller
@RequestMapping("/RetrieveAnswers")
public class RetrieveAnswersServlet{
	@Autowired
	private RetrieveAnswer retrieveAnswer;
	public void setRetrieveAnswer(RetrieveAnswer retrieveAnswer) {
		this.retrieveAnswer = retrieveAnswer;
	}

	@ResponseBody
	@RequestMapping(value="/retrieveAnswers",method=RequestMethod.GET)
	public String  retrieveAnswers(HttpServletRequest req) {
	
		System.out.println("IAM IN RetrieveAnswers SERVLET");
		String questionId=req.getParameter("QUESTIONID");
	   JsonObject jsonData=	retrieveAnswer.retrieveAnswers(questionId);
		return jsonData.toString();

		
	
	}
	

}
