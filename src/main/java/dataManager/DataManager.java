package dataManager;

import user.User;


import java.util.ArrayList;
import java.util.Map;

public abstract class DataManager {
    protected static ArrayList<User> users = new ArrayList<>();

    public static User retrieveUser(String identifier) {
        for (var user : users) {
            if (user.hasMatchingIdentity(identifier))
                return user;
        }
        return null;
    }

    public static void addUser(Map userData) {
        users.add(new User(userData));
    }
}