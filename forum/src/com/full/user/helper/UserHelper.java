package com.full.user.helper;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.full.dto.AskQuestionDto;
import com.full.dto.GenerateUUID;
import com.full.dto.MailServices;
import com.full.dto.PostAnswerDto;
import com.full.dto.RetrieveAnswerDto;
import com.full.dto.RetrieveCommentsDto;
import com.full.dto.RetrieveQuestionDto;
import com.full.dto.SignUpDto;
import com.full.dto.SubmitCommentDto;
import com.full.dto.SubmitVoteDto;
import com.full.user.dao.UserDao;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class UserHelper {
	Logger logger = Logger.getLogger(UserHelper.class.getName());
	@Autowired
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String checkUser(String eMail, String password, HttpServletRequest req) {
		// CheckUserDao checkUserDao=new CheckUserDao();
		System.out.println("IAM IN CHECKUSER");
		boolean userExists = userDao.checkingUser(eMail, password);
		JsonObject jsonObject = new JsonObject();
		if (userExists) {
			System.out.println("USER EXISTS ");
			HttpSession session = req.getSession(true);
			session.setAttribute("EMAIL", eMail);
			session.setAttribute("PASSWORD", password);
			session.setMaxInactiveInterval(120);
			jsonObject.addProperty("status", true);
		} else {
			System.out.println("USER  NOT EXISTS ");
			jsonObject.addProperty("status", false);

		}
		return jsonObject.toString();
	}

	public String CheckingLogin(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		JsonObject jsonObject = new JsonObject();
		if (session != null) {
			jsonObject.addProperty("loggedStatus", true);
		} else {
			jsonObject.addProperty("loggedStatus", false);
		}
		return jsonObject.toString();
	}

	public String isEMailExist(String eMail) {

		boolean isExist = userDao.checkEMail(eMail);
		System.out.println(isExist);
		JsonObject jsonObject = new JsonObject();
		if (isExist) {
			jsonObject.addProperty("mailExistStatus", true);
		}
		else{
		jsonObject.addProperty("mailExistStatus", false);
		}
		return jsonObject.toString();
	}

	public String addUser(HttpServletRequest req) {
		String userName = req.getParameter("USERNAME");
		String eMail = req.getParameter("EMAIL");
		String password = req.getParameter("PASSWORD");
		String confirmPassword = req.getParameter("CONFIRMPASSWORD");
		SignUpDto signUpDto = new SignUpDto();
		signUpDto.setUSERNAME(userName);
		signUpDto.setEMAIL(eMail);
		signUpDto.setPASSWORD(password);
		signUpDto.setCONFIRMPASSWORD(confirmPassword);
		System.out.println(eMail);
		System.out.println(userName);
		System.out.println(password);
		System.out.println(confirmPassword);
		signUpDto.setCustomerId(GenerateUUID.generateUUID());
		userDao.addUserInDb(signUpDto);
		sendSimpleMail(signUpDto.getEMAIL(), signUpDto.getCustomerId());
		JsonObject jsonObject=new JsonObject();
		jsonObject.addProperty("eMailStatus","CONFIRMATION MAIL WAS SEND TO YOUR MAIL");
		jsonObject.addProperty("signUpStatus","SIGN UP WAS SUCCESSFULLY DONE");
		return jsonObject.toString();
	}
	public  void sendSimpleMail(String usereMail,String customerId) {
        String appUrl; 
        String environment = System.getProperty("com.google.appengine.runtime.environment");
        if (environment.equals("Production")) {
            String applicationId = System.getProperty("com.google.appengine.application.id");
            String version = System.getProperty("com.google.appengine.application.version");
            appUrl = "http://localhost:8888/user/signUpConfirmation?customerId="+customerId;
        } else {
            appUrl = "http://localhost:8888/user/signUpConfirmation?customerId="+customerId;
        }
        logger.info("THE URL IS ="+appUrl);
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
 
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("lokesh.rao@anywhere.co", "Test Email"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(usereMail, "Mr. User"));
            msg.setSubject("FORUM CONFIRMATION");
            msg.setText("THE CUSTOMER ID IS ="
                    +customerId); 
            logger.info("THE TEXT IS="+customerId);
            logger.info("MESSAGE IS READY");
            Transport.send(msg);
            logger.info("MESSAGE SENT SUCCESSFULLY");
            System.out.println("Email Sent");
        } catch (Exception e) {
        	logger.info("MAIL NOT SENT SUCCESSFULLY"+e.getMessage());
        } 
 
    }
	public void askedQuestion(HttpServletRequest req) {
		System.out.println("IAM IN ASKQUESTION SERVLET");
		String technology = req.getParameter("TECHNOLOGY");
		String question = req.getParameter("QUESTION");
		System.out.println(technology);
		System.out.println(question);
		HttpSession session = req.getSession(false);
		String eMail = (String) session.getAttribute("EMAIL");
		System.out.println("EMAIL FROM SESSION IS=" + eMail);
		String UUID = GenerateUUID.generateUUID();
		AskQuestionDto askQuestionDto = new AskQuestionDto();
		askQuestionDto.seteMail(eMail);
		askQuestionDto.setUUID(UUID);
		askQuestionDto.setTechnology(technology);
		askQuestionDto.setQuestion(question);
		askQuestionDto.setQuestionedDate(new java.util.Date());
		userDao.askedQuestion(askQuestionDto);

	}

	public String retrieveQuestion(HttpServletRequest req) {
		String question = req.getParameter("QUESTIONS");
		String value = req.getParameter("REQUIRED");
		System.out.println("VALUE IS =" + value);
		HttpSession session = req.getSession(false);
		if (session != null && "USER".equalsIgnoreCase(question)) {
			if ("Questions".equals(value)) {
				String eMail = (String) session.getAttribute("EMAIL");
				String gsonQuestion = retrieveQuestions(eMail);
				return gsonQuestion;
			} else {
				System.out.println("IAM IN RETRIEVE ANSWERQUESTION METHOD");
				String eMail = (String) session.getAttribute("EMAIL");
				// RetriveQuestion retrieveQuestion=new RetriveQuestion();
				String gsonQuestion = retrieveAnswerQuestion(eMail);
				return gsonQuestion;
			}
		} else {
			// RetriveQuestion retrieveQuestion=new RetriveQuestion();
			String gsonQuestion = retrieveQuestions("");
			// logger.info("IAM NOT GETTING ERROR BEFORE"+gsonQuestion);
			return gsonQuestion;

		}
	}

	public String retrieveQuestions(String eMail) {
		PreparedQuery pq = userDao.retrieveQuestion(eMail);
		System.out.println("IAM IN RETRIEVEQUESTION");
		List<RetrieveQuestionDto> questionList = new LinkedList<RetrieveQuestionDto>();
		for (Entity entity : pq.asIterable()) {
			System.out.println("I GOT THE VALUES IN RETRIEVE QUESTION");
			// System.out.println(entity);
			String question = (String) entity.getProperty("QUESTION");
			String uuid = (String) entity.getProperty("QUESTIONID");
			System.out.println(uuid);
			System.out.println(question);
			RetrieveQuestionDto retrieveQuestionDto = new RetrieveQuestionDto();
			retrieveQuestionDto.setQuestionId(uuid);
			retrieveQuestionDto.setQuestion(question);
			questionList.add(retrieveQuestionDto);
		}
		// logger.info("IAM IN RETRIEVE QUESTION HELPER BEFORE");
		Gson gson = new Gson();
		// logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION ");
		String gsonQuestion = gson.toJson(questionList);
		// logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION JSON");
		System.out.println(gsonQuestion);
		return gsonQuestion;
	}

	public String retrieveAnswerQuestion(String eMail) {
		PreparedQuery pq = userDao.retrieveAnswerQuestionDao(eMail);
		Set<String> set = new HashSet<String>();
		String gsonQuestion = null;
		if (pq != null) {
			System.out.println("I GOT ANSWERS FROM DB");
			for (Entity entity : pq.asIterable()) {
				System.out.println("I GOT THE VALUES IN RETRIEVE ANSWER QUESTION");
				// System.out.println(entity);
				String uuid = (String) entity.getProperty("QUESTION_ID");
				System.out.println(uuid);
				set.add(uuid);
			}
			System.out.println("SET VALUE IS :" + set);
			
			Iterator iterator = set.iterator();
			List<Filter> list = new LinkedList<Filter>();
			
			while (iterator.hasNext()) {
				String uuid = (String) iterator.next();
				Filter keyFilter = new FilterPredicate("QUESTIONID", FilterOperator.EQUAL, uuid);
				if(set.size()>=2){
				list.add(keyFilter);
				}else{
					pq = UserDao.retrieveAnswerQuestionDao(keyFilter);
					break;
				}
			}
			if(set.size()>=2){
			pq = UserDao.retrieveAnswerQuestionDao(list);
			}
			if (pq != null) {
				System.out.println("I GOT VALUES THROUGH OR CONDITION ALSO");
				List<RetrieveQuestionDto> questionList = new ArrayList<RetrieveQuestionDto>();
				for (Entity entity : pq.asIterable()) {
					System.out.println("I GOT THE VALUES IN RETRIEVE QUESTION");
					// System.out.println(entity);
					String question = (String) entity.getProperty("QUESTION");
					String uuid = (String) entity.getProperty("QUESTIONID");
					System.out.println(uuid);
					System.out.println(question);
					RetrieveQuestionDto retrieveQuestionDto = new RetrieveQuestionDto();
					retrieveQuestionDto.setQuestionId(uuid);
					retrieveQuestionDto.setQuestion(question);
					questionList.add(retrieveQuestionDto);
				}
				// logger.info("IAM IN RETRIEVE QUESTION HELPER BEFORE");
				Gson gson = new Gson();
				// logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION
				// ");
				gsonQuestion = gson.toJson(questionList);
				// logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION
				// JSON");
				System.out.println(gsonQuestion);

			}
		}
		return gsonQuestion;

	}

	public String retrievingIdQuestion(String questionID) {
		Entity entity = userDao.retrievingIdQuestion(questionID);
		JsonObject jsonObject = new JsonObject();
		if (entity != null) {
			String question = (String) entity.getProperty("QUESTION");
			jsonObject.addProperty("idQuestion", question);

		} else {
			jsonObject.addProperty("idQuestion", false);
		}

		return jsonObject.toString();
	}

	public String sendMail(String eMail) {
		Properties properties = new Properties();
		Session session = Session.getDefaultInstance(properties, null);
		System.out.println("Properties value is =" + properties);
		JsonObject jsonObject = new JsonObject();
		try {
			String customerId = userDao.retrieveUUID(eMail);
			URL url = new URL("http://localhost:8888/passwordrecovery.jsp?eMail=" + eMail);
			StringBuffer sb = new StringBuffer();
			sb.append("<html><body>");
			sb.append("<h1>PASSWORD RECOVERY MAIL</h1>" + "<br/>");

			// sb.append("<a href="+url+">PLEASE CLICK HERE FOR PASSWORD
			// RECOVERY</a>");
			sb.append("THE CUSTOMER ID IS=" + customerId);
			sb.append("</table>" + "</body>" + "</html>");
			String link = sb.toString();
			// System.out.println("THE BODY IN MAIL IS ="+link);
			logger.info("THE BODY IN MAIL IS =" + link);
			MimeMessage msg = new MimeMessage(session);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.setFrom(new InternetAddress("lokesh.rao@anywhere.co"));
			msg.setReplyTo(InternetAddress.parse("lokesh.rao@anywhere.co", false));
			msg.setSubject("PASSWORD RECOVERY");
			msg.setContent(link, "text/html");
			msg.setSentDate(new Date());
			msg.addRecipient(RecipientType.TO, new InternetAddress(eMail));
			logger.info("Message is ready");
			Transport.send(msg);
			logger.info("message sent successfully");
			jsonObject.addProperty("mailStatus", true);
		} catch (MessagingException | MalformedURLException e) {
			logger.info("I GOT EXCEPTION IN MAILER");
			// logger.log(Level.INFO, e.getMessage(), e);
			logger.info("exception is " + e.getMessage());
			jsonObject.addProperty("mailStatus", false);
		}
		return jsonObject.toString();
	}

	public String addAnswer(HttpServletRequest req) {
		String answer = req.getParameter("ANSWER");
		String questionId = req.getParameter("QUESTIONID");
		HttpSession session = req.getSession(false);
		JsonObject jsonObject = new JsonObject();
		if (session != null) {
			String answeredBy = (String) session.getAttribute("EMAIL");
			boolean isSameUser = checkingPostedUser(answeredBy, questionId);
			if (isSameUser) {
				jsonObject.addProperty("answerStatus", true);

			} else {
				addAnswer(questionId, answer, answeredBy);
				jsonObject.addProperty("answerStatus", "ADDED SUCCESSFULLY");
			}
		} else {
			jsonObject.addProperty("answerStatus", "ERROR");

		}
		return jsonObject.toString();
	}

	public void addAnswer(String questionId, String answer, String answeredBy) {
		PostAnswerDto postAnswerDto = new PostAnswerDto();
		postAnswerDto.setQuestionId(questionId);
		postAnswerDto.setAnswerId(GenerateUUID.generateUUID());
		postAnswerDto.setAnswer(answer);
		postAnswerDto.setAnsweredBy(answeredBy);
		postAnswerDto.setAnsweredDate(new Date());
		userDao.addAnswerInDb(postAnswerDto);
	}

	public boolean checkingPostedUser(String eMail, String questionId) {
		Entity entity = userDao.checkingUserMail(questionId);
		if (entity != null) {
			String questionedMail = (String) entity.getProperty("EMAIL");
			if (questionedMail.equals(eMail)) {
				return true;
			}

		}
		return false;
	}

	public String retrieveAnswers(String questionID) {
		PreparedQuery pq = userDao.retrieveAnswer(questionID);
		System.out.println("IAM IN RETRIEVEQUESTION");
		List<RetrieveAnswerDto> answerList = new LinkedList<RetrieveAnswerDto>();
		for (Entity entity : pq.asIterable()) {
			System.out.println("I GOT THE VALUES IN RETRIEVE QUESTION");
			// System.out.println(entity);
			String answeredBy = (String) entity.getProperty("ANSWERED_BY");
			String answer = (String) entity.getProperty("ANSWER");
			String answerId = (String) entity.getProperty("ANSWER_ID");
			Long noOfVotes = (Long) entity.getProperty("NOOFVOTES");
			Boolean validAnswer = new Boolean(false);
			try {
				validAnswer = (Boolean) entity.getProperty("VALIDANSWER");
				logger.info("I DIDN't got null pointer exception");
			} catch (NullPointerException e) {
				logger.info("I GOT NULL POINTER EXCEPTION");

			}
			System.out.println(answeredBy);
			System.out.println(answer);
			System.out.println(answerId);
			System.out.println(noOfVotes);
			System.out.println(validAnswer);
			RetrieveAnswerDto retrieveAnswerDto = new RetrieveAnswerDto();
			retrieveAnswerDto.setAnsweredBy(answeredBy);
			retrieveAnswerDto.setAnswer(answer);
			retrieveAnswerDto.setAnswerId(answerId);
			retrieveAnswerDto.setNoOfVotes(noOfVotes);
			logger.info("THE VALID VOTE IAM SETTING =" + validAnswer);
			retrieveAnswerDto.setValidVote(validAnswer);
			answerList.add(retrieveAnswerDto);

		}
		pq = userDao.retrieveComments(questionID);
		HashMap<String, List<RetrieveCommentsDto>> hm = new HashMap<>();
		for (Entity entity : pq.asIterable()) {
			System.out.println("I GOT THE VALUES IN RETRIEVE QUESTION");
			System.out.println(entity);
			String commentedBy = (String) entity.getProperty("COMMENTED_By");
			String comment = (String) entity.getProperty("COMMENT");
			String answerId = (String) entity.getProperty("ANSWER_ID");
			System.out.println(commentedBy);
			System.out.println(comment);
			System.out.println(answerId);
			if (hm.containsKey(answerId)) {
				System.out.println("I ALREADY HAVE ANSWER LIST");
				List<RetrieveCommentsDto> li = (List<RetrieveCommentsDto>) hm.get(answerId);
				RetrieveCommentsDto retrieveCommentsDto = new RetrieveCommentsDto();
				retrieveCommentsDto.setComment(comment);
				retrieveCommentsDto.setCommentedBy(commentedBy);
				li.add(retrieveCommentsDto);
				hm.put(answerId, li);
			} else {
				System.out.println("I ADDING NEW ANSWER LIST");
				List<RetrieveCommentsDto> li = new ArrayList<RetrieveCommentsDto>();
				RetrieveCommentsDto retrieveCommentsDto = new RetrieveCommentsDto();
				retrieveCommentsDto.setComment(comment);
				retrieveCommentsDto.setCommentedBy(commentedBy);
				li.add(retrieveCommentsDto);
				hm.put(answerId, li);
			}

		}
		Gson gson = new Gson();
		String gsonAnswers = gson.toJson(answerList);
		String gsonComments = gson.toJson(hm);
		System.out.println(gsonComments);
		System.out.println(gsonAnswers);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("ANSWERS", gsonAnswers);
		jsonObject.addProperty("COMMENTS", gsonComments);
		return jsonObject.toString();
	}

	public String retrieveSearchQuestion(String technology) {
		PreparedQuery pq = userDao.retrieveSearchQuestions(technology);
		List<RetrieveQuestionDto> questionList = new LinkedList<RetrieveQuestionDto>();
		for (Entity entity : pq.asIterable()) {
			System.out.println("I GOT THE VALUES IN RETRIEVE QUESTION");
			// System.out.println(entity);
			String question = (String) entity.getProperty("QUESTION");
			String uuid = (String) entity.getProperty("QUESTIONID");
			System.out.println(uuid);
			System.out.println(question);
			RetrieveQuestionDto retrieveQuestionDto = new RetrieveQuestionDto();
			retrieveQuestionDto.setQuestionId(uuid);
			retrieveQuestionDto.setQuestion(question);
			questionList.add(retrieveQuestionDto);
		}
		logger.info("IAM IN RETRIEVE QUESTION HELPER BEFORE");
		Gson gson = new Gson();
		logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION ");
		String gsonQuestion = gson.toJson(questionList);
		logger.info("IAM IN RETRIEVE QUESTION HELPER AFTER CREATION JSON");
		System.out.println(gsonQuestion);
		if (gsonQuestion != null) {
			System.out.println("iam NOT NULL");
			return gsonQuestion;
		}

		return "";
	}

	public String addComment(HttpServletRequest req) {
		String answerId = req.getParameter("ANSWERID");
		String questionId = req.getParameter("QUESTIONID");
		String comment = req.getParameter("COMMENT");
		System.out.println(answerId);
		System.out.println(questionId);
		System.out.println(comment);
		HttpSession session = req.getSession(false);
		String commentedBy = (String) session.getAttribute("EMAIL");
		System.out.println(commentedBy);
		SubmitCommentDto submitCommentDto = new SubmitCommentDto();
		submitCommentDto.setQuestionId(questionId);
		submitCommentDto.setAnswerId(answerId);
		submitCommentDto.setComment(comment);
		submitCommentDto.setCommentedBy(commentedBy);
		submitCommentDto.setCommentId(GenerateUUID.generateUUID());
		submitCommentDto.setDate(new Date().getTime());
		userDao.addComment(submitCommentDto);
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("commentStatus", "YOUR COMMENT IS SUCCESSFULLY ADDED");
		return jsonObject.toString();
	}

	public String addVote(HttpServletRequest req) {
		String answerId = req.getParameter("ANSWERID");
		String questionId = req.getParameter("QUESTIONID");
		String vote = req.getParameter("VOTE");
		System.out.println(answerId);
		System.out.println(questionId);
		System.out.println(vote);
		HttpSession session = req.getSession(false);
		String votedBy = (String) session.getAttribute("EMAIL");
		System.out.println(votedBy);
		System.out.println("IAM IN SUBMIT VOTE AND CALLING DAO");
		boolean isValidVoter = userDao.isUserAnswered(votedBy, questionId);
		System.out.println("I GOT IS USER ANSWERD VALUE=" + isValidVoter);
		JsonObject jsonObject = new JsonObject();
		if (isValidVoter) {
			System.out.println("USER ANSWERD VALUE IS NOT NULL");
			List<Filter> list = new LinkedList<>();
			Filter keyFilter1 = new FilterPredicate("VOTED_BY", FilterOperator.EQUAL, votedBy);
			Filter keyFilter2 = new FilterPredicate("ANSWER_ID", FilterOperator.EQUAL, answerId);
			Filter keyFilter3 = new FilterPredicate("QUESTION_ID", FilterOperator.EQUAL, questionId);
			list.add(keyFilter1);
			list.add(keyFilter2);
			list.add(keyFilter3);
			SubmitVoteDto submitVoteDto = new SubmitVoteDto();
			submitVoteDto.setQuestionId(questionId);
			submitVoteDto.setAnswerId(answerId);
			submitVoteDto.setVotedBy(votedBy);
			submitVoteDto.setVoterID(GenerateUUID.generateUUID());
			submitVoteDto.setDate(new Date().getTime());

			if (vote.equals("1"))
				submitVoteDto.setVote(true);
			else
				submitVoteDto.setVote(false);
			System.out.println("VOTE VALUE IN SUBMIT VOTE DTO IS =" + submitVoteDto.getVote());
			System.out.println("IAM CALLING IS ALREADY VOTED METHOD TO DAO");
			String status = userDao.isAlreadyVoted(list, submitVoteDto);
			jsonObject.addProperty("voteStatus", status);

		} else {
			jsonObject.addProperty("voteStatus", "YOU MUST ANSWER ATLEAST ONE QUESTION");

		}
		return jsonObject.toString();
	}
	public String userConfirmation(String customerId){
		logger.info("IAM IN SIGN UP CONFIRMATION AND IAM CALLING DAO CLASS");
		PreparedQuery pq= userDao.confirmUser(customerId);
		logger.info("IAM IN SIGN UP CONFIRMATION AND I GOT PQ");
		JsonObject jsonObject=new JsonObject();
		for(Entity entity:pq.asIterable()){
			logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN FOR EACH LOOP");
			if(entity!=null){
				logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN ENTITY IS NOT NULL");
				String confirmation=(String)entity.getProperty("CONFIRMATION");
				if(confirmation.equalsIgnoreCase("YES")){
					logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN STATUS IS YES");
					jsonObject.addProperty("signUpConfirmation",true);
					
				}
				else{
					logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN STATUS IS NO");
					userDao.updatingUserStatus(entity);
					jsonObject.addProperty("signUpConfirmation",false);
				}
			}
			else{
				jsonObject.addProperty("signUpConfirmation",false);
			}
		}
		return jsonObject.toString();
	}
	public  String updatingPassword(String customerId,String password){
		logger.info("IAM IN UPDATING PASSWORD METHOD");
		JsonObject jsonObject=new JsonObject();
		
		boolean isPasswordUpdated= userDao.updatingPassword(customerId, password);
		if(isPasswordUpdated){
			logger.info("PASSWORD IS UPDATED SUCCESSFULLY");
			System.out.println("THE PASSWORD IS UPDATED SUCCESSFULLY");
			jsonObject.addProperty("updatePasswordStatus",true);
		
		}
		else{
			logger.info("PASSWORD IS  NOT UPDATED SUCCESSFULLY");
			System.out.println("THE PASSWORD IS NOT UPDATED SUCCESSFULLY");
			jsonObject.addProperty("updatePasswordStatus",false);
		}
		return jsonObject.toString();
	}
}
