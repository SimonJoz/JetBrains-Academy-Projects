package banking.db;

import banking.model.Card;

import java.math.BigDecimal;
import java.sql.*;

import static java.lang.String.format;

public class DBConnection {
    private Connection conn;

    public void createCardTable() throws SQLException {
        try (Statement statement = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS card (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " number TEXT, pin TEXT, balance INTEGER DEFAULT 0);";
            statement.execute(sql);
        }

    }

    public void createConn(String databaseName) throws SQLException {
        final String URL = format("jdbc:sqlite:%s", databaseName);
        conn = DriverManager.getConnection(URL);
    }

    public void saveToDB(Card card) {
        String sql = "INSERT INTO card values (?,?,?);";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, card.getNumber());
            statement.setString(2, card.getPin());
            statement.setBigDecimal(3, new BigDecimal(card.getBalance()));
            statement.execute();
        } catch (SQLException ex) {
            System.out.println("Card not saved. Database error.");
            ex.printStackTrace();
        }
    }

    public void closeConn() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }


}
