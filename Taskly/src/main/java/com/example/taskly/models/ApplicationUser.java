package com.example.taskly.models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="users")
public class ApplicationUser implements UserDetails {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique= true)
	private String email;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime whenJoin;
	
	@OneToOne(fetch=FetchType.EAGER)
	private AccountStatusModel accountStatus;
	
	private Boolean isActive;
	
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
			name="user_role_junction",
			joinColumns = {@JoinColumn(name="id")},
			inverseJoinColumns = {@JoinColumn(name="role_id")}
			)
	private Set<RoleModel> authorities;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastVisit; 
	
	public ApplicationUser() {
		super();
		this.authorities = new HashSet<RoleModel>();
	}
	
	public ApplicationUser(Long id, String username, String email, String password,
			LocalDateTime whenJoin, AccountStatusModel accountStatus, Boolean isActive,
			Set<RoleModel> authorities, LocalDateTime lastVisit) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.whenJoin = whenJoin;
		this.accountStatus = accountStatus;
		this.isActive = isActive;
		this.authorities = authorities;
		this.lastVisit = lastVisit;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}
	
	public void setAuthorities(Set<RoleModel> authorities) {
		this.authorities = authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
