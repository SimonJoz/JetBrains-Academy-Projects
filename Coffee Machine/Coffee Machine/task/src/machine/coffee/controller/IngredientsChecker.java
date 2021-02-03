package machine.coffee.controller;

import machine.coffee.model.MachineResources;
import machine.coffee.model.Coffee;

public final class IngredientsChecker {
    private final boolean hasEnoughWater;
    private final boolean hasEnoughMilk;
    private final boolean hasEnoughCoffeeBeans;
    private final boolean hasEnoughCups;

    public IngredientsChecker(MachineResources status, Coffee coffee) {
        this.hasEnoughWater = (status.getWater() - coffee.getWater()) >= 0;
        this.hasEnoughMilk = (status.getMilk() - coffee.getMilk()) >= 0;
        this.hasEnoughCoffeeBeans = (status.getCoffeeBeans() - coffee.getCoffeeBeans()) >= 0;
        this.hasEnoughCups = status.getDisposalCup() > 0;
    }

    public final boolean hasEnough() {
        if (!hasEnoughWater) {
            System.out.println("Sorry, not enough water!\n");
            return false;
        } else if (!hasEnoughMilk) {
            System.out.println("Sorry, not enough milk!\n");
            return false;
        } else if (!hasEnoughCoffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!\n");
            return false;
        } else if (!hasEnoughCups) {
            System.out.println("Sorry, not enough cups!\n");
            return false;
        } else {
            System.out.println("Making your coffee...\n");
        }
        return true;
    }
}
