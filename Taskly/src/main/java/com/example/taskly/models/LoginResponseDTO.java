package com.example.taskly.models;

public class LoginResponseDTO {

	private ApplicationUser user;
	private String jwt;
	
	public LoginResponseDTO() {
		super();
	}
	
	public LoginResponseDTO(String jwt) {
		super();
		this.jwt = jwt;
	}
	public LoginResponseDTO(ApplicationUser user, String jwt) {
		this.user = user;
		this.jwt = jwt;
	}
	
	public ApplicationUser getUser() {
		return user;
	}
	
	public void setUser(ApplicationUser user) {
		this.user = user;
	}
	
	public String getJwt() {
		return jwt;
	}
	
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	@Override
	public String toString() {
		return "Login values(jwt): " + this.jwt;
	}
}
