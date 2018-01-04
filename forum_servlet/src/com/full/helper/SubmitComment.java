package com.full.helper;

import java.nio.channels.AsynchronousByteChannel;
import java.util.Date;

import com.full.dto.GenerateUUID;
import com.full.dto.SubmitCommentDto;
import com.full.model.SubmitCommentDao;

public class SubmitComment {
	public void addComment(String questionId,String answerId,String comment,String commentedBy){
		SubmitCommentDto 	submitCommentDto=new SubmitCommentDto();
		submitCommentDto.setQuestionId(questionId);
		submitCommentDto.setAnswerId(answerId);
		submitCommentDto.setComment(comment);
		submitCommentDto.setCommentedBy(commentedBy);
		submitCommentDto.setCommentId(GenerateUUID.generateUUID());
		submitCommentDto.setDate(new Date().getTime());
		SubmitCommentDao submitCommentDao=new SubmitCommentDao();
		submitCommentDao.addComment(submitCommentDto);
	}

}
