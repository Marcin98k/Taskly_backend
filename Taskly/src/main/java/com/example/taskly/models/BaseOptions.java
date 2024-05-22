package com.example.taskly.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class BaseOptions {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	protected String name;
	protected String value;
	
	public BaseOptions() {
		super();
	}
	
	public BaseOptions(Long id) {
		this.id = id;
	}
	
	public BaseOptions(Long id, String name, String value) {
		this.id = id;
		this.name = name;
		this.value = value;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public String getValueForName(String name) {
	    if (this.name.equals(name)) {
	        return this.value;
	    } else {
	        return null;
	    }
	}
}
