import dao.GuestDAO;
import dao.RegistrationDAO;
import dao.StudentDAO;
import dao.StudyProgramDAO;

import model.Guest;
import model.ProgramResponsible;
import model.Registration;
import model.Student;
import model.StudyProgram;

import java.sql.Date;
import java.util.Scanner;
import java.util.ArrayList;

    public class Main {

        public static void main(String[] args) {

            Scanner scanner = new Scanner(System.in);
            StudentDAO studentDAO = new StudentDAO();
            RegistrationDAO registrationDAO = new RegistrationDAO();
            GuestDAO guestDAO = new GuestDAO();
            StudyProgramDAO studyProgramDAO = new StudyProgramDAO();

            while (true) {

                System.out.println("\n==================================");
                System.out.println("WELCOME TO EVENT MANAGEMENT");
                System.out.println("==================================");
                System.out.println("1. Sign in");
                System.out.println("2. See overall ceremony program");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {

                    case 1:

                        System.out.print("Enter your name: ");
                        String name = scanner.nextLine();

                        Student student = studentDAO.getStudentByName(name);

                        if (student != null) {

                            boolean loggedIn = true;

                            while (loggedIn) {

                                System.out.println("\n==================================");
                                System.out.println("Welcome, " + student.getName() + "!");
                                System.out.println("==================================");
                                System.out.println("1. Register for event");
                                System.out.println("2. Edit registration");
                                System.out.println("3. Delete registration");
                                System.out.println("4. See all participants");
                                System.out.println("5. See participants from my program");
                                System.out.println("6. Search participant");
                                System.out.println("7. See ceremony program");
                                System.out.println("8. Logout");
                                System.out.print("Choose an option: ");

                                int studentChoice = scanner.nextInt();
                                scanner.nextLine();

                                switch (studentChoice) {

                                    case 1:

                                        System.out.print("How many guests (0-4): ");
                                        int guestCount = scanner.nextInt();
                                        scanner.nextLine();

                                        if (guestCount < 0 || guestCount > 4) {
                                            System.out.println("Maximum 4 guests allowed.");
                                            break;
                                        }

                                        ProgramResponsible responsible =
                                                new ProgramResponsible(1, "Unknown", "");

                                        StudyProgram studyProgram =
                                                studyProgramDAO.getStudyProgramById(
                                                        Integer.parseInt(student.getStudyProgram())
                                                );

                                        if (studyProgram == null) {
                                            studyProgram = new StudyProgram(
                                                    Integer.parseInt(student.getStudyProgram()),
                                                    "Unknown",
                                                    responsible
                                            );
                                        }

                                        Registration registration =
                                                new Registration(
                                                        0,
                                                        student,
                                                        studyProgram,
                                                        new Date(System.currentTimeMillis())
                                                );

                                        int registrationId =
                                                registrationDAO.addRegistration(registration);

                                        for (int i = 1; i <= guestCount; i++) {

                                            System.out.print("Guest " + i + " name: ");
                                            String guestName = scanner.nextLine();

                                            System.out.print("Guest " + i + " email: ");
                                            String guestEmail = scanner.nextLine();

                                            System.out.print("Guest " + i + " organization: ");
                                            String organization = scanner.nextLine();

                                            Guest guest =
                                                    new Guest(
                                                            0,
                                                            guestName,
                                                            guestEmail,
                                                            organization,
                                                            registrationId
                                                    );

                                            guestDAO.addGuest(guest);
                                        }

                                        System.out.println("\nRegistration created successfully.");

                                        break;
                                    case 2:

                                        Registration existingRegistration =
                                                registrationDAO.getRegistrationByStudentId(student.getId());

                                        if (existingRegistration == null) {
                                            System.out.println("\nNo registration found.");
                                            break;
                                        }

                                        System.out.println("\nYour registration ID: " + existingRegistration.getId());

                                        System.out.print("New registration date (yyyy-mm-dd): ");
                                        String newDate = scanner.nextLine();

                                        existingRegistration.setRegistrationDate(Date.valueOf(newDate));

                                        registrationDAO.updateRegistration(existingRegistration);

                                        System.out.println("\nRegistration updated.");

                                        break;

                                    case 3:

                                        Registration registrationToDelete =
                                                registrationDAO.getRegistrationByStudentId(student.getId());

                                        if (registrationToDelete == null) {
                                            System.out.println("\nNo registration found.");
                                            break;
                                        }

                                        guestDAO.deleteGuestsByRegistrationId(registrationToDelete.getId());

                                        registrationDAO.deleteRegistration(registrationToDelete.getId());

                                        System.out.println("\nRegistration deleted.");

                                        break;

                                    case 4:

                                        ArrayList<Registration> registrations = registrationDAO.getAllRegistrations();

                                        if (registrations.isEmpty()) {
                                            System.out.println("\nNo participants found.");
                                            break;
                                        }

                                        System.out.println("\n===== ALL PARTICIPANTS =====");

                                        for (Registration r : registrations) {
                                            System.out.println(
                                                    "Registration ID: " + r.getId() +
                                                            " | Student ID: " + r.getStudent().getId() +
                                                            " | Study Program ID: " + r.getStudyProgram().getId() +
                                                            " | Date: " + r.getRegistrationDate()
                                            );
                                        }

                                        break;

                                    case 5:

                                        ArrayList<Registration> programRegistrations =
                                                registrationDAO.getRegistrationsByStudyProgram(
                                                        Integer.parseInt(student.getStudyProgram())
                                                );

                                        if (programRegistrations.isEmpty()) {
                                            System.out.println("\nNo participants found from your study program.");
                                            break;
                                        }

                                        System.out.println("\n===== PARTICIPANTS FROM MY STUDY PROGRAM =====");

                                        for (Registration r : programRegistrations) {
                                            System.out.println(
                                                    "Registration ID: " + r.getId() +
                                                            " | Student ID: " + r.getStudent().getId() +
                                                            " | Study Program ID: " + r.getStudyProgram().getId() +
                                                            " | Date: " + r.getRegistrationDate()
                                            );
                                        }

                                        break;

                                    case 6:

                                        System.out.print("Enter student ID: ");
                                        int searchId = scanner.nextInt();
                                        scanner.nextLine();

                                        Registration foundRegistration =
                                                registrationDAO.searchRegistrationByStudentId(searchId);

                                        if (foundRegistration == null) {
                                            System.out.println("\nParticipant not found.");
                                            break;
                                        }

                                        System.out.println("\n===== PARTICIPANT FOUND =====");
                                        System.out.println("Registration ID: " + foundRegistration.getId());
                                        System.out.println("Student ID: " + foundRegistration.getStudent().getId());
                                        System.out.println("Study Program ID: " + foundRegistration.getStudyProgram().getId());
                                        System.out.println("Registration Date: " + foundRegistration.getRegistrationDate());

                                        break;

                                    case 7:

                                        System.out.println("\n==================================");
                                        System.out.println("CEREMONY PROGRAM");
                                        System.out.println("==================================");
                                        System.out.println("10:00 - Welcome");
                                        System.out.println("10:15 - Opening speech");
                                        System.out.println("10:30 - Graduation ceremony");
                                        System.out.println("11:30 - Group photo");
                                        System.out.println("12:00 - Lunch");
                                        System.out.println("13:00 - End of ceremony");

                                        break;

                                    case 8:
                                        loggedIn = false;
                                        System.out.println("\nLogged out.");
                                        break;

                                    default:
                                        System.out.println("\nInvalid choice.");
                                }
                            }

                        } else {

                            System.out.println("\nStudent not found.");
                        }

                        break;

                    case 2:

                        System.out.println("\n==================================");
                        System.out.println("OVERALL CEREMONY PROGRAM");
                        System.out.println("==================================");
                        System.out.println("10:00 - Welcome");
                        System.out.println("10:15 - Opening speech");
                        System.out.println("10:30 - Graduation ceremony");
                        System.out.println("11:30 - Group photo");
                        System.out.println("12:00 - Lunch");
                        System.out.println("13:00 - End of ceremony");

                        break;
                    case 3:

                        System.out.println("\nGoodbye!");
                        scanner.close();
                        return;

                    default:

                        System.out.println("\nInvalid choice.");
                }
            }
        }
    }