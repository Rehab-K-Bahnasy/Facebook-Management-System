package user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected LocalDate birthDate;
    protected String gender;


    Person(Map personData) {
        setFirstName((String) personData.get("first name"));
        setLastName((String) personData.get("last name"));
        setPhoneNumber((String) personData.get("phone number"));
        setBirthDate((String) personData.get("birthdate"));
        setGender((String) personData.get("gender"));
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.birthDate = LocalDate.parse(birthDate, formatter);
    }
}
