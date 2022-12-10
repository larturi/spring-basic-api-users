package com.larturi.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.larturi.users.entities.Role;
import com.larturi.users.repositories.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository repository;
	
	public List<Role> getRoles() {
		return repository.findAll();
	}
	
	public Role createRole(Role role) {
		return repository.save(role);
	}
	
	public Role updateRole(Integer id, Role role) {
	     Optional<Role> roleToUpdate = repository.findById(id);
	     if(roleToUpdate.isPresent()) {
	    	 return repository.save(role);
	     } else {
	    	 throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id $d doesn't exists", id));
	     }
	}
	
	public void deleteRole(Integer id) {
	     Optional<Role> roleToDelete = repository.findById(id);
	     if(roleToDelete.isPresent()) {
	    	 repository.delete(roleToDelete.get());
	     } else {
	    	 throw new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Role id $d doesn't exists", id));
	     }
	}

}
