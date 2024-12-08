package Krab.Base.BaseConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Krab.Base.GLOBAL;

public class ConnectionToFrogs {
    private static final String URL = GLOBAL.URL;
    private static final String USER = GLOBAL.USER;
    private static final String PASSWORD = GLOBAL.PASSWORD;

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        
        return connection;
    }
}

