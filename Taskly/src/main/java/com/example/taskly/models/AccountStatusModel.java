package com.example.taskly.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_status")
@AllArgsConstructor
@NoArgsConstructor
public class AccountStatusModel extends BaseOptions{
	
	private String statusDescription;
	
	public AccountStatusModel(Long id, String name, String value, String statusDescription) {
		super(id, name, value);
		this.statusDescription = statusDescription;
	}
	
	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
}
