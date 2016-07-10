package com.liangfen.model;

public class User {
	
	public int id;
	public String userName;
	public String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("User [id = %d, userName = %s, password = %s]", id, userName, password);
	}

}
