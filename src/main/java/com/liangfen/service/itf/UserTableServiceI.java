package com.liangfen.service.itf;

import java.util.List;

import com.liangfen.model.Gender;
import com.liangfen.model.User;

public interface UserTableServiceI {
	
	public User getUserById(int id);
	
	public List<User> getAllUsers();

}
