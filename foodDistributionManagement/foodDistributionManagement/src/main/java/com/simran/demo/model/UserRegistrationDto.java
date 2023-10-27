package com.simran.demo.model;

import lombok.Data;

@Data
public class UserRegistrationDto {
	private String name;
	private String email;
	private String password;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}
}
