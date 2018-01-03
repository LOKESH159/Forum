package com.full.helper;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.full.dto.GenerateUUID;
import com.full.dto.SubmitVoteDto;
import com.full.model.SubmitVoteDao;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
@Service
public class SubmitVote {
	@Autowired
	private SubmitVoteDao submitVoteDao;
	@Autowired
	private SubmitVoteDto submitVoteDto;
	public void setSubmitVoteDao(SubmitVoteDao submitVoteDao) {
		this.submitVoteDao = submitVoteDao;
	}
	public void setSubmitVoteDto(SubmitVoteDto submitVoteDto) {
		this.submitVoteDto = submitVoteDto;
	}
	public String  addVote(String questionId,String answerId,String vote,String votedBy){
		System.out.println("IAM IN SUBMIT VOTE AND CALLING DAO");
		boolean isValidVoter=submitVoteDao.isUserAnswered(votedBy,questionId);
		System.out.println("I GOT IS USER ANSWERD VALUE="+isValidVoter);
	
		if(isValidVoter){
			System.out.println("USER ANSWERD VALUE IS NOT NULL");
			List<Filter> list=new LinkedList<>();
			Filter keyFilter1 = new FilterPredicate("VOTED_BY", FilterOperator.EQUAL, votedBy);
			Filter keyFilter2 = new FilterPredicate("ANSWER_ID", FilterOperator.EQUAL,answerId);
			Filter keyFilter3 = new FilterPredicate("QUESTION_ID", FilterOperator.EQUAL,questionId);
			list.add(keyFilter1);
			list.add(keyFilter2);
			list.add(keyFilter3);
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
