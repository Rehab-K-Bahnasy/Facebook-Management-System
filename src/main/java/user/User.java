package user;

import Friend.Friend;
import conversation.Message;

import java.io.Serializable;
import java.util.*;

import Post.*;

public class User extends Person implements Serializable {
    private String username;
    private String email;
    private String phone_number;
    private String password;
    private List<Friend> allFriends;

    public ArrayList<Message> sent_messages;
    public ArrayList<Message> recieved_message;



    private ArrayList<Post> posts;
    private ArrayList<Post> feed;

    public User(Map userData) {
        super(userData);
        setUsername((String) userData.get("username"));
        setEmail((String) userData.get("email"));
        setPhoneNumber((String) userData.get("phone number"));
        setPassword((String) userData.get("password"));
        allFriends = new ArrayList<>();
        posts = new ArrayList<>();
        feed = new ArrayList<>();
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

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
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

    public List<Friend> getAllFriends() {
        return Collections.unmodifiableList(allFriends);
    }

    public boolean hasSimilarities(String input) {
        boolean name_similarity = first_name.contains(input) || last_name.contains(input);
        boolean username_similarity = username.contains(input);
        return name_similarity || username_similarity;
    }

    public boolean hasMatchCredentials(String input_identifier, String input_password) {
        return hasMatchingIdentity(input_identifier) && hasPasswordMatch(input_password);
    }

    public boolean isFriendWith(String username) {
        for (var friend : allFriends) {
            if(friend.hasMatchingIdentity(username)) {
                return true;
            }
        }
        return false;
    }

    public void createPost(Post post) {
        posts.add(post);
        for (var friend : allFriends) {
            friend.updateFeed(post);
        }
    }

    public void updateFeed(Post post) {
        feed.add(post);
    }

    public ArrayList<Post> getFeed() {
        return feed;
    }
    public ArrayList<Post> getPosts() {
        return posts;
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