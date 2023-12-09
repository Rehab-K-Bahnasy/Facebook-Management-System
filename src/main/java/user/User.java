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

    public boolean hasPasswordMatch(String input_password) {
        return password.equals(input_password);
    }

    public boolean hasUsernameMatch(String input_username) {
        return username.equals(input_username);
    }

    public boolean hasEmailMatch(String input_email) {
        return email.equals(input_email);
    }

    public boolean hasPhoneNumberMatch(String input_phone_number) {
        return phone_number.equals(input_phone_number);
    }

    public boolean hasMatchingIdentity(String input_identifier) {
        return hasUsernameMatch(input_identifier) ||
                hasEmailMatch(input_identifier) ||
                hasPhoneNumberMatch(input_identifier);
    }

    public boolean hasMatchCredentials(String input_identifier, String input_password) {
        return hasMatchingIdentity(input_identifier) && hasPasswordMatch(input_password);
    }
    @Override
    public String toString() {
        return "first name = " + first_name +
                ", last name = " + last_name +
                ", username=" + username +
                ", email=" + email +
                ", password=" + password +
                ", phone number=" + phone_number +
                ", birthdate = " + birthdate +
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