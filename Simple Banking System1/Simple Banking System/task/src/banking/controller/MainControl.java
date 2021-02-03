package banking.controller;


import banking.db.CardDAO;
import banking.db.DBConnection;
import banking.enums.Options;
import banking.io.DataReader;
import banking.model.Card;

import java.sql.SQLException;
import java.util.InputMismatchException;


public class MainControl {
    public static final String COMMAND = "-fileName";
    private final DataReader reader = new DataReader();
    private final DBConnection conn = new DBConnection();
    private final CardDAO cardDAO = new CardDAO(conn);
    private final AccountControl accountControl = new AccountControl(reader, cardDAO);

    public void mainMethod(String[] args) {
        try {
            connectDB(args);
            conn.createCardTable();
            mainLoop();
        } catch (SQLException ex) {
            System.out.println("Database connection error.");
            ex.printStackTrace();
        }finally {
            conn.closeConn();
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
    }

    private void connectDB(String[] args) throws SQLException {
        if (args.length >= 2 && COMMAND.equals(args[0])) {
            conn.createConn(args[1]);
        }
    }

    private void login() throws SQLException {
        System.out.println("Enter your card number:");
        String cardNo = reader.readString();
        System.out.println("Enter your PIN:");
        String pin = reader.readString();
        Card card = cardDAO.getCard(cardNo, pin);
        if (card != null) {
            System.out.println("You have successfully logged in!");
            accountControl.accountLoop(card);
        } else {
            System.out.println("Wrong card number or PIN!");
        }
    }

    private void createCard() {
        cardDAO.save(new Card());
    }

    private Options getOption() {
        Options option = null;
        boolean isInputOk = false;
        while (!isInputOk) {
            try {
                option = Options.getOption(reader.readInt());
                isInputOk = true;
            } catch (InputMismatchException ex) {
                System.err.println("Input must be number ! Try again.");
            }
        }
        return option;
    }

    private void exit() {
        reader.scClose();
        System.out.println("Bye!");
    }
}
