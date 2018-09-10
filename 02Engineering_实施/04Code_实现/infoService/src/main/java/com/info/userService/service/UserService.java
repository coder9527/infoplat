package com.info.userService.service;

import java.util.List;

import com.info.userService.model.User;

public interface UserService {
	
	public List<User> getUsers();
	
	public User getUserById(String userId);
	
	public void saveUser(User user);

}
