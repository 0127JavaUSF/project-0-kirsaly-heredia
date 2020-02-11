package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.List;

import com.revature.models.User;
import com.revature.models.Account;
import com.revature.util.ConnectionUtil;

public class UserDao {

	public static User getUser(String uname, String pword) {
		
		try(Connection connection = ConnectionUtil.getConnection()) { //get connection
			
			String sql = "SELECT userID, firstName, lastName, username, password FROM Users " +
					"WHERE username = ? AND password = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// Set PreparedStatement parameters
			statement.setString(1, uname);
			statement.setString(2, pword);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractUser(result);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	private static User extractUser(ResultSet result) throws SQLException {
		int userID = result.getInt("userID");
		String firstName = result.getString("firstName");
		String lastName = result.getString("lastName");
		String username = result.getString("username");
		String password = result.getString("password");
		return new User(userID, firstName, lastName, username, password );
	}
	
	public static User creatUser(User user) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO Users (firstName, lastName, username, password) " +
					" VALUES (?, ?, ? , ?) RETURNING *";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractUser(result);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
