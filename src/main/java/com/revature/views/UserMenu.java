package com.revature.views;

import java.util.List;

import com.revature.daos.AccountDao;
import com.revature.daos.PlayerAccountsDao;
import com.revature.daos.PlayerDao;
import com.revature.models.Account;
import com.revature.models.Player;
import com.revature.util.InputUtil;

public class UserMenu implements View{

	Player player;
	
	UserMenu(Player player){
		this.player = player;
	}
	
	
	@Override
	public void showMenu() {
		System.out.println("--------------------------\n");
		System.out.println("1. Check Star Balance");
		System.out.println("2. Drop off Stars");
		System.out.println("3. Steal Stars (from yourself)");
		System.out.println("4. Transfer Stars");
		System.out.println("5. Account Info");
		System.out.println("6. Add Account Player");
		System.out.println("7. Open an account.");
		System.out.println("8. Close Account");
		System.out.println("0. Logout");
		System.out.println("\n--------------------------");
	}

	@Override
	public View selectOption() {
		
		int selection = InputUtil.getIntInRange(0, 8);

		switch(selection) {
			case 1: showStars();
					return this;
			case 2: dropStars();
					return this;
			case 3: stealStars();
					return this;
			case 4: transferStars();
					return this;
			case 5: acctInfo();
					return this;
			case 6: addAcctPlayers();
					return this;
			case 7: openAccount(player);
					return this;
			case 8: closeAccount();
					return this;
			case 0: return new HomeScreen();
			default: return null;
		}	
	}	
	
		
	

	// get information to show star balance account-------------------
	void showStars() {
		
		System.out.println("Enter an account number.");
		int acctID = InputUtil.getNextInt();
		
		if( acctValid(acctID) == true) {
			int balance = AccountDao.getAccount(acctID).getBalance();
			System.out.println("\n--------------------------\n");
			System.out.println("You have " + balance + " stars.\n");
		}
	}
	
	
	boolean acctValid(int acctID) {
			
		List<Integer> accounts = PlayerAccountsDao.getAccountsForPlayers(player);
		
		if( AccountDao.getAccount(acctID) == null  | accounts.contains(acctID) == false || AccountDao.getAccount(acctID).isActive() == false) {
			do {
				System.out.println("Invalid Account. Try again.");
				System.out.println("Enter an account number.");
				acctID = InputUtil.getNextInt();
			} while (AccountDao.getAccount(acctID) == null | accounts.contains(acctID) == false || AccountDao.getAccount(acctID).isActive() == false);		
		}
		return true;
	}
	
	
	// get information to deposit stars into account-------------------
	void dropStars() {
		
		System.out.println("Enter an account number.");
		int acctID = InputUtil.getNextInt();
		
		acctValid(acctID);
		
		System.out.println("How many stars will you drop off.");
		int depAmt = InputUtil.getNextInt();
		
		if (depAmt < 1) {
			do {
				System.out.println("You must drop off at least 1 star.");
				System.out.println("How many stars will you drop off.");
				depAmt = InputUtil.getNextInt();
			} while (depAmt < 1);
		}
		
		int balance = AccountDao.getAccount(acctID).getBalance();
		balance += depAmt;
		AccountDao.updateBalance(acctID, balance);
		System.out.println("\n--------------------------\n");
		System.out.println("You dropped off " + depAmt + " stars. Your new balance is " + balance + " stars.\n");
	}
	
	
	// get information to withdraw stars into account-------------------
	void stealStars() {
		System.out.println("Enter an account number.");
		int acctID = InputUtil.getNextInt();
		
		acctValid(acctID);
		
		System.out.println("How many stars will you steal.");
		int withAmt = InputUtil.getNextInt();
		int balance = AccountDao.getAccount(acctID).getBalance();
		
		if (withAmt > balance) {
			do {
				System.out.println("You do not have that many stars in your account.");
				System.out.println("How many stars will you steal.");
				withAmt = InputUtil.getNextInt();
			} while (withAmt > balance);
		}
		
		balance -= withAmt;
		AccountDao.updateBalance(acctID, balance);
		System.out.println("\n--------------------------\n");
		System.out.println("You stole " + withAmt + " stars from yourself. Your new balance is " + balance + " stars.\n");
	}
	
	
	// get information to transfer stars between specified accounts-------------------
	void transferStars() {
		System.out.println("Enter the account number from which you wish to transfer.");
		int myAcctID = InputUtil.getNextInt(); //current player's account 
		
		acctValid(myAcctID);
		
		System.out.println("How many stars will you transfer?");
		int transAmt = InputUtil.getNextInt(); //stars to transfer
		
		System.out.println("Enter the account number of the receiveing account.");
		int transAcctID = InputUtil.getNextInt(); //other account to transfer to
		
		int myBal = AccountDao.getAccount(myAcctID).getBalance(); //retrieve current player's account balance
		myBal -= transAmt; //subtract transfer amount from current player balance
		
		int transBal = AccountDao.getAccount(transAcctID).getBalance(); //retrieve other account balance
		transBal += transAmt;
		
		AccountDao.transfer(myAcctID, myBal, transAcctID, transBal);
		
		System.out.println("\n--------------------------\n");
		System.out.println("You transfered " + transAmt + " stars to account " + 
		transAcctID + " from account " + myAcctID + ". Your new balance is " + myBal + " stars.\n");
	}
	
	
	
	//print players on an account-----------------------------------
	void acctInfo() {
		System.out.println("Enter account number: ");
		
		int id = InputUtil.getNextInt();
		
		acctValid(id);
		
		Account account = AccountDao.getAccount(id);
		List<Player> players = PlayerAccountsDao.getPlayersForAccounts(account);
		System.out.println("\n--------------------------");
		System.out.println("\nThe following players are on account " + id);
		System.out.println("\n| id |    name    |");
		players.forEach(p -> {
			System.out.printf("|%3d |%-11s |\n", 
					p.getUserID(), p.getName());			
		});
	}
	
	
	
	//add a player to an account-----------------------------------
	void addAcctPlayers() {
		System.out.println("What player would you like to add to your account? Enter userID.");
		int playerID = InputUtil.getNextInt();
		System.out.println("Which account would you like to add them to? Enter account number.");
		int acctID = InputUtil.getNextInt();
		
		acctValid(acctID);
		
		Player otherPlayer = PlayerDao.getPlayer(playerID);
		
		AccountDao.makeJoint(acctID);
		PlayerAccountsDao.createAssoc(playerID, acctID);
		System.out.println("You added " + otherPlayer.getName() + " to account " + acctID + "\n");	
	}
	
	
	// get information to create a new account on start up-------------------
	static Player openAccount(Player player) {
		System.out.println("What kind of account would you like to open. Select from the following.");
		System.out.println("1. Checking");
		System.out.println("2. Savings");
		
		int choice = InputUtil.getIntInRange(1, 2);
		
		String acctType = "";
		
		do {
			switch(choice) {
				case 1: acctType = "CKG";
						break;
				case 2: acctType = "SAV";
						break;
				default: System.out.println("Invalid input. Select a number 1-2.");
			}
		} while (choice < 1 && choice > 2);
		
		System.out.println("How many stars would you like to open this account with: ");
		int depoAmt = InputUtil.getNextInt();
		
		boolean active = true;
		boolean joint = false;
		
		Account account = new Account(0, depoAmt, acctType, joint, active);
		account = AccountDao.openAccount(account);//how to use user that is returned in load user
		
		PlayerAccountsDao.createAssoc(player.getUserID(), account.getAcctNum() );
		
		return player;
	}
	
	// get information to close an account-------------------
	void closeAccount() {
		System.out.println("Enter the accountID of the account you would like to close.");
		int acctID = InputUtil.getNextInt();
		
		acctValid(acctID);
		
		AccountDao.makeInactive(acctID);
		System.out.println("You closed account " + acctID + "\n");
	}
	

}