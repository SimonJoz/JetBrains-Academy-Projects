package banking.controller;

import banking.db.CardDAO;
import banking.enums.AccountOptions;
import banking.enums.Options;
import banking.io.DataReader;
import banking.model.Card;
import banking.util.CardValidator;

import java.sql.SQLException;
import java.util.InputMismatchException;

public class AccountControl {
    private Card card;
    private final DataReader reader;
    private final CardDAO cardDAO;

    public AccountControl(DataReader reader, CardDAO cardDAO) {
        this.reader = reader;
        this.cardDAO = cardDAO;
    }

    public void accountLoop(Card card) throws SQLException {
        this.card = card;
        AccountOptions action;
        do {
            AccountOptions.printOptions();
            action = getOption();
            switch (action) {
                case BALANCE:
                    getBalance();
                    break;
                case INCOME:
                    addIncome();
                    break;
                case TRANSFER:
                    transfer();
                    break;
                case CLOSE:
                    closeAccount();
                    return;
                case LOGOUT:
                    logOut();
                    return;
                case EXIT:
                    exit();
                    break;
                case WRONG:
                    System.out.println(Options.WRONG.getDesc());
            }
        } while (!action.equals(AccountOptions.EXIT));
    }

    private void transfer() throws SQLException {
        System.out.println("Transfer\nEnter card number:");
        String receiver = reader.readString();
        if (!isReceivingNumberValid(card, receiver)) return;
        System.out.println("Enter how much money you want to transfer: ");
        long transferAmount = reader.readLong();
        if (!isEnoughMoney(transferAmount)) return;
        cardDAO.moneyTransfer(card, receiver, transferAmount);

    }

    private boolean isReceivingNumberValid(Card from, String toNumber) throws SQLException {
        if (from.getNumber().equals(toNumber)) {
            System.out.println("You can't transfer money to the same account!");
            return false;
        }
        if (!cardDAO.isCardNoExist(toNumber)) {
            System.out.println("Such a card does not exist.");
            return false;
        }
        if (!CardValidator.isCardNoValid(toNumber)) {
            System.out.println("Probably you made mistake in the card number. Please try again!");
            return false;
        }
        return true;
    }

    private boolean isEnoughMoney(long amount) throws SQLException {
        long balance = cardDAO.getBalance(card);
        if (balance - amount >= 0) {
            return true;
        }
        System.out.println("Not enough money!");
        return false;
    }

    private void getBalance() throws SQLException {
        long balance = cardDAO.getBalance(card);
        System.out.printf("Balance: %d\n", balance);
    }

    private void addIncome() throws SQLException {
        System.out.println("Enter income:");
        long income = reader.readLong();
        if (cardDAO.addIncome(card, income)) {
            System.out.println("Income was added!");
        }
    }

    private void closeAccount() throws SQLException {
        if (cardDAO.delete(card)) {
            System.out.println("The account has been closed!");
        }
    }

    private void exit() {
        reader.scClose();
        System.out.println("Bye!");
        System.exit(0);
    }

    private void logOut() {
        card = null; // gc will clean it faster ???? //
        System.out.println("You have successfully logged out!");
    }

    private AccountOptions getOption() {
        AccountOptions option = null;
        boolean isInputOk = false;
        while (!isInputOk) {
            try {
                option = AccountOptions.getOption(reader.readInt());
                isInputOk = true;
            } catch (InputMismatchException ex) {
                System.err.println("Input must be number ! Try again.");
            }
        }
        return option;
    }
}

