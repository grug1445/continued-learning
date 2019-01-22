package com.grug.solid.DIP;

import com.grug.solid.CoffeeDrink;
import com.grug.solid.CoffeeException;

/**
 * Created by feichen on 2019/1/22.
 */
public class CoffeeApp {
    private CoffeeMachine coffeeMachine;

    public CoffeeApp(CoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public CoffeeDrink prepareFilterCoffee() throws CoffeeException {
        CoffeeDrink coffee = this.coffeeMachine.brewFilterCoffee();
        System.out.println("Coffee is ready!");
        return coffee;
    }
}
