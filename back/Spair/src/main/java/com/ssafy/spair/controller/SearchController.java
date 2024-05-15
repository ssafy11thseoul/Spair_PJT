package com.ssafy.spair.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.spair.model.dto.SearchFilter;
import com.ssafy.spair.model.service.PostService;
import com.ssafy.spair.model.service.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	private final SearchService searchService ;
	private final PostService postService ;
	
	@Autowired
	public SearchController(SearchService searchService, PostService postService) {
		this.searchService = searchService ;
		this.postService = postService ;
	}
	
	// 전체 게시글 통합 검색
	@GetMapping("{keyword}")
	public ResponseEntity<?> searchKeyword(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<>(searchService.searchKeyword(keyword), HttpStatus.OK) ;
	}
	
	// 전체 게시글 검색필터 (모집여부, 성별, 운동종류, 가격)
	@GetMapping
	public ResponseEntity<?> filterSearch(
	        @RequestParam(value = "status", required = false) Integer status,
	        @RequestParam(value = "gender", required = false) Integer gender,
	        @RequestParam(value = "exerciseType", required = false) String exerciseType,
	        @RequestParam(value = "minPrice", required = false) Integer minPrice,
	        @RequestParam(value = "maxPrice", required = false) Integer maxPrice) {

	    SearchFilter searchFilter = new SearchFilter(status, gender, exerciseType, minPrice, maxPrice);
	    
	    if (searchFilter.isEmpty()) {
	        // 검색 필터가 비어 있으면 모든 게시글 반환
	        return new ResponseEntity<>(postService.searchAll(), HttpStatus.OK);
	    } else {
	        // 비어 있지 않으면 필터링된 결과 반환
	        return new ResponseEntity<>(searchService.filterSearch(searchFilter), HttpStatus.OK);
	    }
	}
	
	
}
