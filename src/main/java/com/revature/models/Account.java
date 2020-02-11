package com.revature.models;

public class Account {

	private int accountNum;
	private int balance;
	private String accountType;
	
	//getter and setters
	public int getAccountNum() {
		return accountNum;
	}
	
	
	public void setAccountNum(int accountNum) {
		this.accountNum = accountNum;
	}
	
	
	public int getBalance() {
		return balance;
	}
	
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	
	public String getAccountType() {
		return accountType;
	}
	
	
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
	//constructor
	public Account(int accountNum, int balance, String accountType) {
		super();
		this.accountNum = accountNum;
		this.balance = balance;
		this.accountType = accountType;
	}
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//hash code and equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountNum;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + balance;
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountNum != other.accountNum)
			return false;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (balance != other.balance)
			return false;
		return true;
	}
	
	
	//to string
	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", balance=" + balance + ", accountType=" + accountType + "]";
	}
	
	
	


	
}
