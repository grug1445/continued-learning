package com.grug.solid.ISP;

import com.grug.solid.CoffeeDrink;
import com.grug.solid.CoffeeException;
import com.grug.solid.GroundCoffee;

/**
 * Created by feichen on 2019/1/22.
 */
public interface CoffeeMachine {
    CoffeeDrink brewFilterCoffee() throws CoffeeException;

    void addGroundCoffee(GroundCoffee newCoffee) throws CoffeeException;

    CoffeeDrink brewEspresso() throws CoffeeException;
}
