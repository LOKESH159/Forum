package com.full.dto;

import org.springframework.stereotype.Component;

@Component
public class SubmitVoteDto {
private String questionId;
private String answerId;
private String votedBy;
private String voterID;
private Boolean vote;

public Boolean getVote() {
	return vote;
}
public void setVote(Boolean vote) {
	this.vote = vote;
}
private long date;
public String getQuestionId() {
	return questionId;
}
public void setQuestionId(String questionId) {
	this.questionId = questionId;
}
public String getAnswerId() {
	return answerId;
}
public void setAnswerId(String answerId) {
	this.answerId = answerId;
}
public String getVotedBy() {
	return votedBy;
}
public void setVotedBy(String votedBy) {
	this.votedBy = votedBy;
}
public String getVoterID() {
	return voterID;
}
public void setVoterID(String voterID) {
	this.voterID = voterID;
}

public long getDate() {
	return date;
}
public void setDate(long date) {
	this.date = date;
}

}
