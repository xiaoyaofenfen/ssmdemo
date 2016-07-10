package com.liangfen.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import com.liangfen.model.RegisterInfo;

public interface RegisterInfoMapper {

	// query & find
	public List<RegisterInfo> qeuryAllRegisterInfo();
	public RegisterInfo queryRegisterInfoById(@Param("id") int id);
	public RegisterInfo queryRegisterInfoByName(@Param("theName") String theName);
	public int getCountByName(@Param("theName") String theName);
	
	// insert
	public int insertRegisterInfo(@Param("theRegisterInfo") RegisterInfo theRegisterInfo);
	public int getLastInsertId();
}
