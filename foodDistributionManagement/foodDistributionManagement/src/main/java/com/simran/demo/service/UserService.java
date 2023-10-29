package com.simran.demo.service;

import com.simran.demo.model.User;
import com.simran.demo.model.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;




public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}