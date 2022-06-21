package com.example.demo.model;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.data.User;

public interface userDAO extends CrudRepository<User, Integer>{
	Optional<User> findByUsername(String username);
	
	List<User> findByUsernameContaining(String title);
}
