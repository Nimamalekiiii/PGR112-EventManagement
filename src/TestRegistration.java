import dao.RegistrationDAO;
import model.ProgramResponsible;
import model.Registration;
import model.Student;
import model.StudyProgram;

import java.sql.Date;

public class TestRegistration {

    public static void main(String[] args) {

        Student student = new Student(
                1,
                "Nima Maleki",
                "nima@hk.no",
                "S12345",
                "Cybersikkerhet"
        );

        ProgramResponsible responsible = new ProgramResponsible(
                1,
                "Ahmed Hassan",
                "ahmed@hk.no"
        );

        StudyProgram program = new StudyProgram(
                1,
                "Cybersikkerhet",
                responsible
        );

        Registration registration = new Registration(
                1,
                student,
                program,
                new Date(System.currentTimeMillis())
        );

        RegistrationDAO dao = new RegistrationDAO();

        dao.addRegistration(registration);
    }
}