package user;
import java.util.ArrayList;
import java.util.List;
public class User extends Person{
    private String email;
    private String password;
    private List<String> friends;

    public User(String[] userData) {
        setEmail(userData[0]);
        setPassword(userData[1]);
        this.friends = new ArrayList<>();

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

    public List<String> getFriends() {
        return friends;
    }

    public void addFriend(String friendUsername) {
        friends.add(friendUsername);
    }

    public void removeFriend(String friendUsername) {
        friends.remove(friendUsername);
    }
}
