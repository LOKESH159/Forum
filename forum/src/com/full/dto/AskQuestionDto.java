package com.full.dto;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class AskQuestionDto {
	private String eMail;
	private String technology;
	private String UUID;
	private String question;
	private Date questionedDate;
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getTechnology() {
		return technology;
	}
	public void setTechnology(String technology) {
		this.technology = technology;
	}
	public String getUUID() {
		return UUID;
	}
	public void setUUID(String uUID) {
		UUID = uUID;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public Date getQuestionedDate() {
		return questionedDate;
	}
	public void setQuestionedDate(Date questionedDate) {
		this.questionedDate = questionedDate;
	}

}
