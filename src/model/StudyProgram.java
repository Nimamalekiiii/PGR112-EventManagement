package model;

public class StudyProgram {

    private int id;
    private String programName;
    private ProgramResponsible programResponsible;

    public StudyProgram(int id, String programName, ProgramResponsible programResponsible) {
        this.id = id;
        this.programName = programName;
        this.programResponsible = programResponsible;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public ProgramResponsible getProgramResponsible() {
        return programResponsible;
    }

    public void setProgramResponsible(ProgramResponsible programResponsible) {
        this.programResponsible = programResponsible;
    }

    @Override
    public String toString() {
        return "Study Program: " + programName +
                ", Program Responsible: " + programResponsible.getName();
    }
}
