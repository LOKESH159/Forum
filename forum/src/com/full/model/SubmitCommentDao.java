package com.full.model;

import org.springframework.stereotype.Repository;

import com.full.dto.SubmitCommentDto;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
@Repository
public class SubmitCommentDao {
	public void addComment(SubmitCommentDto submitCommentDto){
		DatastoreService dataStore =DatastoreServiceFactory.getDatastoreService();
		Entity entity=new Entity("COMMENTS", submitCommentDto.getCommentId());
		entity.setProperty("QUESTION_ID", submitCommentDto.getQuestionId());
		entity.setProperty("ANSWER_ID",submitCommentDto.getAnswerId());
		entity.setProperty("COMMENT",submitCommentDto.getComment());
		entity.setProperty("COMMENT_ID",submitCommentDto.getCommentId());
		entity.setProperty("COMMENTED_By",submitCommentDto.getCommentedBy());
		entity.setProperty("COMMENT_date",submitCommentDto.getDate());
		System.out.println("IAM ADDING COMMENT");
	    dataStore.put(entity);	
	}

}
