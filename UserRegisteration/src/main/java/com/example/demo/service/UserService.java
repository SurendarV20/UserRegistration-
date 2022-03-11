package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;

public interface UserService {
	User getUserByName(String uname);

	User saveUserInDb(User user);
	
	List<User> getAllUsersFromDb();
	
	User getUserById(long id);
}
