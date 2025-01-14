package com.in28minutes.rest.webservices.restful_web_services.User;

import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
// import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
public class UserResource {
	private UserDaoService service;
	public UserResource(UserDaoService service) {
		this.service=service;
	}
	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
	
	// EntityModel
	// WebMvc Link builder

	
	// @GetMapping("/users/{id}")
	// public User retrieveUserById(@PathVariable int id) {
	// 	User user= service.findUserById(id);
	// 	if(user==null) {
	// 		throw new UserNotFoundException("id "+id);
	// 	}
	// 	return user;
	// }

	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUserById(@PathVariable int id) {
		User user= service.findUserById(id);
		if(user==null) {
			throw new UserNotFoundException("id "+id);
		}
		EntityModel<User>entityModel=EntityModel.of(user);
		WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUserById(@PathVariable int id) {
		service.deleteUserById(id);
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser=service.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/id")
				.buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
