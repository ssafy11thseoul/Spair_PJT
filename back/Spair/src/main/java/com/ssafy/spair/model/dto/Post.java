package com.ssafy.spair.model.dto;

import java.time.LocalDateTime;

public class Post {
	
	private int postId ;
	private int userId ;
	private String title ;
	private String content ;
	private int status ;
	private LocalDateTime createdDate ;
	private LocalDateTime modifiedDate ;
	private int centerId ;
	private String exerciseType ;
	private int price ;
	private int gender ;
	private String recruitmentNum ;
	
	private User user ;
	private SportsCenter sportsCenter ;
	
	public Post(){
		
	}

	// 등록
	public Post(int userId, String title, String content, int status, LocalDateTime createdDate,
			String exerciseType, int price, int gender, String recruitmentNum) {
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.createdDate = createdDate;
		this.exerciseType = exerciseType;
		this.price = price;
		this.gender = gender;
		this.recruitmentNum = recruitmentNum;
	}
	
	// 수정
	public Post(int postId, String title, String content, int status, LocalDateTime modifiedDate, int price, int gender, String recruitmentNum) {
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.status = status;
		this.modifiedDate = modifiedDate;
		this.price = price;
		this.gender = gender;
		this.recruitmentNum = recruitmentNum;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public SportsCenter getSportsCenter() {
		return sportsCenter;
	}

	public void setSportsCenter(SportsCenter sportsCenter) {
		this.sportsCenter = sportsCenter;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public int getCenterId() {
		return centerId;
	}

	public void setCenterId(int centerId) {
		this.centerId = centerId;
	}

	public String getExerciseType() {
		return exerciseType;
	}

	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getRecruitmentNum() {
		return recruitmentNum;
	}

	public void setRecruitmentNum(String recruitmentNum) {
		this.recruitmentNum = recruitmentNum;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", userId=" + userId + ", title=" + title + ", content=" + content
				+ ", status=" + status + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", centerId=" + centerId + ", exerciseType=" + exerciseType + ", price=" + price + ", gender="
				+ gender + ", recruitmentNum=" + recruitmentNum + "]";
	}
	
}
