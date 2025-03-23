package org.example.systems1221;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddFood {
    public void addFood(int id, String name, int calories, double proteins, double fats, double carbohydrates) {
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
}