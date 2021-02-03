package banking.controller;

import banking.enums.AccountOptions;
import banking.io.DataReader;

public class AccountControl {
    private final DataReader reader;

    public AccountControl(DataReader reader) {
        this.reader = reader;
    }

    public void accountLoop() {
        AccountOptions action;
        do {
            AccountOptions.printOptions();
            action = getOption();
            switch (action) {
                case BALANCE:
                    getBalance();
                    break;
                case LOGOUT:
                    logOut();
                    return;
                case EXIT:
                    exit();
                    break;
                }
        } while (!action.equals(AccountOptions.EXIT));
    }

    private void exit() {
        reader.scClose();
        System.out.println("Bye!");
        System.exit(0);
    }

    private void getBalance() {
        System.out.println("Balance: 0");
    }

    private void logOut() {
        System.out.println("You have successfully logged out!");
    }

    private AccountOptions getOption() {
        return AccountOptions.getOption(reader.readInt());
    }
}

