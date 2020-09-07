package com.test.acorn.dto;

public class FriendsDto {
	private int num;
	private String name;
	private String loc;
	
	public FriendsDto() {}

	public FriendsDto(int num, String name, String loc) {
		super();
		this.num = num;
		this.name = name;
		this.loc = loc;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}
	
	
}
