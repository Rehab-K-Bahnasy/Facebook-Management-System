package user;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public abstract class Person implements Serializable {
    String first_name;
    String last_name;
    LocalDate birthdate;
    String gender;

    Person(Map personData) {
        setFirstName((String) personData.get("first name"));
        setLastName((String) personData.get("last name"));
        setBirthdate((String) personData.get("birthdate"));
        setGender((String) personData.get("gender"));
    }

    public String getFirstName() {
        return first_name;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.birthdate = LocalDate.parse(birthDate, formatter);
    }
}
