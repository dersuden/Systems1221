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

                    System.out.println("Количество калорий для поддержания веса: " + basicalMetabolic);
                if (userGoal.equals("Набор массы"))

                    System.out.println("Количество калорий для набора веса: " + bulk);
                if (userGoal.equals("Похудение"))

                    System.out.println("Количество калорий для похудения: " + cut);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void caloriesOfTheDay(Date date, int hundredGrams) {
        String url = "jdbc:postgresql://localhost:5432/Weight_loss";
        String user = "Arthur";
        String password = "123";
        String sql = "SELECT "
                + "d.date, "
                + "d.day, "
                + "COUNT(d.meal) AS meals_count, "
                + "SUM(f.calories_on_portion) AS total_calories, "
                + "SUM(f.proteins) AS total_proteins, "
                + "SUM(f.fats) AS total_fats, "
                + "SUM(f.carbohydrates) AS total_carbohydrates , "
                + "STRING_AGG(d.meal, ', ') AS meals_list "
                + "FROM days_of_week d "
                + "JOIN food f ON d.meal = f.name "
                + "WHERE d.date = ? "
                + "GROUP BY d.date, d.day";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            {
                preparedStatement.setDate(1, new Date(date.getTime()));

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        String meals = resultSet.getString("meals_list");
                        int calories = resultSet.getInt("total_calories");
                        double proteins = resultSet.getDouble("total_proteins");
                        double fats = resultSet.getDouble("total_fats");
                        double carbohydrates = resultSet.getDouble("total_carbohydrates");

                        System.out.println("Съеденное за " + resultSet.getDate("date") + ":");
                        System.out.println("Блюда: " + meals);
                        System.out.println("Общее количество калорий: " + calories * hundredGrams + "г.");
                        System.out.println("Белки: " + proteins * hundredGrams + "г.");
                        System.out.println("Жиры: " + fats * hundredGrams + "г.");
                        System.out.println("Углеводы: " + carbohydrates * hundredGrams + "г.");
                    } else {
                        System.out.println("Нет данных за указанную дату");
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}