package com.larturi.users.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.larturi.users.models.User;

import jakarta.annotation.PostConstruct;

import com.github.javafaker.Faker;

@Service
public class UserService {
	
	@Autowired
	private Faker faker;
	
	private List<User> users = new ArrayList<>();
	
	public List<User> getUsers(String startWith) {
		if(startWith != null) {
			return users.stream().filter(u -> u.getUsername().startsWith(startWith))
					.collect(Collectors.toList());
		} else {
			return users;
		}
	}
	
	public User getUserByUsername(String username) {
		return users.stream().filter(u -> u.getUsername().equals(username)).findAny()
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found", username)));
	}
	
	public User createUser(User user) {
		if(users.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()))) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User %s already exists", user.getUsername()));
		}
		
		users.add(user);
		return user;
	}
	
	public User editUser(User user, String username) {
		User userToBeUpdated = getUserByUsername(username);
		userToBeUpdated.setNick(user.getNick());
		userToBeUpdated.setPassword(user.getPassword());
		return userToBeUpdated;
	}
	
	public User deleteUser(String username) {
		User userToBeDeleted = getUserByUsername(username);
		users.remove(userToBeDeleted);
		return userToBeDeleted;
	}

	@PostConstruct
	public void init() {
		for (int i = 0; i < 100; i++) {
			users.add(new User(
					faker.name().username(),
					faker.name().name(), 
					faker.dragonBall().character()
					));
			
		}
	}

}
