package com.example.taskly.models;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="roles")
public class UserRoleModel implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
	private Long roleId;
	
	private String authority;
	
	public UserRoleModel() {
		super();
	}
	
	public UserRoleModel(String authority) {
		this.authority = authority;
	}
	
	public UserRoleModel(Long roleId, String authority) {
		this.roleId = roleId;
		this.authority = authority;
	}
	
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}
	
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	public Long getRoleId() {
		return this.roleId;
	}
	
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

}
