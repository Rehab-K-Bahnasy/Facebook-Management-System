package user;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 * The abstract class Person represents a person in the Welcome Login application.
 * It includes attributes such as first name, last name, birthdate, and gender.
 * This class is extended by the User class to represent a user in the application.
 *
 * @author SOC-IO
 * @version 1.0
 */
public abstract class Person implements Serializable {

    String first_name;
    String last_name;
    LocalDate birthdate;
    String gender;

    /**
     * Constructs a Person object using the provided person data.
     *
     * @param personData A map containing person data.
     */
    Person(Map personData) {
        setFirstName((String) personData.get("first name"));
        setLastName((String) personData.get("last name"));
        setBirthdate((String) personData.get("birthdate"));
        setGender((String) personData.get("gender"));
    }

    /**
     * Constructs a Person object using a User object.
     *
     * @param user A User object from which to extract data.
     */
    Person(User user) {
        setFirstName(user.getFirstName());
        setLastName(user.getLastName());
        setBirthdate(user.getBirthdate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        setGender(user.getGender());
    }

    /**
     * Retrieves the first name of the person.
     *
     * @return The first name of the person.
     */
    public String getFirstName() {
        return first_name;
    }

    /**
     * Sets the first name of the person.
     *
     * @param first_name The new first name for the person.
     */
    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    /**
     * Retrieves the last name of the person.
     *
     * @return The last name of the person.
     */
    public String getLastName() {
        return last_name;
    }

    /**
     * Sets the last name of the person.
     *
     * @param last_name The new last name for the person.
     */
    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    /**
     * Retrieves the full name of the person.
     *
     * @return The full name of the person.
     */
    public String getName() {
        return first_name + " " + last_name;
    }

    /**
     * Retrieves the gender of the person.
     *
     * @return The gender of the person.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender of the person.
     *
     * @param gender The new gender for the person.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * Sets the birthdate of the person using the provided date string.
     *
     * @param birthDate The date string representing the birthdate.
     */
    public void setBirthdate(String birthDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.birthdate = LocalDate.parse(birthDate, formatter);
    }

    /**
     * Retrieves the birthdate of the person.
     *
     * @return The birthdate of the person.
     */
    public LocalDate getBirthdate() {
        return birthdate;
    }
}
