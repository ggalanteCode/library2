package com.corsojava.library.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.library.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@PostMapping("/createuser")
	public ResponseEntity<Object> createUser() {
		return null;
	}

}