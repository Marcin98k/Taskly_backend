package com.example.taskly.services;

import static org.junit.jupiter.api.Assertions.*;
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
import org.modelmapper.ModelMapper;

import com.example.taskly.dto.UserDto;
import com.example.taskly.exceptions.ResourceNotFoundException;
import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.RoleModel;
import com.example.taskly.repositories.RoleRepository;
import com.example.taskly.repositories.UserRepository;

class UserServiceTest {

	@InjectMocks
	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ModelMapper modelMapper;
	
	@Mock
	private RoleRepository roleRepository;
	
	private AutoCloseable closeable;
	
	@BeforeEach
	void init() throws Exception{
		closeable = MockitoAnnotations.openMocks(this);
	}
	
	@AfterEach()
	void cleaUp() throws Exception{
		closeable.close();
	}
	
	@Test
	void getUserByIdTest() {
		RoleModel adminAuth = null;
		Set<RoleModel> model = new HashSet<RoleModel>();
		model.add(adminAuth);
		
		ApplicationUser user = new ApplicationUser();
		user.setId(1L);
		user.setPassword("pass");
		user.setUsername("username");
		user.setAuthorities(model);
		
		System.out.println(user);
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		
		UserDto userDto = userService.getUserById(1L);
		System.out.println(userDto);
		UserDto temp = modelMapper.map(user, UserDto.class);
		
		System.out.println(temp);
		
//		assertNotNull(userDto);
//		assertEquals(user.getId(), userDto.getId());
	}

}
