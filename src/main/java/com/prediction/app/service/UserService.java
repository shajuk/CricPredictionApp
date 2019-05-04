package com.prediction.app.service;

import com.prediction.app.model.User;

/**
 * @author Shaju K
 *
 */

public interface UserService {

	public User findUserByUsername(String username);
	
	public void saveUser(User user);
}
