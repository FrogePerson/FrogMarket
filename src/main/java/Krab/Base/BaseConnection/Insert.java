package Krab.Base.BaseConnection;

import Krab.Base.BaseConnection.ConnectionToFrogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class Insert {
    public static void addFrog(String name, int cost) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
        connection = ConnectionToFrogs.connect();
        String sql = "INSERT INTO frogs (name, cost) VALUES (?, ?)";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setInt(2, cost);

        int rowsAffected = preparedStatement.executeUpdate();
        System.out.println(rowsAffected + " row(s) inserted.");
    } catch (SQLException e) {
        System.err.println("Insertion failed: " + e.getMessage());
    } finally {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing resources: " + e.getMessage());
        }
    }
}

}
