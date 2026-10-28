package dao;

import database.DatabaseConnection;
import model.ProgramResponsible;
import model.StudyProgram;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudyProgramDAO {

    public void addStudyProgram(StudyProgram studyProgram) {

        String sql = "INSERT INTO StudyProgram(programName, programResponsibleName) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, studyProgram.getProgramName());
            statement.setString(2, studyProgram.getProgramResponsible().getName());

            statement.executeUpdate();

            System.out.println("StudyProgram lagret!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public StudyProgram getStudyProgramById(int id) {

        String sql = "SELECT * FROM StudyProgram WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                ProgramResponsible responsible =
                        new ProgramResponsible(
                                0,
                                resultSet.getString("programResponsibleName"),
                                ""
                        );

                return new StudyProgram(
                        resultSet.getInt("id"),
                        resultSet.getString("programName"),
                        responsible
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
