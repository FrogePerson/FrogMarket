package Krab.Base.BaseConnection;

import Krab.Base.BaseConnection.ConnectionToFrogs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import Krab.Base.GLOBAL;

public class AlterPassword {

    public static void alterPassword(String newPassword) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionToFrogs.connect();
            String sql = "ALTER USER " + GLOBAL.USER + " WITH PASSWORD '" + newPassword + "'";;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Password for user '" + GLOBAL.USER + "' changed successfully.");
        } catch (SQLException e) {
            System.err.println("Failed to change password: " + e.getMessage());
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

