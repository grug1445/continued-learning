package com.grug.solid.ISP;

import com.grug.solid.CoffeeDrink;
import com.grug.solid.CoffeeException;

/**
 * Created by feichen on 2019/1/22.
 */
public interface FilterCoffeeMachine extends CoffeeMachineRefactor{

    CoffeeDrink brewFilterCoffee() throws CoffeeException;
}
