package dao;

import database.DatabaseConnection;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {

    // Legg til student
    public void addStudent(Student student) {

        String sql = "INSERT INTO Student(name, email, studentNumber, studyProgramId) VALUES (?, ?, ?, ?)";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getStudentNumber());

            // Midlertidig bruker vi StudyProgram med ID = 1
            statement.setInt(4, 1);

            statement.executeUpdate();

            System.out.println("Student lagret!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hent student med ID
    public Student getStudentById(int id) {

        String sql = "SELECT * FROM Student WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("studentNumber"),
                        resultSet.getString("studyProgramId")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Hent student med navn
    public Student getStudentByName(String name) {

        String sql = "SELECT * FROM Student WHERE name = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, name);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("studentNumber"),
                        resultSet.getString("studyProgramId")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Hent alle studenter
    public ArrayList<Student> getAllStudents() {

        ArrayList<Student> students = new ArrayList<>();

        String sql = "SELECT * FROM Student";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("studentNumber"),
                        resultSet.getString("studyProgramId")
                );

                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return students;
    }

    // Oppdater student
    public void updateStudent(Student student) {

        String sql = "UPDATE Student SET name = ?, email = ?, studentNumber = ?, studyProgramId = ? WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getStudentNumber());
            statement.setInt(4, 1);
            statement.setInt(5, student.getId());

            statement.executeUpdate();

            System.out.println("Student oppdatert!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Slett student
    public void deleteStudent(int id) {

        String sql = "DELETE FROM Student WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Student slettet!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}