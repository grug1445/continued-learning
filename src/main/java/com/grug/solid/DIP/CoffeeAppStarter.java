package com.grug.solid.DIP;

import com.grug.solid.CoffeeBean;
import com.grug.solid.CoffeeException;
import com.grug.solid.CoffeeSelection;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feichen on 2019/1/22.
 */
public class CoffeeAppStarter {
    public static void main(String[] args) {
        // create a Map of available coffee beans
        Map<CoffeeSelection, CoffeeBean> beans = new HashMap<CoffeeSelection, CoffeeBean>();
        beans.put(CoffeeSelection.ESPRESSO, new CoffeeBean("My favorite espresso bean", 1000));
        beans.put(CoffeeSelection.FILTER_COFFEE, new CoffeeBean("My favorite filter coffee bean", 1000));
        // get a new CoffeeMachine object
        PremiumCoffeeMachineRefactor machine = new PremiumCoffeeMachineRefactor(beans);

        // Instantiate CoffeeApp
        CoffeeApp app = new CoffeeApp(machine);
        // brew a fresh coffee
        try {
            app.prepareFilterCoffee();
        } catch (CoffeeException e) {
            e.printStackTrace();
        }
    }
}
