package com.liangfen.dao;

import java.util.List;

import com.liangfen.model.*;

public interface UserMapper {
	
	public User getUserById(int id);
	
	public List<User> getAllUsers();

}
