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
    private Date friendship_date;

    /**
     * The type of friendship, which can be ALL, CLOSE, RESTRICTED, or BLOCKED.
     */
    private FriendshipType friendship_type;

    /**
     * Enumeration representing different types of friendship.
     */
    public enum FriendshipType {
        ALL,
        CLOSE,
        RESTRICTED,
        BLOCKED
    }

    /**
     * Constructs a Friend object based on a User object.
     *
     * @param user The User object to be converted into a Friend object.
     */
    public Friend(User user) {
        super(user);
    }

    /**
     * Sets the type of friendship for the Friend object.
     *
     * @param friendshipType The type of friendship to be set.
     */
    public void setFriendshipType(FriendshipType friendshipType) {
        this.friendship_type = friendshipType;
    }

    /**
     * Gets the type of friendship for the Friend object.
     *
     * @return The type of friendship.
     */
    public FriendshipType getFriendshipType() {
        return friendship_type;
    }

    /**
     * Sets the date when the friendship was established.
     * If the provided date is null, the current date is set.
     *
     * @param friendshipDate The date when the friendship was established.
     */
    public void setFriendshipDate(Date friendshipDate) {
        this.friendship_date = (friendshipDate != null) ? friendshipDate : new Date();
    }

    /**
     * Gets the date when the friendship was established.
     *
     * @return The date when the friendship was established.
     */
    public Date getFriendshipDate() {
        return friendship_date;
    }
}
