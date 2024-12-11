package Krab.Base.BaseConnection;
import Krab.Base.BaseConnection.ConnectionToFrogs;
import Krab.Base.Models.Frog;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Select {
    public static List<Frog> getFrogs() {
        List<Frog> frogs = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionToFrogs.connect();
            String sql = "SELECT id, name, cost FROM frogs";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int cost = resultSet.getInt("cost");
                Frog frog = new Frog(id, name, cost);
                frogs.add(frog);
            }
        } catch (SQLException e) {
            System.err.println("Retrieval failed: " + e.getMessage());
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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

        return frogs;
    }
}

