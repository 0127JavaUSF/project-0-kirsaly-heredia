package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDao {

	//change static
	//pull account information from the db
	public static Account getAccount(int accountNumber) {
		try(Connection connection = ConnectionUtil.getConnection()) {
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
	
}
