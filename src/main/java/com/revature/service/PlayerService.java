package com.revature.service;

import com.revature.daos.PlayerDao;
import com.revature.exceptions.ValidationException;
import com.revature.models.Player;



public class PlayerService implements IPlayerService{

	@Override
	public Player createPlayer(Player player) throws ValidationException {
		validatePlayer(player);
		return PlayerDao.createPlayer(player);
	}

	
	private boolean validatePlayer(Player player) throws ValidationException {
		ValidationException validationException = new ValidationException();
		
		validateName(player.getName(), validationException);
		validateUsername(player.getUsername(), validationException);
		validatePassword(player.getPassword(), validationException);
		
		if (validationException.hasErrors()) {
			throw validationException;
		}
		return true;
	}
	
	
	
	private void validateName(String name, ValidationException validationException) {
		if (name.isEmpty()) {
			validationException.addError("name", "Name cannot be empty");
		}
		
		if (name.length() > 35) {
			validationException.addError("name", "Name length cannot be longer than 50 characters");
		}
	}
	
	private void validateUsername(String username, ValidationException validationException) {
		if (username.isEmpty()) {
			validationException.addError("username", "username cannot be empty");
		}
		
		if (username.length() > 20) {
			validationException.addError("username", "username length cannot be longer than 20 characters");
		}
		
		if (username.length() < 8) {
			validationException.addError("username", "username length cannot be less than 8 characters");
		}		
	}
	
	
	private void validatePassword(String password, ValidationException validationException) {
		if (password.isEmpty()) {
			validationException.addError("password", "username cannot be empty");
		}
		
		if (password.length() > 20) {
			validationException.addError("password", "username length cannot be longer than 20 characters");
		}
		
		if (password.length() < 8) {
			validationException.addError("password", "username length cannot be less than 8 characters");
		}
	}

}
