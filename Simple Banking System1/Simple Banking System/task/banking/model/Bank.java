package banking.model;

import java.util.ArrayList;
import java.util.List;

public class Bank {
    private final List<Card> cardsList;

    public Bank() {
        this.cardsList = new ArrayList<>();
    }

    public List<Card> getCardsList() {
        return cardsList;
    }
}
