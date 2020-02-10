package com.revature.views;


/*
 * Interface implemented by any class that represents a view that will display
 * a menu for inner application navigation.
 */

public interface View {
	void showMenu();
	View selectOption();
}
