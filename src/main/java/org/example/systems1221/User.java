package org.example.systems1221;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class User {
    public void addUser(int id, String name, String email, int age, int weight, int height, String goal) {
        String url = "jdbc:postgresql://localhost:5432/Weight_loss";
        String user = "Arthur";
        String password = "123";
        String sql = "INSERT INTO users VALUES (?,?,?,?,?,?,?)";
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
            System.out.println("Пользователь добавлен!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}