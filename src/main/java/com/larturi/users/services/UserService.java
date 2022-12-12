package com.larturi.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.larturi.users.entities.User;
import com.larturi.users.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public Page<User> getUsers(int page, int size) {
		return repository.findAll(PageRequest.of(page, size));
	}
	
	public List<String> getEmails() {
		return repository.getEmails();
	}
	
	public User getUserById(Integer id) {
		return repository.findById(id)
				   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with ID %d not found", id)));
	}
	
	public User getUserByEmail(String email) {
		return repository.findByEmail(email)
				   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User with EMAIL %d not found", email)));
	}
	
	public User getUserByEmailAndPassword(String email, String password) {
		return repository.findByEmailAndPassword(email, password)
				   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
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
