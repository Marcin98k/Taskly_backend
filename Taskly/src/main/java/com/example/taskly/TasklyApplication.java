package com.example.taskly;

import java.util.Set;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.UserRoleModel;
import com.example.taskly.repositories.RoleRepository;
import com.example.taskly.repositories.UserRepository;



@SpringBootApplication
public class TasklyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasklyApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if(roleRepository.findByAuthority("ADMIN").isPresent()) return;
			UserRoleModel adminAuth = roleRepository.save(new UserRoleModel("ADMIN"));
			roleRepository.save(new UserRoleModel("USER"));
			
			Set<UserRoleModel> auth = new HashSet<>();
			auth.add(adminAuth);
			
			ApplicationUser admin = new ApplicationUser(1L, "admin", passwordEncoder.encode("password"), auth);
			
			userRepository.save(admin);
		};
	}
}
