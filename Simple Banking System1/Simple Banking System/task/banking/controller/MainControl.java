package banking.controller;


import banking.db.DBConnection;
import banking.enums.Options;
import banking.io.DataReader;
import banking.model.Bank;
import banking.model.Card;

import java.math.BigInteger;
import java.sql.SQLException;


public class MainControl {
    private final Bank bank = new Bank();
    private final DataReader reader = new DataReader();
    private final AccountControl accountControl = new AccountControl(reader);
    private final  DBConnection conn = new DBConnection();

    public void mainMethod(String[] args) {
        try {
            connectDB(args);
            conn.createCardTable();
            mainLoop();
        } catch (SQLException ex) {
            System.out.println("Database connection error.");
        }
    }

    public void mainLoop() throws SQLException {
        Options action;
        do {
            Options.printOptions();
            action = getOption();
            switch (action) {
                case CREATE:
                    createCard();
                    break;
                case LOGIN:
                    login();
                    break;
                case EXIT:
                    exit();
                    break;
                case WRONG:
                    System.out.println(Options.WRONG.getDesc());
            }
        } while (!action.equals(Options.EXIT));
        conn.closeConn();
    }


    private void connectDB(String[] args) throws SQLException {
        if(args.length == 2 ) {
            String databaseName = args[1];
            conn.createConn(databaseName);
        }
    }

    private void login() {
        System.out.println("Enter your card number:");
        String cardNo = reader.readString();
        System.out.println("Enter your PIN:");
        String pin = reader.readString();
        if (isCardValid(new Card(cardNo, pin, BigInteger.ZERO))) {
            System.out.println("You have successfully logged in!");
            accountControl.accountLoop();
        } else {
            System.out.println("Wrong card number or PIN!");
        }
    }

    private boolean isCardValid(Card card) {
        return bank.getCardsList().contains(card);
    }

    private void createCard() {
        Card card = new Card();
        conn.saveToDB(card);
        bank.getCardsList().add(card);
        System.out.printf("Your card has been created\nYour card number: \n%s\nYour card PIN: \n%s\n",
                card.getNumber(), card.getPin());
    }

    private Options getOption() {
        return Options.getOption(reader.readInt());
    }

    private void exit() {
        reader.scClose();
        System.out.println("Bye!");
    }

}
