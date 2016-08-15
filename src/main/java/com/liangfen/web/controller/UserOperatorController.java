package com.liangfen.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import com.liangfen.model.Gender;
import com.liangfen.model.RegisterInfo;
import com.liangfen.model.User;
import com.liangfen.service.itf.RegisterInfoServiceI;
import com.liangfen.service.itf.UserTableServiceI;

@Controller
@RequestMapping(value="/")
public class UserOperatorController {
	
	@Autowired
	private UserTableServiceI userOperatorServiceImpl;
	
	@Autowired
	private RegisterInfoServiceI registerInfoImpl;
	
	@RequestMapping(value="/index")
	public String showAllUser(Model model)
	{
		ArrayList<User> list = (ArrayList<User>) userOperatorServiceImpl.getAllUsers();
		System.out.println("list: " + list);
		if(list != null)
		{
			model.addAttribute("users", list);
		}
		
		String basename = "com.liangfen.resource.Strings";
		Locale cn = Locale.CHINA;//中文
		ResourceBundle myResourcesCN = ResourceBundle.getBundle(basename,cn);
		System.out.println(myResourcesCN.getString("register.username"));
		
		return "index";
	}
	
	@RequestMapping(value="/websocketpage")
	public String showWebSocket()
	{
		return "websocket";
	}
	
	@RequestMapping(value="/simpleTag")
	public String showSimpleTag(Model model)
	{
		return "simpleTag";
	}
	
	@RequestMapping(value="/RegisterInfo")
	public String reigsterInfoTest(Model model)
	{
		// queryAllGender
		List<Gender> list = registerInfoImpl.queryAllGender();
		if(list != null)
		{
			String string = "";
			for (Gender gender : list) {
				string += gender + "\n";
			}
			
			model.addAttribute("queryAllGender", string);
		}
		
		// qeuryAllReigisterInfo
		List<RegisterInfo> registerInfoList = registerInfoImpl.qeuryAllRegisterInfo();
		if(registerInfoList != null)
		{
			String string = "";
			for (RegisterInfo registerInfo : registerInfoList) {
				string += registerInfo + "\n";
			}
			model.addAttribute("qeuryAllReigisterInfo", string);
		}
		
		// queryGenderById
		RegisterInfo r = registerInfoImpl.queryRegisterInfoById(2);
		if(r != null)
		{
			model.addAttribute("queryRegisterInfoById", r);
		}
	
		// insertRegisterInfo
		r.setName("kkkkkkkk1354982");
		int id = registerInfoImpl.insertRegisterInfo(r);
		model.addAttribute("insertRegisterInfo", id);
		
		// queryRegisterInfoByName
		r = registerInfoImpl.queryRegisterInfoByName("lf");
		model.addAttribute("queryRegisterInfoByName", r);
		
		// getCountByName
		id = registerInfoImpl.getCountByName("lf");
		model.addAttribute("getCountByName", id);
		
		return "register_info";
	}

}
