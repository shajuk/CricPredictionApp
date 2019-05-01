package com.prediction.app.service;

import com.prediction.app.model.User;

public interface UserService {

	public User findUserByUsername(String username);
	
	public void saveUser(User user);
}
