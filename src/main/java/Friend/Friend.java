package Friend;

import user.User;

import java.time.LocalDate;
import java.util.*;

public class Friend extends User {
    private Date friendship_date;
    private FriendshipType friendship_type;
    public enum FriendshipType {
        ALL,
        CLOSE,
        RESTRICTED,
        BLOCKED
    }

    public Friend(Map userData) {
        super(userData);
    }
    public void setFriendshipType(FriendshipType friendshipType) {
        this.friendship_type = friendshipType;
    }
    public FriendshipType getFriendshipType() {
        return friendship_type;
    }

    public void setFriendshipDate(Date friendshipDate) {
        this.friendship_date = (friendshipDate != null) ? friendshipDate : new Date();
    }
    public Date getFriendshipDate() {
        return friendship_date;
    }
}