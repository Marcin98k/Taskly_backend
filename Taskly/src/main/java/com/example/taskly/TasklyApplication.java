package com.example.taskly;

import java.util.Set;
import java.time.LocalDateTime;
import java.util.HashSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.taskly.models.ApplicationUser;
import com.example.taskly.models.RoleModel;
import com.example.taskly.repositories.AccountStatusRepository;
import com.example.taskly.repositories.RoleRepository;
import com.example.taskly.repositories.UserRepository;

@SpringBootApplication
public class TasklyApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasklyApplication.class, args);
	}
	
	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository,
			PasswordEncoder passwordEncoder, AccountStatusRepository accountStatusRepository) {
		return args -> {
			Set<RoleModel> auth = null;
			boolean shouldCreateAdmin = true;
			
			if(!roleRepository.findByAuthority("ADMIN").isPresent()) {
				RoleModel adminAuth = roleRepository.save(new RoleModel("ADMIN"));
				roleRepository.save(new RoleModel("USER"));
				auth = new HashSet<>();
				auth.add(adminAuth);
			} else {
				shouldCreateAdmin = false;
			}
			
			if(shouldCreateAdmin) {
				
				ApplicationUser admin = new ApplicationUser(1L, "admin","admin@taskly.pl", passwordEncoder.encode("password"),
						LocalDateTime.parse("2000-01-01T23:59:59"), true, auth, LocalDateTime.parse("2000-01-01T23:59:59"));
				userRepository.save(admin);
			}
		};
	}
}