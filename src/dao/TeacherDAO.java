package dao;

import database.DatabaseConnection;
import model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TeacherDAO {

    public void addTeacher(Teacher teacher) {

        String sql = "INSERT INTO Teacher(name, email, employeeNumber) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teacher.getName());
            statement.setString(2, teacher.getEmail());
            statement.setString(3, teacher.getEmployeeNumber());

            statement.executeUpdate();

            System.out.println("Teacher lagret!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
