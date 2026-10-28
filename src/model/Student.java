package model;

public class Student extends Person {

    private String email;
    private String studentNumber;
    private String studyProgram;

    public Student(int id, String name, String email, String studentNumber, String studyProgram) {
        super(id, name);
        this.email = email;
        this.studentNumber = studentNumber;
        this.studyProgram = studyProgram;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
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
                ", Email: " + email +
                ", Student Number: " + studentNumber +
                ", Study Program: " + studyProgram;
    }
}