package Friend;

import user.User;

import java.time.LocalDate;
import java.util.*;

/**
 * The Friend class represents a user's friend in the Welcome Login application.
 * It extends the User class to inherit basic user information and functionality.
 *
 * @author SOC-IO
 * @version 1.0
 */
public class Friend extends User {

    /**
     * The date when the friendship was established.
     */
    private LocalDate friendship_date;

    /**
     * The type of friendship, which can be ALL, CLOSE, RESTRICTED, or BLOCKED.
     */
    private String friendship_type;

    /**
     * Constructs a Friend object based on a User object.
     *
     * @param user The User object to be converted into a Friend object.
     */
    public Friend(User user) {
        super(user);
        friendship_type = "ALL";
    }

    /**
     * Sets the type of friendship for the Friend object.
     *
     * @param friendshipType The type of friendship to be set.
     */
    public void setFriendshipType(String friendshipType) {
        this.friendship_type = friendshipType;
    }

    /**
     * Gets the type of friendship for the Friend object.
     *
     * @return The type of friendship.
     */
    public String getFriendshipType() {
        return friendship_type;
    }

    /**
     * Gets the date when the friendship was established.
     *
     * @return The date when the friendship was established.
     */
    public LocalDate getFriendshipDate() {
        return friendship_date;
    }
}
