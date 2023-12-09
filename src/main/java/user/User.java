package user;


import java.io.Serializable;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User extends Person implements Serializable {
    private String username;
    Random random = new Random();

    final short key = (short) random.nextInt(Short.MAX_VALUE + 1);
    private String email;
    private String password;

    public User(Map userData) {
        super(userData);
        setUsername((String) userData.get("username"));
        setEmail((String) userData.get("email"));
        setPassword((String) userData.get("password"));
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPasswordMatch(String inputPassword) {
        return password.equals(inputPassword);
    }

    public boolean hasUsernameMatch(String inputUsername) {
        return username.equals(inputUsername);
    }

    public boolean hasEmailMatch(String inputEmail) {
        return username.equals(inputEmail);
    }

    public boolean hasPhoneNumberMatch(String inputPhoneNumber) {
        return username.equals(inputPhoneNumber);
    }

    public boolean hasMatchingIdentity(String identifier) {
        return hasUsernameMatch(identifier) ||
                hasEmailMatch(identifier) ||
                hasPhoneNumberMatch(identifier);
    }

    @Override
    public String toString() {
        return "first name = " + firstName +
                ", last name = " + lastName +
                ", username=" + username +
                ", email=" + email +
                ", password=" + password +
                ", phone number=" + phoneNumber +
                ", birthdate = " + birthDate +
                ", gender=" + gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        //checking username is enough, as a username, email and phone number only assigned once
        return Objects.equals(username, user.username);
    }
}