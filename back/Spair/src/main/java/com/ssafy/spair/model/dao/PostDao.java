package com.ssafy.spair.model.dao;

import java.util.List;

import com.ssafy.spair.model.dto.Post;

public interface PostDao {
	
	int insert(Post post) ;
	
	int modify(Post post) ;
	
	int delete(int postId) ;
	
	List<Post> searchAll() ;
	
	List<Post> townSearchAll(double latitude, double longitude) ;
	
	Post search(int postId) ;

}