package com.ironhack.MusicLibrary;

import com.ironhack.MusicLibrary.dtos.UserDTO;
import com.ironhack.MusicLibrary.model.Role;
import com.ironhack.MusicLibrary.model.User;
import com.ironhack.MusicLibrary.service.RoleService;
import com.ironhack.MusicLibrary.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class MusicLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusicLibraryApplication.class, args);
	}

	// To centralize configuration and allow to use it through dependency injection in our application
	@Bean
	PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	// To have some data to start with, it's executed automatically
	@Bean
	CommandLineRunner run(UserService userService, RoleService roleService) {
		return args -> {
//			roleService.saveRole(new Role(null, "ROLE_USER"));
//			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
//
//			userService.createUser(new UserDTO("John Doe", "john", "1234"));
//			userService.createUser(new UserDTO("James Smith", "james", "1234"));
//			userService.createUser(new UserDTO("Jane Carry", "jane", "1234"));
//			userService.createUser(new UserDTO("Chris Anderson", "chris", "1234"));
//
//			roleService.addRoleToUser("john", "ROLE_USER");
//			roleService.addRoleToUser("james", "ROLE_ADMIN");
//			roleService.addRoleToUser("jane", "ROLE_USER");
//			roleService.addRoleToUser("chris", "ROLE_ADMIN");
//			roleService.addRoleToUser("chris", "ROLE_USER");
		};
	}

}
