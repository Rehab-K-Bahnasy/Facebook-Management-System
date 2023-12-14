package Friend;

import user.User;

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
    public void addFriend(User user, Friend username) {
        user.getAllFriends().add(username);
        username.setFriendshipType(FriendshipType.ALL);
    }

    public void removeFriend(User user, Friend username) {
        user.getAllFriends().remove(username);
    }

    public void restrictFriend( Friend username) {
        username.setFriendshipType(FriendshipType.RESTRICTED);
    }

    public void blockFriend( Friend username) {
        username.getAllFriends().remove(username);
        username.setFriendshipType(FriendshipType.BLOCKED);
    }

    public void unrestrictFriend(Friend username) {
        username.setFriendshipType(FriendshipType.ALL);
    }

    public void unblockFriend(User user,Friend username) {
        user.getAllFriends().add(username);
        username.setFriendshipType(FriendshipType.ALL);
    }
    public void closeFriend(Friend username){
        username.setFriendshipType(FriendshipType.CLOSE);
    }
    public void setFriendshipDate(Date friendshipDate) {
        this.friendship_date = (friendshipDate != null) ? friendshipDate : new Date();
    }
    public Date getFriendshipDate() {
        return friendship_date;
    }
}