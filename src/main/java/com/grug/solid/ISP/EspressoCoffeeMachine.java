package com.grug.solid.ISP;

import com.grug.solid.CoffeeDrink;
import com.grug.solid.CoffeeException;

/**
 * Created by feichen on 2019/1/22.
 */
public interface EspressoCoffeeMachine extends CoffeeMachineRefactor {

    CoffeeDrink brewEspresso() throws CoffeeException;
}
