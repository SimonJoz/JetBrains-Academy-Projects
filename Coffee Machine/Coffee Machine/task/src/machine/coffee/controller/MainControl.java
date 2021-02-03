package machine.coffee.controller;

import machine.coffee.IO.DataReader;
import machine.coffee.model.MachineResources;

import java.util.Arrays;
import java.util.InputMismatchException;

public class MainControl {
    private final DataReader reader = new DataReader();
    private final BuyerControl buyerControl = new BuyerControl(reader);
    private final MachineResources machineResources = new MachineResources(550, 400,
            120, 9, 540);


    public void mainMethod() {
        Options action;
        do {
            Options.printOptions();
            action = getOption();
            switch (action) {
                case EXIT:
                    exit();
                    break;
                case BUY:
                    buyMenu();
                    break;
                case FILL:
                    fill();
                    break;
                case TAKE:
                    withdrawMoney();
                    break;
                case REMAINING:
                    printStatus();
                    break;
            }
        } while (!action.equals(Options.EXIT));
    }

    private Options getOption() {
        Options option = null;
        boolean isOptionOk = false;
        while(!isOptionOk){
            try {
                option = Options.getOptionByID(reader.readInt());
                isOptionOk = true;
            } catch (InputMismatchException e) {
                System.err.println("INPUT MUST BE NUMBER !");
                System.out.println("Try again:");
            }catch (ArrayIndexOutOfBoundsException ex){
                System.err.println("UNKNOWN OPTION !");
                System.out.println("Try again:");

            }
        }
        return option;
    }

    private void exit() {
        reader.scClose();
    }

    private void printStatus() {
        System.out.println("\n" + machineResources + "\n");
    }

    private void buyMenu() {
        buyerControl.buyMenu(machineResources);
    }

    private void fill() {
        int watter = 0, milk = 0, beans = 0,cups = 0;
        try {
            System.out.println("\nWrite how many ml of water do you want to add:");
            watter = reader.readInt();
            System.out.println("Write how many ml of milk do you want to add: ");
            milk = reader.readInt();
            System.out.println("Write how many grams of coffee beans do you want to add:");
            beans = reader.readInt();
            System.out.println("Write how many disposable cups of coffee do you want to add:");
            cups = reader.readInt();
            System.out.println();
        } catch (InputMismatchException e) {
            System.err.println("OPERATION FAIL. INCORRECT INPUT !\n");
        }
        int money = machineResources.getMoney();
        machineResources.updateStatus(new MachineResources(money, watter, beans, cups, milk));
    }


    private void withdrawMoney() {
        int amount = machineResources.getMoney();
        String dollarSign = "$";
        System.out.printf("\nWithdrawing amount: %s%d.\n\n", dollarSign, amount);
        machineResources.withdraw();
    }

    private enum Options {
        BUY(0, "BUY COFFEE"),
        FILL(1, "FILL MACHINE"),
        TAKE(2, "WITHDRAW MONEY"),
        REMAINING(3, "CHECK MACHINE RESOURCES"),
        EXIT(4, "EXIT");

        private final int funID;
        private final String funDescription;

        Options(int funID, String funDescription) {
            this.funID = funID;
            this.funDescription = funDescription;
        }

        public static Options getOptionByID(int id) {
            return Options.values()[id];
        }

        public static void printOptions() {
            Arrays.stream(Options.values())
                    .forEach(option -> System.out.printf("%d -- %s\n", option.funID, option.funDescription));
        }
    }
}
