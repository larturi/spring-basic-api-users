package com.larturi.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.larturi.users.entities.User;
import com.larturi.users.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public List<User> getUsers() {
		return repository.findAll();
	}
	
	public User createUser(User user) {
		return repository.save(user);
	}
	
	public User updateUser(Integer id, User user) {
	     Optional<User> userToUpdate = repository.findById(id);
	     if(userToUpdate.isPresent()) {
	    	 return repository.save(user);
	     } else {
	    	 throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id $d doesn't exists", id));
	     }
	}
	
	public void deleteUser(Integer id) {
	     Optional<User> userToDelete = repository.findById(id);
	     if(userToDelete.isPresent()) {
	    	 repository.delete(userToDelete.get());
	     } else {
	    	 throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id $d doesn't exists", id));
	     }
	}
	
}
