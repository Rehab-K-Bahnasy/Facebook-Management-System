package user;

import org.json.simple.JSONObject;
import java.util.Random;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class User extends Person {
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
    public JSONObject toJsonObject() {
        Map<String, Object> userMapping = new HashMap<>();
        userMapping.put("username", username);
        userMapping.put("email", email);
        userMapping.put("password", password);
        userMapping.put("first name", firstName);
        userMapping.put("last name", lastName);
        userMapping.put("phone number", phoneNumber);
        userMapping.put("birthdate", birthDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        userMapping.put("gender", gender);

        return new JSONObject(userMapping);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                '}';
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