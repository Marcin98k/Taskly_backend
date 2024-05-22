package com.example.taskly.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.taskly.dto.LoginDTO;
import com.example.taskly.dto.RegistrationDTO;
import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.RoleModel;
import com.example.taskly.repositories.RoleRepository;
import com.example.taskly.repositories.UserRepository;

class AuthorizationServiceTest {
	
	private static final String EMAIL = "test@example.com";
	private static final String PASSWORD = "password";
	private static final String TOKEN = "token";
	private static final String ROLE = "USER";
	
	private RegistrationDTO registrationDTO;
	private ApplicationUser applicationUser;
	private Set<RoleModel> role;
	
	@InjectMocks
	private AuthorizationService authorizationService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	@Mock
	private TokenService tokenService;
	
	private AutoCloseable closeable;
	
	@BeforeEach
	void init() throws Exception{
		closeable = MockitoAnnotations.openMocks(this);
		
		registrationDTO = new RegistrationDTO();
		registrationDTO.setEmail(EMAIL);
		registrationDTO.setPassword(PASSWORD);
		
		applicationUser = new ApplicationUser();
		applicationUser.setEmail(EMAIL);
		applicationUser.setPassword(PASSWORD);
		
		role = new HashSet<>();
		role.add(new RoleModel("USER"));
	}
	
	@AfterEach
	void cleanUp() throws Exception{
		closeable.close();
	}
	
	@Test
	public void correctRegistration() {
		
//		given
//		when
		when(userRepository.save(applicationUser)).thenReturn(applicationUser);
		when(roleRepository.findByAuthority(ROLE)).thenReturn(Optional.of(role.iterator().next()));
		when(tokenService.generateJwt(any())).thenReturn(TOKEN);
		
		String result = authorizationService.signUp(registrationDTO);
		
//		then
		assertEquals(TOKEN, result);
	}
	
	@Test
	public void correctLogin() {
		
//		given
		LoginDTO loginDto = new LoginDTO();
		loginDto.setEmail(EMAIL);
		loginDto.setPassword(PASSWORD);
		
//		when
		Authentication auth = new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword());
		
		when(authenticationManager.authenticate(any())).thenReturn(auth);
		when(tokenService.generateJwt(any())).thenReturn(TOKEN);
		
		String result = authorizationService.signIn(loginDto);
		
//		then
		assertEquals(TOKEN, result);
	}
}
