package user;

import Friend.Friend;
import conversation.Message;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import Post.*;
import dataManager.DataManager;

public class User extends Person implements Serializable {
    private String username;
    private String email;
    private String phone_number;
    private String password;
    private ArrayList<Friend> allFriends;

    private static ArrayList<Message> sent_messages;
    private static ArrayList<Message> received_message;

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

    public User(User user)
    {
        super(user);
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPhoneNumber(user.getPhoneNumber());
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

    public ArrayList<Friend> getAllFriends() {
        return new ArrayList<>(allFriends);
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
            if (friend.hasMatchingIdentity(username)) {
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
    public void addFriend(User user, String type) {
        System.out.println(user.toString());
        allFriends.add(new Friend(user));
    }

    public void removeFriend(User user, Friend username) {
        user.getAllFriends().remove(username);
    }

    public void restrictFriend( Friend username) {
        username.setFriendshipType(Friend.FriendshipType.RESTRICTED);
    }

    public void blockFriend( Friend username) {
        username.getAllFriends().remove(username);
        username.setFriendshipType(Friend.FriendshipType.BLOCKED);
    }

    public void unrestrictFriend(Friend username) {
        username.setFriendshipType(Friend.FriendshipType.ALL);
    }

    public void unblockFriend(User user,Friend username) {
        user.getAllFriends().add(username);
        username.setFriendshipType(Friend.FriendshipType.ALL);
    }
    public void closeFriend(Friend username){
        username.setFriendshipType(Friend.FriendshipType.CLOSE);
    }
    public Friend getFriend(String username){
        for (var friend : allFriends) {
            if (friend.hasMatchingIdentity(username)) {
                return friend;
            }
        }
        return null;
    }
    public Map<Object, Object> changeUserintoMap(User user){
        Map <Object, Object> user_map = new HashMap<>();
        user_map.put("email",getEmail());
        user_map.put("username",getUsername());
        user_map.put("first name",getFirstName());
        user_map.put("last name",getLastName());
        user_map.put("gender",getGender());
        user_map.put("birthdate", getBirthdate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        user_map.put("phone number",getPhoneNumber());
        return user_map;
    }
    public static void updateSentMessages(String username, Message message){
        DataManager.getCurrentUser().getSent_messages().add(message);
    }
    public static void updateReceivedMessages(String username,Message message){
        DataManager.retrieveUser(username).getReceived_message().add(message);
    }

    public ArrayList<Message> getReceived_message() {
        return received_message;
    }
    public ArrayList<Message> getSent_messages(){
        return sent_messages;
    }
}