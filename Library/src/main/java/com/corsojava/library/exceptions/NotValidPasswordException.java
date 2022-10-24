package com.corsojava.library.exceptions;

import com.corsojava.library.model.User;

public class NotValidPasswordException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user;

	public NotValidPasswordException(User user) {
		super("NOT VALID PASSWORD. User: " + user.getFirstName() + " " + user.getLastName());
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
