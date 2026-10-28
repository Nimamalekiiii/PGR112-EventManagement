package model;

public class Teacher extends Person {

    private String email;
    private String employeeNumber;

    public Teacher(int id, String name, String email, String employeeNumber) {
        super(id, name);
        this.email = email;
        this.employeeNumber = employeeNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Email: " + email +
                ", Employee Number: " + employeeNumber;
    }
}