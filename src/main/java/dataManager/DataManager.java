package dataManager;

import user.User;


import java.util.ArrayList;
import java.util.Map;

public abstract class DataManager {
    protected static ArrayList<User> users = new ArrayList<>();
    private static User current_user;

    public static User retrieveUser(String identifier) {
        for (var user : users) {
            if (user.hasMatchingIdentity(identifier))
                return user;
        }
        return null;
    }

    public static void setCurrentUser(User user) {
        current_user = user;
    }

    public static User getCurrentUser() {
        return current_user;
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void addUser(Map<Object, Object> user_map) {
        users.add(new User(user_map));
    }
}