package com.example.taskly.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.LoginResponseDTO;
import com.example.taskly.models.UserRoleModel;
import com.example.taskly.repositories.RoleRepository;
import com.example.taskly.repositories.UserRepository;

import java.util.Set;
import java.util.HashSet;


@Service
@Transactional
public class AuthorizationService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final TokenService tokenService;
	
	@Autowired
	public AuthorizationService(UserRepository userRepository, RoleRepository roleRepository,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}
	
	
	public ApplicationUser registerUser(String username, String password) {
		
//		passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(password);
		
		UserRoleModel userRoleModel = roleRepository.findByAuthority("USER").get();
		
		Set<UserRoleModel> role = new HashSet<>();
		role.add(userRoleModel);
		
		return userRepository.save(new ApplicationUser(0L, username, encodedPassword, role));
	}
	
	public LoginResponseDTO loginUser(String username, String password) {
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(username, password));
			
			String token = tokenService.generateJwt(authentication);
			
			return new LoginResponseDTO(userRepository.findByUsername(username).get(), token);
		} catch(AuthenticationException e) {
			return new LoginResponseDTO(null, "");
		}
	}
}
