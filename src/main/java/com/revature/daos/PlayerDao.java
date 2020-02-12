package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.revature.models.Player;
import com.revature.util.ConnectionUtil;

public class PlayerDao {

	//retrieve a player from the db based on pword and uname-----------------------------
	public static Player getPlayer(String uname, String pword) {
		
		try(Connection connection = ConnectionUtil.getConnection()) { //get connection
			
			String sql = "SELECT playerID, name, username, password FROM players " +
					"WHERE username = ? AND password = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// Set PreparedStatement parameters
			statement.setString(1, uname);
			statement.setString(2, pword);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractPlayer(result);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	public static Player getPlayer(int userID) {
		
		try(Connection connection = ConnectionUtil.getConnection()) { //get connection
			
			String sql = "SELECT playerID, name, username, password FROM players " +
					"WHERE playerID = ?";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			// Set PreparedStatement parameters
			statement.setInt(1, userID);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractPlayer(result);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;		
	}
	
	
	
	//take the data from db and add to java variables----------------------------------------------
	static Player extractPlayer(ResultSet result) throws SQLException {
		int playerID = result.getInt("playerID");
		String name = result.getString("name");
		String username = result.getString("username");
		String password = result.getString("password");
		return new Player(playerID, name, username, password );
	}
	
	
	
	//add a player into the db based on user input---------------------------------------------------------
	public static Player createPlayer(Player player) {
		try(Connection connection = ConnectionUtil.getConnection()) {
			
			String sql = "INSERT INTO players (name, username, password) " +
					" VALUES (?, ?, ?) RETURNING *";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, player.getName());
			statement.setString(2, player.getUsername());
			statement.setString(3, player.getPassword());
			
			ResultSet result = statement.executeQuery();
			
			if(result.next()) {
				return extractPlayer(result);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}
