package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class AccountDao {

		
		//pull account information from the db----------------------------------------
		public static Account getAccount(int acctNum) {
			
			try(Connection connection = ConnectionUtil.getConnection()) { //get connection
				
				String sql = "SELECT acctNum, balance, acctType FROM Accounts " +
						"WHERE acctNum = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				
				// Set PreparedStatement parameters
				statement.setInt(1, acctNum);
				
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					int balance = result.getInt("balance");
					String acctType = result.getString("acctType");
					return new Account(acctNum, balance, acctType);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;		
		}
		
		
		//put the user input into the db--------------------------------------
		public static Account openAccount(Account account) {
			try(Connection connection = ConnectionUtil.getConnection()) {
				
				String sql = "INSERT INTO Accounts (balance, acctType) " +
						" VALUES (?, ?) RETURNING *"; 
				
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, account.getBalance());
				statement.setString(2, account.getAcctType());
				
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					return extractAccount(result);
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		//place db data into java variables---------------------------------
		static Account extractAccount(ResultSet result) throws SQLException {
			int accountNumber = result.getInt("acctNum");
			int balance = result.getInt("balance");
			String acctType = result.getString("acctType");
			return new Account(accountNumber, balance, acctType);
		}
		
		
		//update balance in db--------------------------------------
		public static Account updateBalance(int acctNum, int newBalance) {
			try(Connection connection = ConnectionUtil.getConnection()) {
				
				String sql = "UPDATE accounts SET balance = ? " +
						" WHERE acctNum = ? RETURNING *"; 
				
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, newBalance);
				statement.setInt(2, acctNum);
				
				statement.executeQuery();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		
		//remove an account from the db with a specified accountID--------------------------
		public static Account closeAccount(int acctID) {
			try(Connection connection = ConnectionUtil.getConnection()) {
				
				String sql = "DELETE FROM accounts" +
						" WHERE acctNum = ?"; 
				
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, acctID);
				statement.executeQuery();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
}
