package user;

public class User extends Person{
    private String email;
    private String username;
    private String password;
    public User(String[] userData) {
        setEmail(userData[0]);
        setPassword(userData[1]);

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
}