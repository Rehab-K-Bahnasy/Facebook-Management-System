package user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected LocalDate birthDate;
    protected String gender;
    Person(){

    }
    Person(String[] personData) {
        //firstName lastName phoneNumber birthDate gender
        setFirstName(personData[0]);
        setLastName(personData[1]);
        setPhoneNumber(personData[2]);
        setBirthDate(personData[3]);
        setGender(personData[4]);
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
