import database.DatabaseConnection;
import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            if (connection != null) {
                System.out.println("Koblet til databasen!");
            }

            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}