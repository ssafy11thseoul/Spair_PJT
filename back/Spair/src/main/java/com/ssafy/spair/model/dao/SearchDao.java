package com.ssafy.spair.model.dao;

import java.util.List;

import com.ssafy.spair.model.dto.Post;
import com.ssafy.spair.model.dto.SearchFilter;

public interface SearchDao {
	
	List<Post> searchKeyword(String keyword) ;
	
	List<Post> filterSearch(SearchFilter searchFilter) ;

}
