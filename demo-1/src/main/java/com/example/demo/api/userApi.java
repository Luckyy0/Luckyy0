package com.example.demo.api;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.data.User;
import com.example.demo.model.userDAO;

@RestController
@RequestMapping(path = "api/tacos", produces = "application/json")
@CrossOrigin(origins = "http;//tacocloud:8080")
public class userApi {
	private userDAO userRepo;
	@Autowired
	public userApi(userDAO userRepo) {
		this.userRepo = userRepo;
	}

	@GetMapping("/user")
	public Iterable<User> getListUser() {
		return userRepo.findAll();
	}
	@GetMapping("/user/ss/{search}")
	public Iterable<User> us(@PathVariable("search") String username){
		return userRepo.findByUsernameContaining(username);
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<User> userById(@PathVariable("id") int id) {
		Optional<User> optUser = userRepo.findById(id);
		if (optUser.isPresent()) {
			return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
			
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	@GetMapping("/user/finduser/{username}")
	public ResponseEntity<User> userByUsername(@PathVariable("username") String username) {
		Optional<User> optUser = userRepo.findByUsername(username);
		if (optUser.isPresent()) {
			return new ResponseEntity<>(optUser.get(), HttpStatus.OK);
			
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(path = "/user/add",consumes = "application/json")
//	@ResponseStatus(HttpStatus.CREATED)
	public User postUser(@RequestBody User user) {
		return userRepo.save(user);
		
		
	}


	@PostMapping(path = "/user/{id}", consumes = "application/json")
	public User patchUser(@PathVariable("id") int id, @RequestBody User patch) {
		System.out.print(id);
		User user = userRepo.findById(id).get();
		if (patch.getUsername() != null) {
			user.setUsername(patch.getUsername());
		}
		if (patch.getPassword() != null) {
			user.setPassword(patch.getPassword());
		}
		return userRepo.save(user);
	}

	@DeleteMapping(path = "/user/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteOrder(@PathVariable("id") int id) {
		try {
			userRepo.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
		}
	}
}
