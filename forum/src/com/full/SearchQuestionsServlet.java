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

import com.full.dto.GenerateUUID;
import com.full.dto.SignUpDto;
import com.full.helper.SearchQuestions;
import com.full.helper.SignUp;
@Controller
@RequestMapping("/SearchQuestionsServlet")
public class SearchQuestionsServlet extends HttpServlet {
	private SearchQuestions searchQuestions;
	@Autowired
	public void setSearchQuestions(SearchQuestions searchQuestions) {
		this.searchQuestions = searchQuestions;
	}
	@RequestMapping(value="/searchQuestion",method=RequestMethod.GET)
	@ResponseBody
	public String searchQuestion(HttpServletRequest req) {
	
		System.out.println("IAM IN SEARCH QUESTIONS SERVLET");
		String technology=req.getParameter("TECHNOLOGY");
		System.out.println("TECHNOLOGY VALUE IS ="+technology);
      String questions=  searchQuestions.retrieveSearchQuestion(technology);
      //System.out.println(questions.length());
      if(questions!=null){
    	  System.out.println("iam NOT NULL");
    	  return questions;
      }
        
		return "";
	
	}

}
