package machine.coffee.controller;

import machine.coffee.IO.DataReader;
import machine.coffee.model.Coffee;
import machine.coffee.model.MachineResources;

import java.util.Arrays;
import java.util.InputMismatchException;

public class BuyerControl {
    private final DataReader reader;

    public BuyerControl(DataReader reader) {
        this.reader = reader;
    }

    void buyMenu(MachineResources status) {
        CoffeeType.printCoffeeChoice();
        CoffeeType choice = getType();
        switch (choice) {
            case ESPRESSO:
                sellCoffee(status, Coffee.ESPRESSO);
                break;
            case LATTE:
                sellCoffee(status, Coffee.LATTE);
                break;
            case CAPPUCCINO:
                sellCoffee(status, Coffee.CAPPUCCINO);
                break;
            case EXIT:
                break;
        }
    }

    private CoffeeType getType() {
        CoffeeType type = null;
        boolean exist = false;
        while(!exist){
            try {
                type = CoffeeType.getTypeByID(reader.readInt());
                exist = true;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("UNKNOWN OPTION");
                System.out.println("Try again:");
            }catch (InputMismatchException ex){
                System.err.println("INPUT MUST BE NUMBER!");
                System.out.println("Try again:");
            }
        }
        return type;
    }

    private void sellCoffee(MachineResources status, Coffee coffee) {
        var ingredientsChecker = new IngredientsChecker(status, coffee);
        if (ingredientsChecker.hasEnough())
            status.updateStatus(coffee);
    }

    private enum CoffeeType {
        ESPRESSO(0, "Espresso"),
        LATTE(1, "Latte"),
        CAPPUCCINO(2, "Cappuccino"),
        EXIT(3, "Back to main menu");

        private final int id;
        private final String description;

        CoffeeType(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public static CoffeeType getTypeByID(int id) {
            return CoffeeType.values()[id];
        }

        public static void printCoffeeChoice() {
            Arrays.stream(CoffeeType.values())
                    .forEach(coffee -> System.out.printf("%d -- %s\n", coffee.id, coffee.description));
        }
    }

}
