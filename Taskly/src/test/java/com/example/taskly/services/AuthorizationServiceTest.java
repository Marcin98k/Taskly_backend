package com.example.taskly.services;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.taskly.repositories.RoleRepository;
import com.example.taskly.repositories.UserRepository;

class AuthorizationServiceTest {

	@InjectMocks
	private AuthorizationService authorizationService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	private AutoCloseable closeable;
	
	@BeforeEach
	void init() throws Exception{
		closeable = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach
	void cleanUp() throws Exception{
		closeable.close();
	}
	
	@Test
	void registerUserTest() {
		fail("Not yet implemented");
	}

}
