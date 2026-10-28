package model;

public class ProgramResponsible extends Person {

    private String studyProgram;

    public ProgramResponsible(int id, String name, String studyProgram) {
        super(id, name);
        this.studyProgram = studyProgram;
    }

    public String getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(String studyProgram) {
        this.studyProgram = studyProgram;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Responsible for: " + studyProgram;
    }
}
