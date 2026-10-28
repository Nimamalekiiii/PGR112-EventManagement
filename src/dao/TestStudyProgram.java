import dao.StudyProgramDAO;
import model.ProgramResponsible;
import model.StudyProgram;

public class TestStudyProgram {

    public static void main(String[] args) {

        ProgramResponsible ansvarlig =
                new ProgramResponsible(1, "Ola Nordmann", "Cybersikkerhet");

        StudyProgram studieprogram =
                new StudyProgram(1, "Cybersikkerhet", ansvarlig);

        StudyProgramDAO dao = new StudyProgramDAO();

        dao.addStudyProgram(studieprogram);
    }
}