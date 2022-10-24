package com.corsojava.library.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corsojava.library.exceptions.NotValidPasswordException;
import com.corsojava.library.model.User;
import com.corsojava.library.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/createuser")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
		try {
			return new ResponseEntity<Object>(userService.create(user), HttpStatus.CREATED);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/updatepassword")
	public ResponseEntity<Object> updatePassword(@RequestBody UpdatePasswordParam updatePasswordParam) {
		try {
			Optional<User> optionalUser = userService.findById(updatePasswordParam.getUserId());
			if(optionalUser.isPresent()) {
				return new ResponseEntity<Object>(userService.updatePassword(optionalUser.get(),updatePasswordParam.getNewPassword()), HttpStatus.OK);
			} else {
				return new ResponseEntity<Object>("unable to find user: " + updatePasswordParam.getUserId(), HttpStatus.FORBIDDEN);
			}
		} catch(NotValidPasswordException nvpe) {
			return new ResponseEntity<Object>(nvpe.getMessage(), HttpStatus.FORBIDDEN);
		} catch(Exception e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
