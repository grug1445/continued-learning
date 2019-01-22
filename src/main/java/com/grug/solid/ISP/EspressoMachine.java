package com.grug.solid.ISP;

import com.grug.solid.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by feichen on 2019/1/22.
 */
public class EspressoMachine implements CoffeeMachine {
    private Map configMap;
    private GroundCoffee groundCoffee;
    private BrewingUnit brewingUnit;

    public EspressoMachine(GroundCoffee coffee) {
        this.groundCoffee = coffee;
        this.brewingUnit = new BrewingUnit();

        this.configMap = new HashMap();
        this.configMap.put(CoffeeSelection.ESPRESSO, new Configuration(8, 28));
    }

    @Override
    public CoffeeDrink brewEspresso() {
        Configuration config = (Configuration) configMap.get(CoffeeSelection.ESPRESSO);

        // brew a espresso coffee
        return this.brewingUnit.brew(CoffeeSelection.ESPRESSO,
                this.groundCoffee, config.getQuantityWater());
    }

    @Override
    public void addGroundCoffee(GroundCoffee newCoffee) throws CoffeeException {
        if (this.groundCoffee != null) {
            if (this.groundCoffee.getName().equals(newCoffee.getName())) {
                this.groundCoffee.setQuantity(this.groundCoffee.getQuantity()
                        + newCoffee.getQuantity());
            } else {
                throw new CoffeeException(
                        "Only one kind of coffee supported for each CoffeeSelection.");
            }
        } else {
            this.groundCoffee = newCoffee;
        }
    }

    @Override
    public CoffeeDrink brewFilterCoffee() throws CoffeeException {
        throw new CoffeeException("This machine only brew espresso.");
    }
}
