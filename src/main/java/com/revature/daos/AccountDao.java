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
				
				String sql = "SELECT acctNum, balance, acctType, joint, active FROM Accounts " +
						"WHERE acctNum = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				
				// Set PreparedStatement parameters
				statement.setInt(1, acctNum);
				
				ResultSet result = statement.executeQuery();
				
				if(result.next()) {
					int balance = result.getInt("balance");
					String acctType = result.getString("acctType");
					boolean joint = result.getBoolean("joint");
					boolean active = result.getBoolean("active");
					return new Account(acctNum, balance, acctType, joint, active);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;		
		}
		
		
		//put the play account input into the db--------------------------------------
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
			boolean joint = result.getBoolean("joint");
			boolean active = result.getBoolean("active");
			return new Account(accountNumber, balance, acctType, joint, active);
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
		
		
		//transaction transfer--------------------------------------
		public static Account transfer(int acctNum, int myBal, int trasnAcctID, int transBal) {
			try(Connection connection = ConnectionUtil.getConnection()) {
				
				//start transaction block
				connection.setAutoCommit(false);
				
				//update both accounts involved in the transfer
				String sql = "UPDATE accounts SET balance = ? " +
						"WHERE acctNum = ? RETURNING * ";
				
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, myBal);
				statement.setInt(2, acctNum);
				statement.executeQuery();
				
				sql = "UPDATE accounts SET balance = ? " +
						"WHERE acctNum = ? RETURNING * ";
				
				statement = connection.prepareStatement(sql);
				statement.setInt(1, transBal);
				statement.setInt(2, trasnAcctID);
				statement.executeQuery();
				
				//end transaction block
				connection.commit();
				
				//set back to default
				connection.setAutoCommit(true);
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		
		//mark an account inactive--------------------------------------
		public static Account makeInactive(int acctNum) {
			try(Connection connection = ConnectionUtil.getConnection()) {
				
				String sql = "UPDATE accounts SET active = ? " +
						" WHERE acctNum = ? RETURNING *"; 
				
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setBoolean(1, false);
				statement.setInt(2, acctNum);
				
				statement.executeQuery();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		//mark an account as joint--------------------------------------
		public static Account makeJoint(int acctNum) {
			try(Connection connection = ConnectionUtil.getConnection()) {
				
				String sql = "UPDATE accounts SET joint = ? " +
						" WHERE acctNum = ? RETURNING *"; 
				
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setBoolean(1, true);
				statement.setInt(2, acctNum);
				
				statement.executeQuery();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
}
