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
			case 1: return new UserMenu(login());
			case 2: return new UserMenu(signup());
			default: return this;
		}
	}
	
	
	Player login() {
		
		System.out.println("Enter username: ");
		String uname = InputUtil.getNextString();
		
		System.out.println("Enter password: ");
		String pword = InputUtil.getNextString();
		
		//if the username and password combination doesn't
		if( PlayerDao.getPlayer(uname, pword) == null) {
			
			do {
				System.out.println("\nInvalid login. Try again.");
				
				System.out.println("Enter username: ");
				uname = InputUtil.getNextString();
				
				System.out.println("Enter password: ");
				pword = InputUtil.getNextString();
			} while( PlayerDao.getPlayer(uname, pword) == null);
		}
		
		Player player = PlayerDao.getPlayer(uname, pword);
		
		System.out.println("\n--------------------------");
		System.out.println("\nWelcome " + player.getName() + "!\n");
		
		return player;
	}
	
	
	Player signup() {
		System.out.println("Enter your first name");
		String name = InputUtil.getNextString();
		
		System.out.println("Enter a username");
		String username = InputUtil.getNextString();
		
		System.out.println("Enter a password");
		String password = InputUtil.getNextString();

		Player player = new Player(0, name, username, password);
		player = PlayerDao.createPlayer(player);
		
		UserMenu.openAccount(player);
		
		System.out.println("\n--------------------------");
		System.out.println("\nWelcome " + player.getName() + "!\n");
				
		return player;
	}
	

}
