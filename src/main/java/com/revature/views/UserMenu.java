package com.revature.views;

import com.revature.daos.UserDao;
import com.revature.util.InputUtil;

public class UserMenu implements View{

	UserDao userDao = new UserDao();
	
	@Override
	public void showMenu() {
		System.out.println("Hello!");
		System.out.println("1. Open an account.");
		System.out.println("2. Account 3948");
		System.out.println("0. Logout");
		
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(0, 2);
		// User selects something - should be reusable
		// Do something with their selection, custom to this class
		switch(selection) {
			case 1: return null;
			case 2: return  new BankingMenu();
			case 0: return new HomeScreen();
			default: return null;
		}
	}

}
