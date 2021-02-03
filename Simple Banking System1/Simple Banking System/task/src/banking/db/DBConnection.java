package banking.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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

    public void closeConn() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public Connection getConn() {
        return conn;
    }
}
