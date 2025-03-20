package org.example.systems1221;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddUser {
    public static void addUser(int id, String name, String email, int age, int weight, int height, String goal) {
        String url = "jdbc:postgresql://localhost:5432/Weight_loss";
        String user = "Arthur";
        String password = "123";
        String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";
        double basicalMetabolic = (10 * weight) + (6.25 * height) - (5 * age) + 5;
        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setInt(4, age);
            preparedStatement.setInt(5, weight);
            preparedStatement.setInt(6, height);
            preparedStatement.setString(7, goal);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
