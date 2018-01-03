package com.full.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.full.dto.GenerateUUID;
import com.full.dto.SignUpDto;
import com.full.helper.ForgotPasswordMail;
import com.full.helper.PasswordRecovery;
import com.full.user.helper.UserHelper;
import com.google.gson.JsonObject;

@Controller
public class UserController {
	@Autowired
	private UserHelper userHelper;
Logger logger=Logger.getLogger(UserController.class.getClass());
	public void setUserHelper(UserHelper userHelper) {
		this.userHelper = userHelper;
	}

	@RequestMapping(value = "/Login", method = RequestMethod.GET)
	@ResponseBody
	public String checkUserServlet(@RequestParam("EMAIL") String eMail, @RequestParam("PASSWORD") String password,
			HttpServletRequest req) {

		System.out.println(eMail);
		System.out.println(password);
		return userHelper.checkUser(eMail, password, req);
	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.GET)
	@ResponseBody
	public String checkLogin(HttpServletRequest req) {
		System.out.println("IAM IN CheckingLoginServlet SERVLET");
		return userHelper.CheckingLogin(req);
	}

	@RequestMapping(value = "/checkEMail", method = RequestMethod.GET)
	@ResponseBody
	public String checkingUser(@RequestParam("EMAIL") String eMail) {
		System.out.println("IAM IN CHECK EMAIL SERVLET");
		System.out.println(eMail);
		return userHelper.isEMailExist(eMail);
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public @ResponseBody String addUser(HttpServletRequest req) {
		System.out.println("IAM IN SignUp SERVLET");
		return userHelper.addUser(req);
	
	}

	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	@ResponseBody
	public void addQuestion(HttpServletRequest req) {
		userHelper.askedQuestion(req);
	}
	
	

	@ResponseBody
	@RequestMapping(value = "/retrieveQuestions", method = RequestMethod.GET)
	public String retrieveQuestions(HttpServletRequest req) {
		System.out.println("IAM IN RetrieveQuestions SERVLET");
		return userHelper.retrieveQuestion(req);
	}

	@ResponseBody
	@RequestMapping(value = "/retrieveIdQuestion", method = RequestMethod.GET)
	public String retrieveIdQuestion(HttpServletRequest req) {
		System.out.println("IAM IN AnswerQuestionServlet SERVLET");
		String questionId = req.getParameter("QUESTIONID");
		System.out.println(questionId);
		return userHelper.retrievingIdQuestion(questionId);
	}

	@RequestMapping(value="/forgotPassword",method = RequestMethod.POST)
	@ResponseBody
	public String forgotPassword(HttpServletRequest req) {
		String eMail = req.getParameter("EMAIL");
		System.out.println("EMAIL IS =" + eMail);
		return userHelper.sendMail(eMail);
	}

	@RequestMapping(value = "/addAnswer", method = RequestMethod.POST)
	@ResponseBody
	public String addAnswer(HttpServletRequest req) {
		System.out.println("IAM IN PostAnswerServle SERVLET");
		return userHelper.addAnswer(req);
	}

	@ResponseBody
	@RequestMapping(value = "/retrieveAnswers", method = RequestMethod.GET)
	public String retrieveAnswers(HttpServletRequest req) {
		System.out.println("IAM IN RetrieveAnswers SERVLET");
		String questionId = req.getParameter("QUESTIONID");
		return userHelper.retrieveAnswers(questionId);
	}

	@RequestMapping(value = "/searchQuestion", method = RequestMethod.GET)
	@ResponseBody
	public String searchQuestion(HttpServletRequest req) {

		System.out.println("IAM IN SEARCH QUESTIONS SERVLET");
		String technology = req.getParameter("TECHNOLOGY");
		System.out.println("TECHNOLOGY VALUE IS =" + technology);
		return userHelper.retrieveSearchQuestion(technology);
	}

	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	@ResponseBody
	public String addComment(HttpServletRequest req) {
		return userHelper.addComment(req);

	}

	@RequestMapping(value = "/addVote", method = RequestMethod.POST)
	@ResponseBody
	public String addVote(HttpServletRequest req) {
		System.out.println("IAM IN SUBMIT VOTE SERVLET");
		return userHelper.addVote(req);
	}
	@RequestMapping("/")
	public String getHomePage(){
		return "homepage";
	}
	@RequestMapping("/signUpConfirmation")
	@ResponseBody
	public String userConfirmation(HttpServletRequest req) {
		String customerID=req.getParameter("customerId");
	return userHelper.userConfirmation(customerID);
	
	}
	@RequestMapping("/passwordRecovery")
	@ResponseBody
	public String passwordRecovery(HttpServletRequest req) {
		logger.info("IAM IN PASSWORD REcovery SERVLET");
		String customerId=req.getParameter("CUSTOMERID");
		String password=req.getParameter("PASSWORD");   	    
		logger.info("CUSTOMER ID IS="+customerId);
		logger.info("PASSWORD IS="+password);
		return userHelper.updatingPassword(customerId, password);
	
	}
}
