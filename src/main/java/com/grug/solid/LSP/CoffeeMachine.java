package com.grug.solid.LSP;

import com.grug.solid.Coffee;
import com.grug.solid.CoffeeDrink;
import com.grug.solid.CoffeeException;
import com.grug.solid.CoffeeSelection;

public interface CoffeeMachine {
	
	CoffeeDrink brewCoffee(CoffeeSelection selection) throws CoffeeException;

	void addCoffee(CoffeeSelection sel, Coffee newCoffee) throws CoffeeException;
}