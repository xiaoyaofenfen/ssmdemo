package com.liangfen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liangfen.dao.GenderMapper;
import com.liangfen.dao.RegisterInfoMapper;
import com.liangfen.model.Gender;
import com.liangfen.model.RegisterInfo;
import com.liangfen.service.itf.RegisterInfoServiceI;

@Service
public class RegisterInfoServiceImpl implements RegisterInfoServiceI{
	//
	// gender
	//
	@Autowired
	private GenderMapper genderMapper;
	
	@Override
	public List<Gender> queryAllGender() {
		// TODO Auto-generated method stub
		return genderMapper.queryAllGender();
	}

	@Override
	public Gender queryGenderById(int id) {
		// TODO Auto-generated method stub
		if(id <= 0) return null;
		return genderMapper.queryGenderById(id);
	}

	// register info
	@Autowired
	private RegisterInfoMapper registerInfoMapper;
	
	@Override
	public List<RegisterInfo> qeuryAllRegisterInfo() {
		// TODO Auto-generated method stub
		return registerInfoMapper.qeuryAllRegisterInfo();
	}

	@Override
	public RegisterInfo queryRegisterInfoById(int id) {
		// TODO Auto-generated method stub
		if(id <= 0) return null;
		return registerInfoMapper.queryRegisterInfoById(id);
	}

	private boolean isRegisterInfoValided(RegisterInfo registerInfo)
	{
		if(registerInfo == null) return false;
		
		if(registerInfo.getName() == null || registerInfo.getName().equals("")) return false;
		
		if(registerInfo.getEducationEntity().getId() <= 0 ||
				registerInfo.getGenderEntity().getId() <= 0 ||
				registerInfo.getOriginEntity().getId() <= 0 ||
				registerInfo.getUserEntity().getId() <= 0)
		{
			return false;
		}
		
		return true;
	}
	
	private boolean isStringEmpty(String string) {
		return (string == null || string.trim().equals(""));
	}
	
	@Override
	public int insertRegisterInfo(RegisterInfo theRegisterInfo) {
		// TODO Auto-generated method stub
		if(!isRegisterInfoValided(theRegisterInfo)) return -1;
		if(registerInfoMapper.getCountByName(theRegisterInfo.getName()) > 0) return -1;
		int ret = registerInfoMapper.insertRegisterInfo(theRegisterInfo);
		if(ret > 0)
		{
			return registerInfoMapper.getLastInsertId();
		}
		return -1;
	}

	@Override
	public RegisterInfo queryRegisterInfoByName(String theName) {
		// TODO Auto-generated method stub
		if(isStringEmpty(theName)) return null;
		return registerInfoMapper.queryRegisterInfoByName(theName);
	}

	@Override
	public int getCountByName(String theName) {
		// TODO Auto-generated method stub
		if(isStringEmpty(theName)) return -1;
		return registerInfoMapper.getCountByName(theName);
	}
}
