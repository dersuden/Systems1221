package org.example.systems1221;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CaloriesInfo {

    public static void caloriesInfo(int id) {
        String url = "jdbc:postgresql://localhost:5432/Weight_loss";
        String user = "Arthur";
        String password = "123";
        String getInfo = "SELECT users.age, users. weight, users.height, users.goal FROM users WHERE id = ?";
        int userAge = Integer.parseInt("SELECT users.age FROM users WHERE id = ?");
        double userWeight = Double.parseDouble("SELECT users.weight FROM users WHERE id = ?");
        double userHeight = Double.parseDouble("SELECT users.height FROM users WHERE id = ?");
        double userGoal = Double.parseDouble("SELECT users.goal FROM users WHERE id = ?");
        double basicalMetabolic = (10 * userWeight) + (6.25 * userHeight) - (5 * userAge) + 5;
        double bulk = basicalMetabolic + 200;
        double cut = basicalMetabolic - 200;
        try {
            System.out.println("Высчитываем калории...");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(getInfo);
            connection.prepareStatement(getInfo);
            statement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}