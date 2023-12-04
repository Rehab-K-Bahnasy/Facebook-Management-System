package user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Person {
    protected String firstName;
    protected String lastName;
    protected String gender;
    protected LocalDate birthDate;
    Person()
    {

    }
    Person(String[] personData) {
        setFirstName(personData[0]);
        setLastName(personData[1]);
        setLastName(personData[2]);

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
        this.birthDate = LocalDate.parse(birthDate,formatter);
    }
}
