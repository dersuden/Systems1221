package org.example.systems1221;

import java.sql.*;

public class CaloriesInfo {

    public void caloriesInfo(int id) {
        String url = "jdbc:postgresql://localhost:5432/Weight_loss";
        String user = "Arthur";
        String password = "123";
        String getInfo = "SELECT users.age, users.weight, users.height, users.goal FROM users WHERE id = ?";
        try {
            System.out.println("Высчитываем калории...");
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(getInfo);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userAge = resultSet.getInt("age");
                double userWeight = resultSet.getDouble("weight");
                double userHeight = resultSet.getDouble("height");
                String userGoal = resultSet.getString("goal");
                double basicalMetabolic = (10 * userWeight) + (6.25 * userHeight) - (5 * userAge) + 5;
                double bulk = basicalMetabolic + 200;
                double cut = basicalMetabolic - 200;
                if (userGoal.equals("Поддержание"))

                    System.out.println("Количесвто калорий для поддержания веса: " + basicalMetabolic);
                if (userGoal.equals("Набор массы"))

                    System.out.println("Количесвто калорий для набора веса: " + bulk);
                if (userGoal.equals("Похудение"))

                    System.out.println("Количесвто калорий для похудения: " + cut);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}