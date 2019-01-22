package com.grug.solid.OCP;

import com.grug.solid.CoffeeDrink;
import com.grug.solid.CoffeeException;
import com.grug.solid.CoffeeSelection;

/**
 * Created by feichen on 2019/1/22.
 */
public interface CoffeeMachine {
    CoffeeDrink brewCoffee(CoffeeSelection selection) throws CoffeeException;
}
