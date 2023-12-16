package user;

import Friend.Friend;
import conversation.Message;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;

import Post.*;
import dataManager.DataManager;

/**
 * The User class represents a user in the Welcome Login application.
 * It extends the Person class and includes information such as username, email, phone number, password, and friends.
 * The class provides methods for managing posts, friends, and messages.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class User extends Person implements Serializable {
    private String username;
    private String email;
    private String phone_number;
    private String password;
    private ArrayList<Friend> allFriends;
    private static ArrayList<Message> sent_messages = new ArrayList<>();
    private static ArrayList<Message> received_message = new ArrayList<>();

    private ArrayList<Post> posts;
    private ArrayList<Post> feed;

    /**
     * Constructs a User object using the provided user data.
     *
     * @param userData A map containing user data.
     */
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

    /**
     * Constructs a User object using another User object.
     *
     * @param user Another User object to copy data from.
     */
    public User(User user) {
        super(user);
        setUsername(user.getUsername());
        setEmail(user.getEmail());
        setPhoneNumber(user.getPhoneNumber());
        allFriends = user.getAllFriends();
        posts = user.getPosts();
        feed = user.getFeed();
    }

    /**
     * Retrieves the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     *
     * @param username The new username for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The new email for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retrieves the phone number of the user.
     *
     * @return The phone number of the user.
     */
    public String getPhoneNumber() {
        return phone_number;
    }

    /**
     * Sets the phone number of the user.
     *
     * @param phone_number The new phone number for the user.
     */
    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Sets the password for the user.
     *
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Checks if the provided password matches the user's password.
     *
     * @param input_password The password to be checked.
     * @return True if the password matches, false otherwise.
     */
    public boolean hasPasswordMatch(String input_password) {
        return password.equals(input_password);
    }

    /**
     * Checks if the provided username matches the user's username.
     *
     * @param input_username The username to be checked.
     * @return True if the usernames match, false otherwise.
     */
    public boolean hasUsernameMatch(String input_username) {
        return username.equals(input_username);
    }

    /**
     * Checks if the provided email matches the user's email.
     *
     * @param input_email The email to be checked.
     * @return True if the emails match, false otherwise.
     */
    public boolean hasEmailMatch(String input_email) {
        return email.equals(input_email);
    }

    /**
     * Checks if the provided phone number matches the user's phone number.
     *
     * @param input_phone_number The phone number to be checked.
     * @return True if the phone numbers match, false otherwise.
     */
    public boolean hasPhoneNumberMatch(String input_phone_number) {
        return phone_number.equals(input_phone_number);
    }

    /**
     * Checks if the provided identifier matches any of the user's identity attributes (username, email, phone number).
     *
     * @param input_identifier The identifier to be checked.
     * @return True if the identifier matches, false otherwise.
     */
    public boolean hasMatchingIdentity(String input_identifier) {
        return hasUsernameMatch(input_identifier) ||
                hasEmailMatch(input_identifier) ||
                hasPhoneNumberMatch(input_identifier);
    }

    /**
     * Retrieves a copy of the list of all friends of the user.
     *
     * @return A copy of the list of all friends of the user.
     */
    public ArrayList<Friend> getAllFriends() {
        return new ArrayList<>(allFriends);
    }

    /**
     * Checks if the user's first name, last name, or username contains the provided input.
     *
     * @param input The input to be checked for similarities.
     * @return True if there are similarities, false otherwise.
     */
    public boolean hasSimilarities(String input) {
        boolean name_similarity = first_name.contains(input) || last_name.contains(input);
        boolean username_similarity = username.contains(input);
        return name_similarity || username_similarity;
    }

    /**
     * Checks if the provided identifier and password match the user's identity and password.
     *
     * @param input_identifier The identifier to be checked.
     * @param input_password   The password to be checked.
     * @return True if the identifier and password match, false otherwise.
     */
    public boolean hasMatchCredentials(String input_identifier, String input_password) {
        return hasMatchingIdentity(input_identifier) && hasPasswordMatch(input_password);
    }

    /**
     * Checks if the user is friends with the provided username.
     *
     * @param username The username to check for friendship.
     * @return True if the user is friends with the provided username, false otherwise.
     */
    public boolean isFriendWith(String username) {
        for (var friend : allFriends) {
            if (friend.hasMatchingIdentity(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates a new post for the user and updates the feeds of their friends.
     *
     * @param post The post to be created.
     */
    public void createPost(Post post) {
        posts.add(post);
        for (var friend : allFriends) {
            friend.updateFeed(post);
        }
    }

    /**
     * Updates the user's feed with a new post.
     *
     * @param post The post to be added to the user's feed.
     */
    public void updateFeed(Post post) {
        feed.add(post);
    }

    /**
     * Updates the user's feed with a list of posts.
     *
     * @param posts The list of posts to be added to the user's feed.
     */
    public void updateFeed(ArrayList<Post> posts) {
        this.feed.addAll(posts);
    }

    /**
     * Retrieves a copy of the user's feed.
     *
     * @return A copy of the user's feed.
     */
    public ArrayList<Post> getFeed() {
        return feed;
    }

    /**
     * Retrieves a copy of the user's posts.
     *
     * @return A copy of the user's posts.
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Adds a new friend for the user and updates the feed with the friend's posts.
     *
     * @param user The user to be added as a friend.
     */
    public void addFriend(User user) {
        var friend = new Friend(user);
        allFriends.add(friend);
        updateFeed(friend.getPosts());
    }

    /**
     * Removes a friend from the user's friend list.
     *
     * @param user     The user object representing the friend to be removed.
     * @param username The friend's username to be removed.
     */
    public void removeFriend(User user, Friend username) {
        user.getAllFriends().remove(username);
    }

    /**
     * Restricts the interaction level with a friend to "RESTRICTED."
     *
     * @param friend The friend's username to be restricted.
     */
    public void restrictFriend(Friend friend) {
        friend.setFriendshipType("RESTRICTED");
    }

    /**
     * Blocks a friend, removing them from the friend list.
     *
     * @param friend The friend's username to be blocked.
     */
    public void blockFriend(Friend friend) {
        friend.setFriendshipType("BLOCKED");
    }

    /**
     * Removes restriction from a friend, setting the interaction level to "ALL."
     *
     * @param friend The friend's username to be unrestricted.
     */
    public void unrestrictFriend(Friend friend) {
        friend.setFriendshipType("ALL");
    }

    /**
     * Unblocks a friend, adding them back to the friend list with the interaction level set to "ALL."
     *
     * @param friend The friend's username to be unblocked.
     */
    public void unblockFriend(Friend friend) {
        allFriends.add(friend);
        friend.setFriendshipType("ALL");
    }

    /**
     * Sets the interaction level with a friend to "CLOSE."
     *
     * @param friend The friend's username to be set as a close friend.
     */
    public void closeFriend(Friend friend) {
        friend.setFriendshipType("CLOSE");
    }

    /**
     * Retrieves the friend object with the provided username.
     *
     * @param username The friend's username to be retrieved.
     * @return The friend object with the provided username, or null if not found.
     */
    public Friend getFriend(String username) {
        for (var friend : allFriends) {
            if (friend.hasMatchingIdentity(username)) {
                return friend;
            }
        }
        return null;
    }

    /**
     * Updates the list of sent messages for the user.
     *
     * @param username The recipient's username.
     * @param message  The message to be added to the sent messages list.
     */
    public static void updateSentMessages(String username, Message message) {
        DataManager.getCurrentUser().getSent_messages().add(message);
    }

    /**
     * Updates the list of received messages for the user.
     *
     * @param username The sender's username.
     * @param message  The message to be added to the received messages list.
     */
    public static void updateReceivedMessages(String username, Message message) {
        DataManager.retrieveUser(username).getReceived_message().add(message);
    }

    /**
     * Retrieves a copy of the list of received messages for the user.
     *
     * @return A copy of the list of received messages for the user.
     */
    public ArrayList<Message> getReceived_message() {
        return received_message;
    }

    /**
     * Retrieves a copy of the list of sent messages for the user.
     *
     * @return A copy of the list of sent messages for the user.
     */
    public ArrayList<Message> getSent_messages() {
        return sent_messages;
    }

    public String getFriendType(Friend friend) {
        return friend.getFriendshipType();
    }

    /**
     * Overrides the toString method to provide a string representation of the user.
     *
     * @return A string representation of the user.
     */
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

    /**
     * Overrides the equals method to check for equality based on the username.
     *
     * @param o The object to compare.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        // Checking username is enough, as a username, email, and phone number only assigned once
        return Objects.equals(username, user.username);
    }
}
