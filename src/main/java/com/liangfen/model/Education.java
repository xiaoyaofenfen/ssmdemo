package com.liangfen.model;

public class Education {

	private int id;
	private String level;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Eduction [id = %d, level = %s]", id, level);
	}
	
	
	
}
