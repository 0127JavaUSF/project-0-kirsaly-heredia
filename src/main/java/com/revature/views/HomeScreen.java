package com.revature.views;

import com.revature.util.InputUtil;

public class HomeScreen implements View{

	@Override
	public void showMenu() {
		System.out.println("Welcome to TransAct Banking.");
		System.out.println("1. Login");
		System.out.println("2. SignUp");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(1, 2);
		// User selects something - should be reusable
		// Do something with their selection, custom to this class
		switch(selection) {
			case 1: return new UserMenu();
			case 2: return  null;
			default: return null;
		}

	}
	
	
	public void login() {
		
	}
	
	
	public void signup() {
		
	}

}
