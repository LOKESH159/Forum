package com.full.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.full.dto.DataStoreConnection;
import com.full.dto.SubmitVoteDto;
import com.google.appengine.api.datastore.DatastoreService;
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
public class SubmitVoteDao {
	DatastoreService dataStore;
	Query q;
	PreparedQuery pq;
	Entity entity;
	public boolean isUserAnswered(String votedBy,String questionId){
	 dataStore=DataStoreConnection.dataBaseConnection();
	 Key key=KeyFactory.createKey("QUESTIONDB",questionId);
	 try {
	Entity entity=	dataStore.get(key);
	String eMail=(String)entity.getProperty("EMAIL");
	if(eMail.equals(votedBy)){
	return true;
	}
	} catch (EntityNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		Filter keyFilter = new FilterPredicate("ANSWERED_BY", FilterOperator.EQUAL, votedBy);
	    Query q = new Query("ANSWER_DB").setFilter(keyFilter);
	    PreparedQuery pq=dataStore.prepare(q);
	    List lis=pq.asList(FetchOptions.Builder.withDefaults());
		System.out.println(lis);
		if(lis!=null&&!(lis.isEmpty())){
			return true;
		}
		return false;
	}
	public String isAlreadyVoted(List<Filter> list,SubmitVoteDto submitVoteDto){
		System.out.println("IAM ALREADY VOTED METHOD IN DAO");
		try{
		 dataStore=DataStoreConnection.dataBaseConnection();
			Filter filter = CompositeFilterOperator.and(list);
			
			 Query q = new Query("VOTES").setFilter(filter);
			 System.out.println("QUERY VALUE IS ="+q);
			 System.out.println("IAM FILTERING THE VOTES TABLE BASED ON FILTERS");
		PreparedQuery pq=dataStore.prepare(q);
			 System.out.println("MY VOTES FILTER VALUE IS NOT NULL"+pq.asSingleEntity());
			 entity=pq.asSingleEntity();
			    if(entity!=null){
			
				 boolean isVoted=(Boolean)entity.getProperty("VOTE");
				 System.out.println("IS VOTED VALUE IS "+isVoted);
				 if(isVoted==submitVoteDto.getVote()){
					 System.out.println("YOU ALREADY VOTED");
					 return "YOU ALREADY VOTED FOR THIS ANSWER";
				 }
				 else{
					 System.out.println("IAM CALING UPDATE VOTE STATUS");
					 
				 Long noOfVotes=	   updateVoteStatus((String)entity.getProperty("VOTE_ID"),submitVoteDto);
				  System.out.println("NO OF VOTES AFTER UPDATING VOTE STATUS IS ="+noOfVotes);
					   return "WE UPDATED YOUR VOTE STATUS @"+noOfVotes;
				    }
			 }
			  else{
				  System.out.println("IAM CALING UPDATE VOTE STATUS");
				
				Long noOfVotes= addVote(submitVoteDto);
				  System.out.println("NO OF VOTES AFTER ADDING VOTE STATUS IS ="+noOfVotes);
				  return "WE ADDED YOUR VOTE @"+noOfVotes;
			  }
		}
		catch(NullPointerException npe){
			 Long noOfVotes= addVote(submitVoteDto);
			  System.out.println("NO OF VOTES AFTER ADDING VOTE STATUS IS ="+noOfVotes);
			  return "WE ADDED YOUR VOTE @"+noOfVotes;
		}
 }
	public Long addVote(SubmitVoteDto submitVoteDto){
		Entity entity=new Entity("VOTES",submitVoteDto.getVoterID());					 
		entity.setProperty("QUESTION_ID", submitVoteDto.getQuestionId());
		entity.setProperty("ANSWER_ID",submitVoteDto.getAnswerId());
		entity.setProperty("VOTE",submitVoteDto.getVote());
		entity.setProperty("VOTE_ID",submitVoteDto.getVoterID());
		entity.setProperty("VOTED_BY",submitVoteDto.getVotedBy());
		entity.setProperty("VOTED_DATE",submitVoteDto.getDate());
		System.out.println("IAM ADDING COMMENT");
	     dataStore.put(entity);
	 Long noOfVotes=    addVoteInAnswer(submitVoteDto.getAnswerId(),submitVoteDto.getVote());	
	 return noOfVotes;
	}
		public Long  updateVoteStatus(String voterID,SubmitVoteDto submitVoteDto){
			System.out.println("IAM IN UPDATE VOTE STATUS MEATHOD");
			Key voterKey = KeyFactory.createKey("VOTES",voterID);
			Long noOfVotes=(long)0;
			try {
				Entity e = dataStore.get(voterKey);
				System.out.println("ENTITY VALUE IN UPDATE VOTE STATUS IS ="+entity);
				e.setProperty("VOTE",submitVoteDto.getVote());
				dataStore.put(e);
		     noOfVotes=	addVoteInAnswer(submitVoteDto.getAnswerId(),submitVoteDto.getVote());
			
			} catch (EntityNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("I GOT EXCEPTION");
				
			}
			return noOfVotes;
		}
		public Long addVoteInAnswer(String answerId,Boolean vote){
			System.out.println("IAM ADDVOTE IN ANSWER METHOD");
			 Filter keyFilter = new FilterPredicate("ANSWER_ID", FilterOperator.EQUAL, answerId);
		     q = new Query("ANSWER_DB").setFilter(keyFilter);
		     pq=dataStore.prepare(q);
		     entity=pq.asSingleEntity();
		     System.out.println("ENTITY VALUE IN ADD VOTE IN ANSWER METHOD IS ="+entity);
		     try{
		     Long noOfVotes=(Long)entity.getProperty("NOOFVOTES");
		     System.out.println("NO OF VOTES FROM DB IS ="+noOfVotes);
		     boolean validVote;
		     if(vote){
		     noOfVotes=noOfVotes+1;
		     validVote=true;
		     }
		     else{
		      noOfVotes=noOfVotes-1;
		      validVote=false;
		      }
		     
		     System.out.println("AFTER INCREASING VOTES ="+noOfVotes);
		     entity.setProperty("NOOFVOTES",noOfVotes);
		     entity.setProperty("VALIDANSWER", validVote);
		     dataStore.put(entity);
		 return noOfVotes;
		     }
		     catch(NullPointerException npe){
		    	 if(vote){
		    	entity.setProperty("NOOFVOTES",1);
		    	 entity.setProperty("VALIDANSWER", true);
		    	dataStore.put(entity);
		    	return (long) 1;
		   
		    	 }
		    	else{
		    		entity.setProperty("NOOFVOTES",-1);
		    		 entity.setProperty("VALIDANSWER",false);
		    		dataStore.put(entity);
			    	return (long) -1;
		    	}
		    
		     }
		}


}
