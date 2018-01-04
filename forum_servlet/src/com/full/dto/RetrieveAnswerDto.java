package com.full.dto;

public class RetrieveAnswerDto {
private String answeredBy;
private String answer;
private String answerId;
private Long noOfVotes;
private Boolean validVote;

public Boolean isValidVote() {
	return validVote;
}
public void setValidVote(Boolean validVote) {
	this.validVote = validVote;
}
public Long getNoOfVotes() {
	return noOfVotes;
}
public void setNoOfVotes(Long noOfVotes) {
	this.noOfVotes = noOfVotes;
}
public String getAnsweredBy() {
	return answeredBy;
}
public void setAnsweredBy(String answeredBy) {
	this.answeredBy = answeredBy;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public String getAnswerId() {
	return answerId;
}
public void setAnswerId(String answerId) {
	this.answerId = answerId;
}

}
