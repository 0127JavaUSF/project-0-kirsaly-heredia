package com.revature.views;

import com.revature.daos.PlayerDao;
import com.revature.models.Player;
import com.revature.util.InputUtil;

public class HomeScreen implements View{

	@Override
	public void showMenu() {
		System.out.println("__________________________________\n");
		System.out.println("Welcome to Mario Party Center!");                                  
		System.out.println("__________________________________\n");
		System.out.println("1. Login");
		System.out.println("2. SignUp");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(1, 2);
		switch(selection) {
			case 1: login();
					return new UserMenu();
			case 2: signup();
					UserMenu.openAccount();
					return new UserMenu();
			default: return this;
		}
	}
	
	
	Player login() {
		System.out.println("Enter username: ");
		String uname = InputUtil.getNextString();
		
		System.out.println("Enter password: ");
		String pword = InputUtil.getNextString();
		
		Player player = PlayerDao.getPlayer(uname, pword);
		System.out.println(player);
		
		return player;
	}
	
	
	void signup() {
		System.out.println("Enter your first name");
		String name = InputUtil.getNextString();
		
		System.out.println("Enter a username");
		String username = InputUtil.getNextString();
		
		System.out.println("Enter a password");
		String password = InputUtil.getNextString();

		Player player = new Player(0, name, username, password);
		player = PlayerDao.createPlayer(player);
		System.out.println(player);
	}
	

}
