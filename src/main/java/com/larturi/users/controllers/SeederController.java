package com.larturi.users.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larturi.users.entities.Role;
import com.larturi.users.entities.User;
import com.larturi.users.services.RoleService;
import com.larturi.users.services.UserService;

@RestController
@RequestMapping("/seeder")
public class SeederController {
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Void> seedRoles() {
		
		// Roles
		Role roleAdmin = new Role();
		roleAdmin.setId(1);
		roleAdmin.setName("ADMIN");
		roleService.createRole(roleAdmin);
		
		Role roleUser = new Role();
		roleUser.setId(2);
		roleUser.setName("USER");
		roleService.createRole(roleUser);
		
		// Users
		for (Integer i = 0; i <=100; i++) {
			User user = new User();
			user.setId(i);
			user.setEmail("user" + i + "@correo.com");
			user.setPassword("12345678");
			
			if(i==1) {
				user.setRole(roleAdmin);

			} else {
				user.setRole(roleUser);
			}
			
			userService.createUser(user);
		}
		
		return new ResponseEntity<Void>(HttpStatus.OK);

	}

}
