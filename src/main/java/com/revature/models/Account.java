package com.revature.models;

public class Account {

	private int acctNum;
	private int balance;
	private String acctType;
	private boolean joint;
	private boolean active;
	
	
	// getters and setters -----------------------------------------------------
	public int getAcctNum() {
		return acctNum;
	}
	
	public void setAcctNum(int acctNum) {
		this.acctNum = acctNum;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	public String getAcctType() {
		return acctType;
	}
	
	public void setAcctType(String acctType) {
		this.acctType = acctType;
	}
	
	public boolean isJoint() {
		return joint;
	}
	
	public void setJoint(boolean joint) {
		this.joint = joint;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	// constructors -----------------------------------------------------
	public Account(int acctNum, int balance, String acctType, boolean joint, boolean active) {
		super();
		this.acctNum = acctNum;
		this.balance = balance;
		this.acctType = acctType;
		this.joint = joint;
		this.active = active;
	}
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	// hashcode/equals/tostring -----------------------------------------------------
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acctNum;
		result = prime * result + ((acctType == null) ? 0 : acctType.hashCode());
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + balance;
		result = prime * result + (joint ? 1231 : 1237);
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
		if (acctNum != other.acctNum)
			return false;
		if (acctType == null) {
			if (other.acctType != null)
				return false;
		} else if (!acctType.equals(other.acctType))
			return false;
		if (active != other.active)
			return false;
		if (balance != other.balance)
			return false;
		if (joint != other.joint)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Account [acctNum=" + acctNum + ", balance=" + balance + ", acctType=" + acctType + ", joint=" + joint
				+ ", active=" + active + "]";
	}
	
	
	
}
