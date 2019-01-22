package com.grug.solid.ISP;

import com.grug.solid.CoffeeException;
import com.grug.solid.GroundCoffee;

/**
 * Created by feichen on 2019/1/22.
 */
public interface CoffeeMachineRefactor {

    void addGroundCoffee(GroundCoffee newCoffee) throws CoffeeException;

}
