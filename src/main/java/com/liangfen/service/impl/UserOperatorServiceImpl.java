package com.liangfen.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liangfen.dao.GenderMapper;
import com.liangfen.dao.UserMapper;
import com.liangfen.model.Gender;
import com.liangfen.model.User;
import com.liangfen.service.itf.UserTableServiceI;

@Service
public class UserOperatorServiceImpl implements UserTableServiceI{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		
		if(id <= 0) return null;
		
		return userMapper.getUserById(id);
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		ArrayList<User> list = (ArrayList<User>) userMapper.getAllUsers();
		
		return list;
	}
	
}
