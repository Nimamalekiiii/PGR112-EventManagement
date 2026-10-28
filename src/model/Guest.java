package model;

public class Guest extends Person {

    private String email;
    private String organization;
    private int registrationId;

    public Guest(int id, String name, String email, String organization, int registrationId) {
        super(id, name);
        this.email = email;
        this.organization = organization;
        this.registrationId = registrationId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(int registrationId) {
        this.registrationId = registrationId;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Email: " + email +
                ", Organization: " + organization +
                ", Registration ID: " + registrationId;
    }
}