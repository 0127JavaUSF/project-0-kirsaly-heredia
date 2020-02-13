package com.revature.service;

import com.revature.exceptions.ValidationException;
import com.revature.models.Player;

public interface IPlayerService {
	
	public Player createPlayer(Player player) throws ValidationException;
}
