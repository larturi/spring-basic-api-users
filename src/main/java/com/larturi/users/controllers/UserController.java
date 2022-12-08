package com.larturi.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.larturi.users.models.User;
import com.larturi.users.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}
	
	@GetMapping(value="/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<User>(userService.getUserByUsername(username), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
	}
	
	@PutMapping(value="/{username}")
	public ResponseEntity<User> editUser(@RequestBody User user, @PathVariable("username") String username) {
		return new ResponseEntity<User>(userService.editUser(user, username), HttpStatus.OK);
	}
	
	@DeleteMapping(value="/{username}")
	public ResponseEntity<User> deleteUser(@PathVariable("username") String username) {
		return new ResponseEntity<User>(userService.deleteUser(username), HttpStatus.OK);
	}

}
