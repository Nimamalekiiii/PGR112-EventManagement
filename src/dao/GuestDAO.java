package dao;

import database.DatabaseConnection;
import model.Guest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GuestDAO {

    public void addGuest(Guest guest) {

        String sql = "INSERT INTO Guest(name, email, organization, registrationId) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, guest.getName());
            statement.setString(2, guest.getEmail());
            statement.setString(3, guest.getOrganization());
            statement.setInt(4, guest.getRegistrationId());

            statement.executeUpdate();

            System.out.println("Guest lagret!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Guest> getGuestsByRegistrationId(int registrationId) {

        ArrayList<Guest> guests = new ArrayList<>();

        String sql = "SELECT * FROM Guest WHERE registrationId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, registrationId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Guest guest = new Guest(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("organization"),
                        resultSet.getInt("registrationId")
                );

                guests.add(guest);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return guests;
    }

    public void updateGuest(Guest guest) {

        String sql = "UPDATE Guest SET name = ?, email = ?, organization = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, guest.getName());
            statement.setString(2, guest.getEmail());
            statement.setString(3, guest.getOrganization());
            statement.setInt(4, guest.getId());

            statement.executeUpdate();

            System.out.println("Guest oppdatert!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteGuestsByRegistrationId(int registrationId) {

        String sql = "DELETE FROM Guest WHERE registrationId = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, registrationId);

            statement.executeUpdate();

            System.out.println("Guests deleted!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}