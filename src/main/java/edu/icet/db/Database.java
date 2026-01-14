package edu.icet.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static Connection connection;

    private Database() {
    }

    public static Connection getInstance() {
        try {
            return connection == null ? DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "PasinduDev678") : connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
