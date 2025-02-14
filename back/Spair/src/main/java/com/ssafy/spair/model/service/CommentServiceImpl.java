package com.ssafy.spair.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.spair.model.dao.CommentDao;
import com.ssafy.spair.model.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {
	
	private final CommentDao commentDao ;
	
	@Autowired
	public CommentServiceImpl(CommentDao commentDao) {
		this.commentDao = commentDao ;
	}

	@Override
	public int insert(Comment comment) {
		return commentDao.insert(comment);
	}

	@Override
	public int modify(Comment comment) {
		return commentDao.modify(comment);
	}

	@Override
	public int delete(int commentId) {
		return commentDao.delete(commentId);
	}

	@Override
	public List<Comment> search(int postId) {
		return commentDao.search(postId);
	}

}
