package com.full;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.full.dto.GenerateUUID;
import com.full.dto.SignUpDto;
import com.full.helper.SearchQuestions;
import com.full.helper.SignUp;

public class SearchQuestionsServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException,ServletException {
		resp.setContentType("application/json");
		System.out.println("IAM IN SEARCH QUESTIONS SERVLET");
		String technology=req.getParameter("TECHNOLOGY");
		System.out.println("TECHNOLOGY VALUE IS ="+technology);
        SearchQuestions searchQuestions=new SearchQuestions();
      String questions=  searchQuestions.retrieveSearchQuestion(technology);
      //System.out.println(questions.length());
      if(questions!=null){
    	  System.out.println("iam NOT NULL");
    	  resp.getWriter().println(questions);
      }
        
		
	
	}

}
