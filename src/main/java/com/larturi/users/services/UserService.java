package com.larturi.users.services;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<User> getUsers() {
		return users;
	}
	
	public User getUserByUsername(String username) {
		return users.stream().filter(u -> u.getUsername().equals(username)).findAny()
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User %s not found", username)));
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
