import dao.GuestDAO;
import model.Guest;

public class TestGuest {

    public static void main(String[] args) {

        Guest guest = new Guest(
                1,
                "Mohammed Ali",
                "mohammed@gmail.com",
                "OsloMet",
                1
        );

        GuestDAO dao = new GuestDAO();

        dao.addGuest(guest);
    }
}