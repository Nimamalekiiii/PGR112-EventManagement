package model;

import java.sql.Date;
import java.util.ArrayList;

public class Registration {

    private int id;
    private Student student;
    private StudyProgram studyProgram;
    private Date registrationDate;
    private ArrayList<Guest> guests;

    public Registration(int id, Student student, StudyProgram studyProgram, Date registrationDate) {
        this.id = id;
        this.student = student;
        this.studyProgram = studyProgram;
        this.registrationDate = registrationDate;
        this.guests = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void addGuest(Guest guest) {
        if (guests.size() < 4) {
            guests.add(guest);
        } else {
            System.out.println("Maximum of 4 guests allowed.");
        }
    }

    public void removeGuest(Guest guest) {
        guests.remove(guest);
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", student=" + student +
                ", studyProgram=" + studyProgram +
                ", registrationDate=" + registrationDate +
                ", guests=" + guests +
                '}';
    }
}