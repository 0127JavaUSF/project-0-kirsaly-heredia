package com.revature.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.views.HomeScreen;

public class UserServiceTest {

	private HomeScreen homeScreen;

	@BeforeClass // runs once before any tests
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass // runs once after all tests finish
	public static void tearDownAfterClass() throws Exception {
	}

	@Before // runs before each test
	public void setUp() throws Exception {
		homeScreen = new HomeScreen();
	}

	@After // runs after each test
	public void tearDown() throws Exception {
	}

	@Test
	public void testShortCred() {
		//assertTrue();
	}
	

	
}
