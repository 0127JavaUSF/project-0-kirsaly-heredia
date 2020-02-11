package com.revature.views;

import java.util.List;

import com.revature.daos.AccountDao;
import com.revature.daos.UserDao;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.InputUtil;

public class UserMenu implements View{

	UserDao userDao = new UserDao();
	
	@Override
	public void showMenu() {
		System.out.println("\n--------------------------");
		System.out.println("\nHello!\n");
		System.out.println("1. Check Star Balance");
		System.out.println("2. Deposit Starts");
		System.out.println("3. Withdraw Stars");
		System.out.println("4. Transfer Stars");
		System.out.println("5. Player Info");
		System.out.println("6. Add Account Player");
		System.out.println("7. Open an account.");
		System.out.println("8. Close Account");
		System.out.println("0. Logout");
	}

	@Override
	public View selectOption() {
		
		int selection = InputUtil.getIntInRange(0, 8);

		switch(selection) {
			case 1: checkBalance();
					return this;
			case 2: deposit();
					return this;
			case 3: withdraw();
					return this;
			case 4: transfer();
					return this;
			case 5: loadAccount();
					return this;
			case 6: addAccountUser();
					return this;
			case 7: openAccount();
					return this;
			case 8: closeAccount();
					return this;
			case 0: return new HomeScreen();
			default: return null;
		}
		
	}

	
	private void checkBalance() {
		System.out.println("You have __ stars.");
	}

	
	private void deposit() {
		System.out.println("How many stars will you drop off.");
//		int depAmt = InputUtil.getNextInt();
//		int bal = account.getBalance();
//		bal += depAmt;
//		System.out.println("You dropped off " + depAmt + " stars. Your new balance is " + bal);
	}
	
	
	private void withdraw() {
		System.out.println("How many stars will you take out.");
//		int withAmt = InputUtil.getNextInt();
//		int bal = account.getBalance();
//		bal += withAmt;
//		System.out.println("You took " + withAmt + " stars out. Your new balance is " + bal);
	}
	
	
	private void transfer() {
		System.out.println("You transfered $x. Your new balance is $x.");
	}
	
	
	//shows account details
	private Account loadAccount() {
		
		System.out.println("Enter account number: ");
		int accountNumber = InputUtil.getIntInRange(1, Integer.MAX_VALUE);
		
		Account account = AccountDao.getAccount(accountNumber);
				
		System.out.println(account);
		
		return account;
		//System.out.println("Account number:");
		//System.out.println("Account holders:");
	}
		
		
	private void addAccountUser() {
		System.out.println("You added [name] as a account holder.");
	}

	//changed to pkg
	//static
	static void openAccount() {
		System.out.println("What kind of account would you like to open. Select from the following.");
		System.out.println("1. Single Player CKG");
		System.out.println("2. SinglePlayer SV");
		System.out.println("3. Multi Player CKG");
		System.out.println("4. MultiPlayer SV");
		int choice = InputUtil.getNextInt();
		String accountType = "";
		
		do {
			switch(choice) {
				case 1: accountType = "Single Player CKG";
						break;
				case 2: accountType = "SinglePlayer SV";
						break;
				case 3: accountType = "Multi Player CKG";
						break;	
				case 4: accountType = "MultiPlayer SV";
						break;
				default: System.out.println("Invalid input. Select a number 1-4.");
			}
		} while (choice < 1 && choice > 4);
		
		System.out.println("How many stars would you like to open this account with: ");
		int depoAmt = InputUtil.getNextInt();
		
		Account account = new Account(0, depoAmt, accountType);
		account = AccountDao.openAccount(account);//how to use user that is returned in load user
		System.out.println(account);
	}
	
	
	private void closeAccount() {
		System.out.println("Enter the account number of the account you wish to close: ");
		System.out.println("Your account was closed.");
	}
	
	
	
		
	
	
	
	

}
