package com.liangfen.model;

public class RegisterInfo {

	private int id;
	private String name;
	private Gender genderEntity;
	private Education educationEntity;
	private Origin originEntity;
	private User userEntity;
	
	
	
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Gender getGenderEntity() {
		return genderEntity;
	}



	public void setGenderEntity(Gender genderEntity) {
		this.genderEntity = genderEntity;
	}





	public Education getEducationEntity() {
		return educationEntity;
	}



	public void setEducationEntity(Education educationEntity) {
		this.educationEntity = educationEntity;
	}



	public Origin getOriginEntity() {
		return originEntity;
	}



	public void setOriginEntity(Origin originEntity) {
		this.originEntity = originEntity;
	}




	public User getUserEntity() {
		return userEntity;
	}



	public void setUserEntity(User userEntity) {
		this.userEntity = userEntity;
	}



	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("RigisterInfo [id = %d, name = %s, origin = %s, gender = %s, education = %s, user = %s]",
				id, name, originEntity, genderEntity, educationEntity, userEntity);
	}
	
}
