package com.full;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.helper.RetriveQuestion;

@SuppressWarnings("serial")
@Controller
@RequestMapping("RetrieveQuestionsServlet")
public class RetriveQuestionsServlet {
	public  Logger logger = Logger.getLogger(RetriveQuestionsServlet.class.getName()); 
	private RetriveQuestion retrieveQuestion;
@Autowired
	public void setRetrieveQuestion(RetriveQuestion retrieveQuestion) {
		this.retrieveQuestion = retrieveQuestion;
	}
	@ResponseBody
	@RequestMapping(value="/retrieveQuestion",method=RequestMethod.GET)
	public String retrieveQuestions(HttpServletRequest req) {
		System.out.println("IAM IN RetrieveQuestions SERVLET");
		String question=req.getParameter("QUESTIONS");
		String value=req.getParameter("REQUIRED");
		System.out.println("VALUE IS ="+value);
		HttpSession session=req.getSession(false);
		if(session!=null && "USER".equalsIgnoreCase(question)){
			if("Questions".equals(value)){
		String eMail=(String)session.getAttribute("EMAIL");
		//RetriveQuestion retrieveQuestion=new RetriveQuestion();
	   String gsonQuestion=	retrieveQuestion.retrieveQuestion(eMail);
		return gsonQuestion;	
			}
			else{
				System.out.println("IAM IN RETRIEVE ANSWERQUESTION METHOD");
				String eMail=(String)session.getAttribute("EMAIL");
			//	RetriveQuestion retrieveQuestion=new RetriveQuestion();
			   String gsonQuestion=	retrieveQuestion.retrieveAnswerQuestion(eMail);
			   return gsonQuestion;	
			}
		}
		else{
		//	RetriveQuestion retrieveQuestion=new RetriveQuestion();
			String gsonQuestion=retrieveQuestion.retrieveQuestion("");
			logger.info("IAM NOT GETTING ERROR BEFORE"+gsonQuestion);
			return gsonQuestion;
			
		}
	}
/*	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doGet(req, resp);
	}*/


}
