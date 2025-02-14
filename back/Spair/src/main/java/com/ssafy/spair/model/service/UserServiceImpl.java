package com.ssafy.spair.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.spair.model.dao.UserDao;
import com.ssafy.spair.model.dto.User;

@Service
public class UserServiceImpl implements UserService {
	
	private UserDao userDao ;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao ;
	}

	@Override
	public User search(int userId) {
		return userDao.search(userId);
	}

	@Override
	public int join(User user) {
		return userDao.join(user);
	}

	@Override
	public List<User> searchAll() {
		return userDao.searchAll();
	}

}
