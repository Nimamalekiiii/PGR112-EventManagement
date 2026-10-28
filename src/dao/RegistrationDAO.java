package dao;

import database.DatabaseConnection;
import model.ProgramResponsible;
import model.Registration;
import model.Student;
import model.StudyProgram;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationDAO {

    // Legg til registrering
    public int addRegistration(Registration registration) {

        String sql = "INSERT INTO Registration (studentId, studyProgramId, registrationDate) VALUES (?, ?, ?)";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {

            statement.setInt(1, registration.getStudent().getId());
            statement.setInt(2, registration.getStudyProgram().getId());
            statement.setDate(3, registration.getRegistrationDate());

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {

                int registrationId = generatedKeys.getInt(1);

                System.out.println("Registration lagret!");

                return registrationId;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }

    // Hent registrering med ID
    public Registration getRegistrationById(int id) {

        String sql = "SELECT * FROM Registration WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("studentId"),
                        "Unknown",
                        "",
                        "",
                        ""
                );

                ProgramResponsible responsible =
                        new ProgramResponsible(1, "Unknown", "");

                StudyProgram program =
                        new StudyProgram(
                                resultSet.getInt("studyProgramId"),
                                "Unknown",
                                responsible
                        );

                return new Registration(
                        resultSet.getInt("id"),
                        student,
                        program,
                        resultSet.getDate("registrationDate")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Hent alle registreringer
    public ArrayList<Registration> getAllRegistrations() {

        ArrayList<Registration> registrations = new ArrayList<>();

        String sql = "SELECT * FROM Registration";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("studentId"),
                        "Unknown",
                        "",
                        "",
                        ""
                );

                ProgramResponsible responsible =
                        new ProgramResponsible(1, "Unknown", "");

                StudyProgram program =
                        new StudyProgram(
                                resultSet.getInt("studyProgramId"),
                                "Unknown",
                                responsible
                        );

                Registration registration = new Registration(
                        resultSet.getInt("id"),
                        student,
                        program,
                        resultSet.getDate("registrationDate")
                );

                registrations.add(registration);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registrations;
    }
    // Hent registrering for en student
    public Registration getRegistrationByStudentId(int studentId) {

        String sql = "SELECT * FROM Registration WHERE studentId = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("studentId"),
                        "Unknown",
                        "",
                        "",
                        String.valueOf(resultSet.getInt("studyProgramId"))
                );

                ProgramResponsible responsible =
                        new ProgramResponsible(1, "Unknown", "");

                StudyProgram program =
                        new StudyProgram(
                                resultSet.getInt("studyProgramId"),
                                "Unknown",
                                responsible
                        );

                return new Registration(
                        resultSet.getInt("id"),
                        student,
                        program,
                        resultSet.getDate("registrationDate")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // Oppdater registrering
    public void updateRegistration(Registration registration) {

        String sql = "UPDATE Registration SET studentId = ?, studyProgramId = ?, registrationDate = ? WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, registration.getStudent().getId());
            statement.setInt(2, registration.getStudyProgram().getId());
            statement.setDate(3, registration.getRegistrationDate());
            statement.setInt(4, registration.getId());

            statement.executeUpdate();

            System.out.println("Registration oppdatert!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Slett registrering
    public void deleteRegistration(int id) {

        String sql = "DELETE FROM Registration WHERE id = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, id);

            statement.executeUpdate();

            System.out.println("Registration slettet!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

public ArrayList<Registration> getRegistrationsByStudyProgram(int studyProgramId) {

    ArrayList<Registration> registrations = new ArrayList<>();

    String sql = "SELECT * FROM Registration WHERE studyProgramId = ?";

    try (
            Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)
    ) {

        statement.setInt(1, studyProgramId);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {

            Student student = new Student(
                    resultSet.getInt("studentId"),
                    "Unknown",
                    "",
                    "",
                    ""
            );

            ProgramResponsible responsible =
                    new ProgramResponsible(1, "Unknown", "");

            StudyProgram studyProgram =
                    new StudyProgram(
                            resultSet.getInt("studyProgramId"),
                            "Unknown",
                            responsible
                    );

            Registration registration = new Registration(
                    resultSet.getInt("id"),
                    student,
                    studyProgram,
                    resultSet.getDate("registrationDate")
            );

            registrations.add(registration);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return registrations;
  }
    public Registration searchRegistrationByStudentId(int studentId) {

        String sql = "SELECT * FROM Registration WHERE studentId = ?";

        try (
                Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("studentId"),
                        "Unknown",
                        "",
                        "",
                        ""
                );

                ProgramResponsible responsible =
                        new ProgramResponsible(1, "Unknown", "");

                StudyProgram studyProgram =
                        new StudyProgram(
                                resultSet.getInt("studyProgramId"),
                                "Unknown",
                                responsible
                        );

                return new Registration(
                        resultSet.getInt("id"),
                        student,
                        studyProgram,
                        resultSet.getDate("registrationDate")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}

