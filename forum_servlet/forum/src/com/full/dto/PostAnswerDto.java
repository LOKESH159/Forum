package com.full.dto;

import java.util.Date;

public class PostAnswerDto {
private String answerId;
private String questionId;
private String answer;
private Date answeredDate;
private String answeredBy;
public String getAnswerId() {
	return answerId;
}
public void setAnswerId(String answerId) {
	this.answerId = answerId;
}
public String getQuestionId() {
	return questionId;
}
public void setQuestionId(String questionId) {
	this.questionId = questionId;
}
public String getAnswer() {
	return answer;
}
public void setAnswer(String answer) {
	this.answer = answer;
}
public Date getAnsweredDate() {
	return answeredDate;
}
public void setAnsweredDate(Date answeredDate) {
	this.answeredDate = answeredDate;
}
public String getAnsweredBy() {
	return answeredBy;
}
public void setAnsweredBy(String answeredBy) {
	this.answeredBy = answeredBy;
}

}
