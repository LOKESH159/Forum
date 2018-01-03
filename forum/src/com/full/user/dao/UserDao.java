package com.full.user.dao;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import com.full.dto.AskQuestionDto;
import com.full.dto.DataStoreConnection;
import com.full.dto.PostAnswerDto;
import com.full.dto.SignUpDto;
import com.full.dto.SubmitCommentDto;
import com.full.dto.SubmitVoteDto;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@Repository
public class UserDao {
	Logger logger=Logger.getLogger(UserDao.class.getClass());
	public boolean checkingUser(String eMail, String password) {
		System.out.println("IAM IN  checkingUser");
		System.out.println(eMail);
		System.out.println(password);
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Key employeeKey = KeyFactory.createKey("USER_ACCOUNT", eMail);
		System.out.println("KEY VALUE IS =" + employeeKey);
		try {
			Entity e = dataStore.get(employeeKey);
			String passwordDb = (String) e.getProperty("PASSWORD");
			System.out.println("PASSWORD DB VALUE IS=" + passwordDb);
			if (passwordDb.equals(password)) {
				System.out.println("PASSWORD IS VALID FOR THE USER");
				return true;
			} else {
				System.out.println("PASSWORD IS NOT VALID FOR THE USER");
				return false;
			}
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("I GOT EXCEPTION");

		}
		return false;
	}

	public boolean checkEMail(String eMail) {
		System.out.println("IAM IN  checkEMail");
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Key employeeKey = KeyFactory.createKey("USER_ACCOUNT", eMail);
		System.out.println(employeeKey);
		try {
			Entity e = dataStore.get(employeeKey);
			System.out.println(e.getProperty("USERNAME"));
			return true;
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("I GOT EXCEPTION");

		}
		return false;

	}

	public void addUserInDb(SignUpDto signUpDto) {
		System.out.println("IAM IN addUserInDb");
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("USER_ACCOUNT", signUpDto.getEMAIL());
		entity.setProperty("USERNAME", signUpDto.getUSERNAME());
		entity.setProperty("PASSWORD", signUpDto.getPASSWORD());
		entity.setProperty("CONFIRMATION", "NO");
		entity.setProperty("CUSTOMER_ID", signUpDto.getCustomerId());
		System.out.println("IAM ADDING USER");
		Key key = dataStore.put(entity);
		System.out.println(key.getId());
	}

	public void askedQuestion(AskQuestionDto askQuestionDto) {
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("QUESTIONDB", askQuestionDto.getUUID());
		entity.setProperty("QUESTIONID", askQuestionDto.getUUID());
		entity.setProperty("EMAIL", askQuestionDto.geteMail());
		entity.setProperty("QUESTION", askQuestionDto.getQuestion());
		entity.setProperty("TECHNOLOGY", askQuestionDto.getTechnology());
		entity.setProperty("QUESTIONED_DATE", askQuestionDto.getQuestionedDate());
		System.out.println("IAM ADDING QUESTION");
		dataStore.put(entity);
	}

	public static PreparedQuery retrieveQuestion(String eMail) {
		System.out.println("IAM IN RETRIEVEQUESTIONDAO");
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		if (eMail != "") {
			Filter keyFilter = new FilterPredicate("EMAIL", FilterOperator.EQUAL, eMail);
			Query q = new Query("QUESTIONDB").setFilter(keyFilter);
			PreparedQuery pq = dataStore.prepare(q);
			return pq;
		} else {
			Query q = new Query("QUESTIONDB");
			PreparedQuery pq = dataStore.prepare(q);
			return pq;
		}
	}

	public static PreparedQuery retrieveAnswerQuestionDao(String eMail) {
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = null;
		if (eMail != "") {
			Filter keyFilter = new FilterPredicate("ANSWERED_BY", FilterOperator.EQUAL, eMail);
			Query q = new Query("ANSWER_DB").setFilter(keyFilter);
			pq = dataStore.prepare(q);

		}
		return pq;
	}

	public static PreparedQuery retrieveAnswerQuestionDao(List<Filter> list) {
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = null;
		Filter filter = CompositeFilterOperator.or(list);
		Query q = new Query("QUESTIONDB").setFilter(filter);
		pq = dataStore.prepare(q);
		return pq;
	}
	public static PreparedQuery retrieveAnswerQuestionDao(Filter filter) {
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = null;
		Query q = new Query("QUESTIONDB").setFilter(filter);
		pq = dataStore.prepare(q);
		return pq;
	}

	public static Entity retrievingIdQuestion(String questionId) {
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Key employeeKey = KeyFactory.createKey("QUESTIONDB", questionId);
		Entity entity = null;
		try {
			entity = dataStore.get(employeeKey);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("I GOT EXCEPTION");

		}
		return entity;
	}

	public String retrieveUUID(String eMail) {

		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Key employeeKey = KeyFactory.createKey("USER_ACCOUNT", eMail);
		String customerId = null;
		try {
			Entity entity = dataStore.get(employeeKey);
			customerId = (String) entity.getProperty("CUSTOMER_ID");
			return customerId;

		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("I GOT EXCEPTION");
		}
		return customerId;
	}

	public static void addAnswerInDb(PostAnswerDto postAnswerDto) {
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("ANSWER_DB", postAnswerDto.getAnswerId());
		entity.setProperty("QUESTION_ID", postAnswerDto.getQuestionId());
		entity.setProperty("ANSWER", postAnswerDto.getAnswer());
		entity.setProperty("ANSWERED_BY", postAnswerDto.getAnsweredBy());
		entity.setProperty("ANSWERED_DATE", postAnswerDto.getAnsweredDate());
		entity.setProperty("ANSWER_ID", postAnswerDto.getAnswerId());
		entity.setProperty("NOOFVOTES", 0);
		entity.setProperty("VALIDANSWER", false);
		System.out.println("IAM ADDING ANSWER");
		Key key = dataStore.put(entity);
		System.out.println(key.getId());

	}

	public static Entity checkingUserMail(String questionId) {
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Key employeeKey = KeyFactory.createKey("QUESTIONDB", questionId);
		Entity entity = null;
		try {
			entity = dataStore.get(employeeKey);
			// System.out.println(entity.getProperty("USERNAME"));
			return entity;
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("I GOT EXCEPTION");

		}
		return entity;
	}

	public static PreparedQuery retrieveAnswer(String questionId) {
		System.out.println("IAM IN RETRIEVEANSWERDAO");
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Filter keyFilter = new FilterPredicate("QUESTION_ID", FilterOperator.EQUAL, questionId);
		Query q = new Query("ANSWER_DB").setFilter(keyFilter);
		PreparedQuery pq = dataStore.prepare(q);
		return pq;

	}

	public static PreparedQuery retrieveComments(String questionId) {
		System.out.println("IAM IN RETRIEVECOMMENT DAO");
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Filter keyFilter = new FilterPredicate("QUESTION_ID", FilterOperator.EQUAL, questionId);
		Query q = new Query("COMMENTS").setFilter(keyFilter);
		PreparedQuery pq = dataStore.prepare(q);
		return pq;
	}

	public PreparedQuery retrieveSearchQuestions(String technology) {
		System.out.println("IAM IN RETRIEVEQUESTIONDAO");
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		PreparedQuery pq = null;
		if (technology != "") {
			Filter keyFilter = new FilterPredicate("TECHNOLOGY", FilterOperator.EQUAL, technology);
			Query q = new Query("QUESTIONDB").setFilter(keyFilter);
			pq = dataStore.prepare(q);

		}
		return pq;
	}

	public void addComment(SubmitCommentDto submitCommentDto) {
		DatastoreService dataStore = DatastoreServiceFactory.getDatastoreService();
		Entity entity = new Entity("COMMENTS", submitCommentDto.getCommentId());
		entity.setProperty("QUESTION_ID", submitCommentDto.getQuestionId());
		entity.setProperty("ANSWER_ID", submitCommentDto.getAnswerId());
		entity.setProperty("COMMENT", submitCommentDto.getComment());
		entity.setProperty("COMMENT_ID", submitCommentDto.getCommentId());
		entity.setProperty("COMMENTED_By", submitCommentDto.getCommentedBy());
		entity.setProperty("COMMENT_date", submitCommentDto.getDate());
		System.out.println("IAM ADDING COMMENT");
		dataStore.put(entity);
	}

	public boolean isUserAnswered(String votedBy, String questionId) {
		DatastoreService dataStore = DataStoreConnection.dataBaseConnection();
		Key key = KeyFactory.createKey("QUESTIONDB", questionId);
		try {
			Entity entity = dataStore.get(key);
			String eMail = (String) entity.getProperty("EMAIL");
			if (eMail.equals(votedBy)) {
				return true;
			}
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Filter keyFilter = new FilterPredicate("ANSWERED_BY", FilterOperator.EQUAL, votedBy);
		Query q = new Query("ANSWER_DB").setFilter(keyFilter);
		PreparedQuery pq = dataStore.prepare(q);
		List lis = pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println(lis);
		if (lis != null && !(lis.isEmpty())) {
			return true;
		}
		return false;
	}

	public String isAlreadyVoted(List<Filter> list, SubmitVoteDto submitVoteDto) {
		System.out.println("IAM ALREADY VOTED METHOD IN DAO");
		try {
			DatastoreService dataStore = DataStoreConnection.dataBaseConnection();
			Filter filter = CompositeFilterOperator.and(list);

			Query q = new Query("VOTES").setFilter(filter);
			System.out.println("QUERY VALUE IS =" + q);
			System.out.println("IAM FILTERING THE VOTES TABLE BASED ON FILTERS");
			PreparedQuery pq = dataStore.prepare(q);
			System.out.println("MY VOTES FILTER VALUE IS NOT NULL" + pq.asSingleEntity());
			Entity entity = pq.asSingleEntity();
			if (entity != null) {

				boolean isVoted = (Boolean) entity.getProperty("VOTE");
				System.out.println("IS VOTED VALUE IS " + isVoted);
				if (isVoted == submitVoteDto.getVote()) {
					System.out.println("YOU ALREADY VOTED");
					return "YOU ALREADY VOTED FOR THIS ANSWER";
				} else {
					System.out.println("IAM CALING UPDATE VOTE STATUS");

					Long noOfVotes = updateVoteStatus((String) entity.getProperty("VOTE_ID"), submitVoteDto);
					System.out.println("NO OF VOTES AFTER UPDATING VOTE STATUS IS =" + noOfVotes);
					return "WE UPDATED YOUR VOTE STATUS @" + noOfVotes;
				}
			} else {
				System.out.println("IAM CALING UPDATE VOTE STATUS");

				Long noOfVotes = addVote(submitVoteDto);
				System.out.println("NO OF VOTES AFTER ADDING VOTE STATUS IS =" + noOfVotes);
				return "WE ADDED YOUR VOTE @" + noOfVotes;
			}
		} catch (NullPointerException npe) {
			Long noOfVotes = addVote(submitVoteDto);
			System.out.println("NO OF VOTES AFTER ADDING VOTE STATUS IS =" + noOfVotes);
			return "WE ADDED YOUR VOTE @" + noOfVotes;
		}
	}

	public Long addVote(SubmitVoteDto submitVoteDto) {
		DatastoreService dataStore = DataStoreConnection.dataBaseConnection();
		Entity entity = new Entity("VOTES", submitVoteDto.getVoterID());
		entity.setProperty("QUESTION_ID", submitVoteDto.getQuestionId());
		entity.setProperty("ANSWER_ID", submitVoteDto.getAnswerId());
		entity.setProperty("VOTE", submitVoteDto.getVote());
		entity.setProperty("VOTE_ID", submitVoteDto.getVoterID());
		entity.setProperty("VOTED_BY", submitVoteDto.getVotedBy());
		entity.setProperty("VOTED_DATE", submitVoteDto.getDate());
		System.out.println("IAM ADDING COMMENT");
		dataStore.put(entity);
		Long noOfVotes = addVoteInAnswer(submitVoteDto.getAnswerId(), submitVoteDto.getVote());
		return noOfVotes;
	}

	public Long updateVoteStatus(String voterID, SubmitVoteDto submitVoteDto) {
		System.out.println("IAM IN UPDATE VOTE STATUS MEATHOD");
		DatastoreService dataStore = DataStoreConnection.dataBaseConnection();
		Key voterKey = KeyFactory.createKey("VOTES", voterID);
		Long noOfVotes = (long) 0;
		try {
			Entity e = dataStore.get(voterKey);
			System.out.println("ENTITY VALUE IN UPDATE VOTE STATUS IS =" + e);
			e.setProperty("VOTE", submitVoteDto.getVote());
			dataStore.put(e);
			noOfVotes = addVoteInAnswer(submitVoteDto.getAnswerId(), submitVoteDto.getVote());

		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("I GOT EXCEPTION");

		}
		return noOfVotes;
	}

	public Long addVoteInAnswer(String answerId, Boolean vote) {
		System.out.println("IAM ADDVOTE IN ANSWER METHOD");
		DatastoreService dataStore = DataStoreConnection.dataBaseConnection();
		Filter keyFilter = new FilterPredicate("ANSWER_ID", FilterOperator.EQUAL, answerId);
		Query q = new Query("ANSWER_DB").setFilter(keyFilter);
		PreparedQuery pq = dataStore.prepare(q);
		Entity entity = pq.asSingleEntity();
		System.out.println("ENTITY VALUE IN ADD VOTE IN ANSWER METHOD IS =" + entity);
		try {
			Long noOfVotes = (Long) entity.getProperty("NOOFVOTES");
			System.out.println("NO OF VOTES FROM DB IS =" + noOfVotes);
			boolean validVote;
			if (vote) {
				noOfVotes = noOfVotes + 1;
				validVote = true;
			} else {
				noOfVotes = noOfVotes - 1;
				validVote = false;
			}

			System.out.println("AFTER INCREASING VOTES =" + noOfVotes);
			entity.setProperty("NOOFVOTES", noOfVotes);
			entity.setProperty("VALIDANSWER", validVote);
			dataStore.put(entity);
			return noOfVotes;
		} catch (NullPointerException npe) {
			if (vote) {
				entity.setProperty("NOOFVOTES", 1);
				entity.setProperty("VALIDANSWER", true);
				dataStore.put(entity);
				return (long) 1;

			} else {
				entity.setProperty("NOOFVOTES", -1);
				entity.setProperty("VALIDANSWER", false);
				dataStore.put(entity);
				return (long) -1;
			}

		}
	}
	public  PreparedQuery confirmUser(String customerId){
		logger.info("IAM IN SIGNUP CONFIRMATION DAO AND HE CUSTOMER ID VALUE IS "+customerId);
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		Filter keyFilter = new FilterPredicate("CUSTOMER_ID", FilterOperator.EQUAL,customerId);
	    Query q = new Query("USER_ACCOUNT").setFilter(keyFilter);
	    System.out.println("QUERY VALUE IS ="+q);
	    PreparedQuery pq=dataStore.prepare(q);
	    System.out.println("PQ AS SINGLE ENTITY IS="+pq.asSingleEntity());
	    return pq;

	}
	public void updatingUserStatus(Entity entity){
		logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN updatingUserStatus");
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		    entity.setProperty("CONFIRMATION", "YES");
		    dataStore.put(entity);
		    logger.info("IAM IN SIGN UP CONFIRMATION AND IAM IN STORED THE ENTITY");
	}
	public  boolean updatingPassword(String customerId,String password){
		logger.info("IAM IN UPDATING PASSWORD DAO METHOD");
		System.out.println("IAM IN  checkingUser");
		System.out.println(customerId);
		System.out.println(password);
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		Filter keyFilter = new FilterPredicate("CUSTOMER_ID", FilterOperator.EQUAL,customerId);
	    Query q = new Query("USER_ACCOUNT").setFilter(keyFilter);
	    PreparedQuery pq=dataStore.prepare(q);
	try {
		for(Entity entity:pq.asIterable()){
			System.out.println("IAM IN PASSWORD RECOVERY DAO THE ENTITY IS ="+entity);
			logger.info("IAM IN PASSWORD RECOVERY DAO THE ENTITY IS ="+entity);
			entity.setProperty("PASSWORD",password);
			dataStore.put(entity);
			logger.info("I STORED THE VALUE IN DATASTORE");
			return true;
		}
	}
	catch(Exception e){
		logger.info("I GOT EXCEPTION IAM RETURNING FALSE");
	}
	return false;
		}

}
