package com.example.taskly.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.taskly.dto.UserDto;
import com.example.taskly.exceptions.ResourceNotFoundException;
import com.example.taskly.models.ApplicationUser;
import com.example.taskly.repositories.UserRepository;
import com.example.taskly.responses.ApiResponseDto;

@Service
public class UserService implements UserDetailsService {

	private final UserRepository userRepository;
	private final ModelMapper modelMapper;

	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User no exist"));
	}
	
	public List<ApplicationUser> getAllUsers() {
		return userRepository.findAll();
	}

	public UserDto getUserById(Long id) {
		ApplicationUser user = findUserById(id);
		return modelMapper.map(user, UserDto.class);
	}

	public ResponseEntity<ApiResponseDto> deleteUser(Long id) {
		ApplicationUser user = findUserById(id);
		userRepository.delete(user);
		ApiResponseDto respone = new ApiResponseDto(true, "User deleted successfully");
		return ResponseEntity.ok(respone);
	}

	public ResponseEntity<ApiResponseDto> partllyUpdateUser(Long id, Map<String, Object> fields) {
		ApplicationUser user = findUserById(id);
	
		fields.forEach((key, value) -> {
			Field field = ReflectionUtils.findField(ApplicationUser.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, user, value);
		});
		userRepository.save(user);
		ApiResponseDto response = new ApiResponseDto(true, "User updated successfully");
		return ResponseEntity.ok(response);
	}
	
	private ApplicationUser findUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User with this id: (" + id + ") not exist"));
	}
}
