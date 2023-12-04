package user;

import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class User extends Person{
    private String username;
    private String email;
    private String username;
    private String password;
    public User(String[] userData) {
        //sending userData from 4th (3rd index) element to person constructor
        //firstName lastName phoneNumber birthDate gender
        super(Arrays.copyOfRange(userData,3,userData.length));
        setUsername(userData[0]);
        setEmail(userData[1]);
        setPassword(userData[2]);
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
    public JSONObject toJsonObject()
    {
        Map<String,String> userMapping = new HashMap<>();
        userMapping.put("username",username);
        userMapping.put("email",email);
        userMapping.put("password",password);
        userMapping.put("first name",firstName);
        userMapping.put("last name",lastName);
        userMapping.put("phone number",phoneNumber);
        userMapping.put("birth date",birthDate.toString());
        userMapping.put("gender",gender);

        return new JSONObject(userMapping);
    }
}