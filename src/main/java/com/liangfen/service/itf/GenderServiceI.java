package com.liangfen.service.itf;

import java.util.List;

import com.liangfen.model.Gender;

public interface GenderServiceI {

	public List<Gender> queryAllGender();
	public Gender queryGenderById(int id);
	
}
