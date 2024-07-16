package com.tenco.tboard.repository.interfaces;

import java.util.List;

import com.tenco.tboard.model.User;

public interface UserRepository {

	int addUser(User user);
	void deleteUser(int id);
	User getUserByUsername(String username);
	User getUserByUsernameAndPassword(String username,String password);
	List<User> getAllUsers();
}
