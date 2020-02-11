package com.revature.views;

import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;
import com.revature.models.Account;
import com.revature.models.User;
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
		// User selects something - should be reusable
		// Do something with their selection, custom to this class
		switch(selection) {
			case 1: loadUser();
				return new UserMenu();
			case 2: creatUser();
			return new UserMenu();
			default: return null;
		}

	}
	
	
	
	//shows user details
	private User loadUser() {
		
		System.out.println("Enter username: ");
		String uname = InputUtil.getNextString();
		System.out.println("Enter password: ");
		String pword = InputUtil.getNextString();
		
		User user = UserDao.getUser(uname, pword);
				
		System.out.println(user);
		
		return user;
		//System.out.println("Account number:");
		//System.out.println("Account holders:");
	}
	
	private void creatUser() {
		System.out.println("Enter your first name");
		String firstName = InputUtil.getNextString();
		System.out.println("Enter your last name");
		String lastName = InputUtil.getNextString();
		
		System.out.println("Enter a username");
		String username = InputUtil.getNextString();
		System.out.println("Enter a password");
		String password = InputUtil.getNextString();

		User user = new User(0, firstName, lastName, username, password);
		user = UserDao.creatUser(user);//how to use user that is returned in load user
		System.out.println(user);
		
		UserMenu.openAccount();
	}

}
