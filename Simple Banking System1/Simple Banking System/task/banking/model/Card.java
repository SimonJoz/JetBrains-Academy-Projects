package banking.model;

import banking.util.CardGenerator;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

public class Card {
    private int id;
    private final String number;
    private final String pin;
    private BigInteger balance;

    public Card() {
        this.id = CardGenerator.getId();
        this.number = CardGenerator.generateCardNo();
        this.pin = CardGenerator.generatePin();
        this.balance = BigInteger.ZERO;
    }

    public Card(String number, String pin, BigInteger balance) {
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

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(number, card.number) &&
                Objects.equals(pin, card.pin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, pin);
    }
}
