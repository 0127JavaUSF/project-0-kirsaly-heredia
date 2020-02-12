package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.Player;
import com.revature.util.ConnectionUtil;

public class PlayerAccountsDao {

	//create association between user and accounts--------------------------------------
		public static void createAssoc(int UserID, int accctNum) {
			
			try(Connection connection = ConnectionUtil.getConnection()) {
				
				String sql = "INSERT INTO playerAccounts (playerID, acctNum) " +
						" VALUES (?, ?) RETURNING *"; 

				
				PreparedStatement statement = connection.prepareStatement(sql);
				
				statement.setInt(1, UserID);
				statement.setInt(2, accctNum);

				statement.executeQuery();
							
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return;
		}
		
		
		//get all the accounts associated with a user--------------------------------------
		public List<Account> getAccountsForPlayers(Player player) {
			List<Account> accounts = new ArrayList<>();
			try(Connection connection = ConnectionUtil.getConnection()) {
				
				String sql = "SELECT accounts.acctNum, accounts.balance, accounts.acctType " +
				"FROM accounts " +
				"JOIN playerAccounts on  accounts.acctNum = playerAccounts.acctNum " +
				"JOIN players on players.playerID = playerAccounts.playerID " +
				"WHERE players.playerID = ? RETURNING *"; 
				
				PreparedStatement ps = connection.prepareStatement(sql);
				
				ps.setInt(1, player.getUserID());
				
				ResultSet result = ps.executeQuery();
				
				while(result.next()) {
					accounts.add(AccountDao.extractAccount(result));
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return accounts;
		}
		
		
		//get all the players associated with an account--------------------------------------
		public static List<Player> getPlayersForAccounts(Account account) {
			List<Player> players = new ArrayList<>();
			try(Connection connection = ConnectionUtil.getConnection()) {
				
				String sql = 				
				"SELECT players.playerID, players.name, players.username, players.password " +
				"FROM Players " +
				"JOIN playerAccounts on  players.playerID = playerAccounts.playerID " +
				"JOIN accounts on accounts.acctNum = playerAccounts.acctNum " +
				"WHERE accounts.acctNum = ?";
									
				PreparedStatement ps = connection.prepareStatement(sql);
				
				ps.setInt(1, account.getAcctNum());
				
				ResultSet result = ps.executeQuery();
				
				while(result.next()) {
					players.add(PlayerDao.extractPlayer(result));
				}
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return players;
		}
	
	
}
