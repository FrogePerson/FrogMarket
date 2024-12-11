package Krab.Base.BaseConnection;

import Krab.Base.BaseConnection.ConnectionToFrogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public static void deleteFrogByName(String name) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        
        try {
            connection = ConnectionToFrogs.connect();
            String sql = "DELETE FROM frogs WHERE name = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            int rowsAffected = preparedStatement.executeUpdate();
            
            System.out.println(rowsAffected + " row(s) deleted.");
        } catch (SQLException e) {
            System.err.println("Deletion failed: " + e.getMessage());
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
