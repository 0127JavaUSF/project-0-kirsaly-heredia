package com.revature.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.stream.IntStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.util.reflection.FieldSetter;
import org.mockito.runners.MockitoJUnitRunner;

import com.revature.daos.PlayerDao;
import com.revature.exceptions.ValidationException;
import com.revature.models.Player;
import com.revature.service.IPlayerService;
import com.revature.service.PlayerService;


@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

	static IPlayerService PlayerService = new PlayerService();
	
	@Mock
	static PlayerDao mockPlayerDao;
	
	
	String validName = "Myname";
	String validUsername = "Myname123";
	String validPassword = "123Myname";

	
	@Before
	public void setup() throws NoSuchFieldException, SecurityException {
		// Dao fields are private, setting values using Mockito utility class FieldSetter
		new FieldSetter(PlayerService, PlayerService.getClass().getDeclaredField("PlayerDao")).set(mockPlayerDao);
		
		// Default stubs for methods used by the residentService
		when(PlayerDao.createPlayer(Mockito.any(Player.class))).thenReturn(new Player());
	}
	
	@Test(expected = ValidationException.class)
	public void testEmptyName() throws Exception {
		String invalidName = "";
		Player player = new Player(0, invalidName, validUsername, validPassword);
		PlayerService.createPlayer(player);
		fail();
	}
	
	@Test(expected = ValidationException.class)
	public void testLongUname() throws Exception {
		// Create string of length 36
		String invalidUsername = IntStream.range(0, 20).mapToObj(i -> "a").reduce("", (a, b) ->  a+b);
		
		Player player = new Player(0, validName, invalidUsername, validPassword);
		PlayerService.createPlayer(player);
		fail();
	}
	
	
	
}
