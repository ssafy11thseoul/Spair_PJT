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
import com.ssafy.spair.model.service.SearchService;

@RestController
@RequestMapping("/api/search")
public class SearchController {

	private final SearchService searchService ;
	
	@Autowired
	public SearchController(SearchService searchService) {
		this.searchService = searchService ;
	}
	
	// 전체 게시글 통합 검색
	@GetMapping("{keyword}")
	public ResponseEntity<?> searchKeyword(@PathVariable("keyword") String keyword) {
		return new ResponseEntity<>(searchService.searchKeyword(keyword), HttpStatus.OK) ;
	}
	
	// 전체 게시글 검색필터 (모집여부, 성별, 운동 종류, 가격)
	@GetMapping(params = {"status", "gender", "exerciseType", "minPrice", "maxPrice"})
	public ResponseEntity<?> searchKeyword(@RequestParam("status") int status, 
			@RequestParam("gender") int gender, @RequestParam("exerciseType") String exerciseType,
			@RequestParam("minPrice") int minPrice, @RequestParam("maxPrice") int maxPrice) {
		SearchFilter searchFilter = new SearchFilter(status, gender, exerciseType, minPrice, maxPrice) ;
		return new ResponseEntity<>(searchService.filterSearch(searchFilter), HttpStatus.OK) ;
	}
	
}
