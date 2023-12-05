package dataManager;

import user.User;

import java.util.ArrayList;

public class DataManager {
    protected static ArrayList<User> users = new ArrayList<>();
    protected final static String USERS_FILE_PATH = "src\\main\\resources\\dataManager\\users.json";

    public static User retrieveUser(String identifier) {
        for (var user : users) {
            if (user.hasMatchingIdentity(identifier))
                return user;
        }
        return null;
    }
}