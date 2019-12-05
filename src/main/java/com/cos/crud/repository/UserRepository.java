package com.cos.crud.repository;

import com.cos.crud.model.User;

public interface UserRepository {

	void save(User user);
	User findByUsernameAndPassword(String username, String password);
	void update(String profile, int id);
}
