package com.grug.solid.OCP;

import com.grug.solid.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feichen on 2019/1/22.
 */
public class BasicCoffeeApp {
    private BasicCoffeeMachine coffeeMachine;

    public BasicCoffeeApp(BasicCoffeeMachine coffeeMachine) {
        this.coffeeMachine = coffeeMachine;
    }

    public CoffeeDrink prepareCoffee(CoffeeSelection selection) throws CoffeeException {
        CoffeeDrink coffee = this.coffeeMachine.brewCoffee(selection);
        System.out.println("Coffee is ready!");
        return coffee;
    }

    public static void main(String[] args) {
        // create a Map of available coffee beans
        Map<CoffeeSelection, GroundCoffee> beans = new HashMap<CoffeeSelection, GroundCoffee>();
        beans.put(CoffeeSelection.FILTER_COFFEE, new GroundCoffee("My favorite filter coffee bean", 1000));

        // get a new CoffeeMachine object
        BasicCoffeeMachine machine = new BasicCoffeeMachine(beans);

        // Instantiate CoffeeApp
        BasicCoffeeApp app = new BasicCoffeeApp(machine);

        // brew a fresh coffee
        try {
            app.prepareCoffee(CoffeeSelection.FILTER_COFFEE);
        } catch (CoffeeException e) {
            e.printStackTrace();
        }

        //add ground coffee
        try {
            machine.addGroundCoffee(CoffeeSelection.FILTER_COFFEE, new GroundCoffee("filter", 2000));
        } catch (CoffeeException e) {
            e.printStackTrace();
        }
    } // end main
}
