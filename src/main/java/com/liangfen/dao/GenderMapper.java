package com.liangfen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liangfen.model.Gender;

public interface GenderMapper {

	// query & find
	public List<Gender> queryAllGender();
	public Gender queryGenderById(@Param("id") int id);
}
