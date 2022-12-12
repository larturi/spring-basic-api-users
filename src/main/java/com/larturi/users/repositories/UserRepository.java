package com.larturi.users.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.larturi.users.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	public Optional<User> findByEmail(String email);
	
	public Optional<User> findByEmailAndPassword(String email, String password);
	
	@Query("SELECT u.email from User u")
	public List<String> getEmails();


}