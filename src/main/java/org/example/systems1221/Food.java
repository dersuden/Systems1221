package org.example.systems1221;


import java.sql.*;


public class Food {
    public void addFoodIntoList(int id, String name, int calories, double proteins, double fats, double carbohydrates) {
        String url = "jdbc:postgresql://localhost:5432/Weight_loss";
        String user = "Arthur";
        String password = "123";
        String sql = "INSERT INTO food VALUES (?,?,?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, calories);
            preparedStatement.setDouble(4, proteins);
            preparedStatement.setDouble(5, fats);
            preparedStatement.setDouble(6, carbohydrates);
            preparedStatement.executeUpdate();
            System.out.println("Блюдо добавлено!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listOfDishes(int id) {
        String url = "jdbc:postgresql://localhost:5432/Weight_loss";
        String user = "Arthur";
        String password = "123";
        String sql = "SELECT * FROM food WHERE id = ?";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                int calories = resultSet.getInt("calories_on_portion");
                double proteins = resultSet.getDouble("proteins");
                double fats = resultSet.getDouble("fats");
                double carbohydrates = resultSet.getDouble("carbohydrates");
                System.out.println("Наименование блюда: " + name);
                System.out.println("Количество калорий на 100гр: " + calories);
                System.out.println("Количество протеинов на 100гр: " + proteins);
                System.out.println("Количество жиров на 100гр: " + fats);
                System.out.println("Количество углеводов на 100гр: " + carbohydrates);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addMeal(int id, Date date, String day, String meal) {
        String url = "jdbc:postgresql://localhost:5432/Weight_loss";
        String user = "Arthur";
        String password = "123";
        String sql = "INSERT INTO days_of_week VALUES (?,?,?,?)";
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setDate(2, date);
            preparedStatement.setString(3, day);
            preparedStatement.setString(4, meal);
            preparedStatement.executeUpdate();
            System.out.println("Приём Пищи добавлен!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void caloriesOfTheDay(Date date) {
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

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, new Date(date.getTime()));

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String meals = resultSet.getString("meals_list");
                    int calories = resultSet.getInt("total_calories");

                    System.out.println("Съеденное за " + resultSet.getDate("date") + ":");
                    System.out.println("Блюда: " + meals);
                    System.out.println("Общее количество калорий: " + calories);
                    System.out.println("Белки: " + resultSet.getDouble("total_proteins") + "г");
                    System.out.println("Жиры: " + resultSet.getDouble("total_fats") + "г");
                    System.out.println("Углеводы: " + resultSet.getDouble("total_carbohydrates") + "г");
                } else {
                    System.out.println("Нет данных за указанную дату");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}