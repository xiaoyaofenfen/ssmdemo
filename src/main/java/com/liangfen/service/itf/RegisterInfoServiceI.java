package com.liangfen.service.itf;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liangfen.model.RegisterInfo;

public interface RegisterInfoServiceI extends GenderServiceI{
	
	// query & find
	public List<RegisterInfo> qeuryAllRegisterInfo();
	public RegisterInfo queryRegisterInfoById(int id);
	
	public RegisterInfo queryRegisterInfoByName(String theName);
	public int getCountByName(String theName);	
	
	// insert
	public int insertRegisterInfo(RegisterInfo theRegisterInfo);

}
