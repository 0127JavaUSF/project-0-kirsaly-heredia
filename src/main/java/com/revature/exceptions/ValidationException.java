package com.revature.exceptions;

import java.util.ArrayList;
import java.util.List;

	/**
	 * Class defines a generic ValidationException that can collect validation errors and be used to print them to the user.
	 * @author Mitch
	 *
	 */	
	
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;

	public class InvalidProperty {
		final String property;
		final String message;
		
		private InvalidProperty(String property, String message) {
			super();
			this.property = property;
			this.message = message;
		}		
	}
	
	private List<InvalidProperty> errors = new ArrayList<>();
	
	public void addError(String property, String message) {
		errors.add(new InvalidProperty(property, message));
	}
	
	public void printErrors() {
		errors.forEach(e -> System.out.printf("Invalid input for '%s': %s%n", e.property, e.message));
	}
	
	public boolean hasErrors() {
		return errors.size() > 0;
	}
	
	
}
