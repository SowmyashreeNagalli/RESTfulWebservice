package com.webeservice.example.restfulServices.dtos;

public class UserMsDto {

	private Long userId;
	private String username;
	private String emailaddress;
	private String rolename;

	public UserMsDto() {

	}

	public UserMsDto(Long userId, String username, String emailaddress, String rolename) {
		super();
		this.userId = userId;
		this.username = username;
		this.emailaddress = emailaddress;
		this.rolename = rolename;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailaddress() {
		return emailaddress;
	}

	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
