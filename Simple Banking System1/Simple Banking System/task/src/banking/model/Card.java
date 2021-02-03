package banking.model;

import banking.util.CardGenerator;

import java.util.Objects;

public final class Card {
    private int id;
    private final String number;
    private final String pin;
    private final long balance;

    public Card() {
        this.number = CardGenerator.generateCardNo();
        this.pin = CardGenerator.generatePin();
        this.balance = 0L;
    }

    public Card(int id, String number, String pin, long balance) {
        this.id = id;
        this.number = number;
        this.pin = pin;
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public String getPin() {
        return pin;
    }

    public long getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

}
