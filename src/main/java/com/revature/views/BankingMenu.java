package com.revature.views;

import com.revature.daos.AccountDao;
import com.revature.models.Account;
import com.revature.util.InputUtil;

public class BankingMenu implements View {

	@Override
	public void showMenu() {
		System.out.println("1. Check Balance");
		System.out.println("2. Deposit");
		System.out.println("3. Withdraw");
		System.out.println("4. Transfer Money");
		System.out.println("5. Account Information");
		System.out.println("6. Add Account User");
		System.out.println("7. Close Account");
		System.out.println("0. Exit");
	}

	@Override
	public View selectOption() {
		int selection = InputUtil.getIntInRange(0, 7);
		// User selects something - should be reusable
		// Do something with their selection, custom to this class
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
			case 7: closeAccount();
					return this;
			case 0: return new UserMenu();
			default: return null;
		}
	}

	
	private void checkBalance() {
		System.out.println("Your current balance is $x.");
	}

	
	private void deposit() {
		System.out.println("You deposited $x. Your new balance is $x.");
	}
	
	
	private void withdraw() {
		System.out.println("You withdrew $x. Your new balance is $x.");
	}
	
	
	private void transfer() {
		System.out.println("You transfered $x. Your new balance is $x.");
	}
	
	
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
		System.out.println("You add [name] as a account holder.");
	}

	private void closeAccount() {
		System.out.println("Your account was closed.");
	}

}
