package com.liangfen.model;

public class Origin {

	private int id;
	private String city;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Origin [id = %d, city = %s]", id, city);
	}
}
