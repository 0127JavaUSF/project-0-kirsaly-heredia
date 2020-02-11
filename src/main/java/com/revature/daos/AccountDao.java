package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.util.ConnectionUtil;

public class AccountDao {

	//change static
	//pull account information from the db
	public static Account getAccount(int accountNumber) {
		
		try(Connection connection = ConnectionUtil.getConnection()) { //get connection
			
			String sql = "SELECT accountNumber, balance, accountType FROM Accounts " +
					"WHERE accountNumber = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// Set PreparedStatement parameters
			statement.setInt(1, accountNumber);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				int balance = result.getInt("balance");
				String accountType = result.getString("accountType");
				return new Account(accountNumber, balance, accountType);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	//changed to static
	public static Account openAccount(Account account) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO Accounts (balance, accountType) " +
					" VALUES (?, ?) RETURNING *"; 
//					"INSERT INTO usrAccounts (userID, accountNumber) " +
//					" VALUES (?, ?) RETURNING *";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, account.getBalance());
			statement.setString(2, account.getAccountType());
//			statement.setInt(3, user.getUserID());
//			statement.setInt(4, account.getAccountNum());
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractAccount(result);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//static
	private static Account extractAccount(ResultSet result) throws SQLException {
		int accountNumber = result.getInt("accountNumber");
		int balance = result.getInt("balance");
		String accountType = result.getString("accountType");
		return new Account(accountNumber, balance, accountType);
	}
	
	
}
