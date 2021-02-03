package banking.db;

import banking.model.Card;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CardDAO {
    private final DBConnection conn;

    public CardDAO(DBConnection conn) {
        this.conn = conn;
    }


    public Card getCard(String number, String pin) throws SQLException {
        String selectByNoAndPin = "SELECT * FROM card WHERE number=? AND pin=?";
        try (PreparedStatement statement = conn.getConn().prepareStatement(selectByNoAndPin)) {
            statement.setString(1, number);
            statement.setString(2, pin);
            ResultSet resultSet = statement.executeQuery();
            Card card = null;
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                long balance = resultSet.getLong("balance");
                card = new Card(id, number, pin, balance);
            }
            return card;
        }
    }

    public void save(Card card) {
        String insertCard = "INSERT INTO card (number,pin,balance) values (?,?,?);";
        try (PreparedStatement statement = conn.getConn().prepareStatement(insertCard)) {
            statement.setString(1, card.getNumber());
            statement.setString(2, card.getPin());
            statement.setBigDecimal(3, new BigDecimal(card.getBalance()));
            if (statement.executeUpdate() > 0) {
                System.out.printf("Your card has been created\nYour card number: \n%s\nYour card PIN: \n%s\n",
                        card.getNumber(), card.getPin());
            }
        } catch (SQLException ex) {
            System.out.println("Card not saved. Database error.");
            ex.printStackTrace();
        }
    }

    public boolean delete(Card card) throws SQLException {
        String removeById = "DELETE FROM card WHERE id=?;";
        try (PreparedStatement statement = conn.getConn().prepareStatement(removeById)) {
            statement.setInt(1, card.getId());
            int rowAffected = statement.executeUpdate();
            return rowAffected == 1;
        }
    }

    public long getBalance(Card card) throws SQLException {
        String getBalance = "SELECT balance FROM card WHERE id=?;";
        try (PreparedStatement statement = conn.getConn().prepareStatement(getBalance)) {
            statement.setInt(1, card.getId());
            ResultSet resultSet = statement.executeQuery();
            return resultSet.getLong(1);
        }
    }


    public boolean addIncome(Card card, long income) throws SQLException {
        String updBalance = "UPDATE card SET balance=balance + ? WHERE id=?;";
        try (PreparedStatement statement = conn.getConn().prepareStatement(updBalance)) {
            statement.setLong(1, income);
            statement.setInt(2, card.getId());
            int rowAffected = statement.executeUpdate();
            return rowAffected == 1;
        }
    }

    public boolean isCardNoExist(String number) throws SQLException {
        number = number.substring(0, number.length() - 1) + '%';
        String numbCount = "SELECT count(*) FROM card WHERE number LIKE ?;";
        try (PreparedStatement statement = conn.getConn().prepareStatement(numbCount)) {
            statement.setString(1, number);
            ResultSet resultSet = statement.executeQuery();
            int size = resultSet.getInt(1);
            return size > 0;
        }
    }


    public void moneyTransfer(Card from, String toNumber, long amount) {
        Connection conn = this.conn.getConn();
        String updateSenderBalance = "UPDATE card SET balance=balance - ? WHERE id=?;";
        String updateReceiverBalance = "UPDATE card SET balance=balance + ? WHERE number=?;";
        try (PreparedStatement ownerStm = conn.prepareStatement(updateSenderBalance);
             PreparedStatement receiverStm = conn.prepareStatement(updateReceiverBalance)) {
            conn.setAutoCommit(false);
            ownerStm.setLong(1, amount);
            ownerStm.setInt(2, from.getId());
            receiverStm.setLong(1, amount);
            receiverStm.setString(2, toNumber);

            int fromRowAffected = ownerStm.executeUpdate();
            int toRowAffected = receiverStm.executeUpdate();
            if (fromRowAffected == 1 && toRowAffected == 1) {
                conn.commit();
                System.out.println("Success!");
            }
        } catch (SQLException exception) {
            try {
                conn.rollback();
                System.out.println("Transaction aborted. Database error.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }


}
