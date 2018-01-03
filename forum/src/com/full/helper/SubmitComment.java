package com.full.helper;

import java.nio.channels.AsynchronousByteChannel;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.full.dto.GenerateUUID;
import com.full.dto.SubmitCommentDto;
import com.full.model.SubmitCommentDao;
@Service
public class SubmitComment {
	private SubmitCommentDao submitCommentDao;
	private SubmitCommentDto submitCommentDto;
	@Autowired
	public void setSubmitCommentDao(SubmitCommentDao submitCommentDao) {
		this.submitCommentDao = submitCommentDao;
	}
@Autowired
	public void setSubmitCommentDto(SubmitCommentDto submitCommentDto) {
		this.submitCommentDto = submitCommentDto;
	}

	public void addComment(String questionId,String answerId,String comment,String commentedBy){
		submitCommentDto.setQuestionId(questionId);
		submitCommentDto.setAnswerId(answerId);
		submitCommentDto.setComment(comment);
		submitCommentDto.setCommentedBy(commentedBy);
		submitCommentDto.setCommentId(GenerateUUID.generateUUID());
		submitCommentDto.setDate(new Date().getTime());
		submitCommentDao.addComment(submitCommentDto);
	}

}
