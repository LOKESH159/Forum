package com.full.helper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.full.dto.GenerateUUID;
import com.full.dto.SubmitVoteDto;
import com.full.model.SubmitVoteDao;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class SubmitVote {
	public String  addVote(String questionId,String answerId,String vote,String votedBy){
		System.out.println("IAM IN SUBMIT VOTE AND CALLING DAO");
		SubmitVoteDao submitVoteDao=new SubmitVoteDao();
		boolean isValidVoter=submitVoteDao.isUserAnswered(votedBy,questionId);
		System.out.println("I GOT IS USER ANSWERD VALUE="+isValidVoter);
	
		if(isValidVoter){
			System.out.println("USER ANSWERD VALUE IS NOT NULL");
			List<Filter> list=new ArrayList<>();
			Filter keyFilter1 = new FilterPredicate("VOTED_BY", FilterOperator.EQUAL, votedBy);
			Filter keyFilter2 = new FilterPredicate("ANSWER_ID", FilterOperator.EQUAL,answerId);
			Filter keyFilter3 = new FilterPredicate("QUESTION_ID", FilterOperator.EQUAL,questionId);
			list.add(keyFilter1);
			list.add(keyFilter2);
			list.add(keyFilter3);
			SubmitVoteDto submitVoteDto=new SubmitVoteDto();
			submitVoteDto.setQuestionId(questionId);
			submitVoteDto.setAnswerId(answerId);
			submitVoteDto.setVotedBy(votedBy);
			submitVoteDto.setVoterID(GenerateUUID.generateUUID());
			submitVoteDto.setDate(new Date().getTime());
		
			if(vote.equals("1"))
			submitVoteDto.setVote(true);
			else
		    submitVoteDto.setVote(false);
			System.out.println("VOTE VALUE IN SUBMIT VOTE DTO IS ="+submitVoteDto.getVote());
			System.out.println("IAM CALLING IS ALREADY VOTED METHOD TO DAO");
			String status=submitVoteDao.isAlreadyVoted(list,submitVoteDto);
			
			return status;
			
		}else{
			return "YOU MUST ANSWER ATLEAST ONE QUESTION";
		}
		
	}

}
