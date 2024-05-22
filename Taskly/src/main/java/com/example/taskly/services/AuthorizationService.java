package com.example.taskly.services;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.example.taskly.dto.LoginDTO;
import com.example.taskly.dto.RegistrationDTO;
import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.RoleModel;
import com.example.taskly.repositories.RoleRepository;
import com.example.taskly.repositories.UserRepository;

import java.util.Set;
import java.time.LocalDateTime;
import java.util.HashSet;


@Service
@Transactional
public class AuthorizationService{

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	public AuthorizationService(UserRepository userRepository, RoleRepository roleRepository, 
			AuthenticationManager authenticationManager, TokenService tokenService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}
	
	public String signUp(RegistrationDTO registrationDTO) {
		
		String encodedPassword = encodeThePassword(registrationDTO.getPassword());
		Set<RoleModel> role = setRole();
		LocalDateTime currently = LocalDateTime.now();
		
		saveUser(registrationDTO, encodedPassword, currently, role);
		
		return attemptTokenGeneration(registrationDTO);
	}
	
	public String signIn(LoginDTO loginDTO) {
		return attemptTokenGeneration(loginDTO);
	}
	
	private String encodeThePassword(String password) {
		PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return passwordEncoder.encode(password);
	}
	
	private Set<RoleModel> setRole() {
		RoleModel userRoleModel = roleRepository.findByAuthority("USER").get();
		Set<RoleModel> role = new HashSet<>();
		role.add(userRoleModel);
		return role;
	}
	
	private ApplicationUser saveUser(RegistrationDTO registrationDTO, String encodedPassword,
			LocalDateTime currently, Set<RoleModel> role) {
		try {
			return userRepository.save(new ApplicationUser(0L,
				registrationDTO.getUsername(), registrationDTO.getEmail(),
				encodedPassword, currently, false, role, currently));
		}catch(DataAccessException e) {
			throw new DataAccessException("Failed to save user") {};
		}
	}
	
	private String attemptTokenGeneration(LoginDTO authDto) {
		try {
			return generateToken(authDto);
		} catch(AuthenticationException e) {
			throw new ResponseStatusException(
					HttpStatus.UNAUTHORIZED, "User not found", e);
		}
	}
	
	private String generateToken(LoginDTO authDto) {
		System.out.print(authDto.toString());
		try {
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authDto.getEmail(), authDto.getPassword()));
			System.out.println("MarrCin");
			return tokenService.generateJwt(authentication);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			System.out.println("Authentication failed: " + e.getMessage());
			return null;
		}
	}
}
