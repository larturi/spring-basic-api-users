package com.larturi.users.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@PostConstruct
	public void init() {
		for (int i = 0; i < 100; i++) {
			users.add(new User(
					faker.funnyName().name(), 
					faker.name().username(), 
					faker.dragonBall().character()));
			
		}
	}

}
