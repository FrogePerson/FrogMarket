package Krab.Base.BaseConnection;


import Krab.Base.BaseConnection.ConnectionToFrogs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Alter {

    
    public static void addColumn(String columnName, String columnType) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionToFrogs.connect();
            String sql = "ALTER TABLE frogs ADD COLUMN " + columnName + " " + columnType;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Column '" + columnName + "' of type '" + columnType + "' added successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to add column: " + e.getMessage());
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    
    public static void delleteColumn(String columnName) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionToFrogs.connect();
            String sql = "ALTER TABLE frogs DROP COLUMN " + columnName;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Column '" + columnName + "' removed successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to remove column: " + e.getMessage());
        } finally {
            closeResources(preparedStatement, connection);
        }
    }

    
    private static void closeResources(PreparedStatement preparedStatement, Connection connection) {
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


