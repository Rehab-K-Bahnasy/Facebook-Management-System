package dataManager;

import user.User;

import java.util.ArrayList;
import java.util.Map;

/**
 * The DataManager class is responsible for managing user data in the Welcome Login application.
 * It provides methods for retrieving, setting, and accessing user information.
 *
 * @author SOC-IO
 * @version 1.0
 */
public abstract class DataManager {

    /**
     * The list of registered users in the application.
     */
    protected static ArrayList<User> users = new ArrayList<>();

    /**
     * The currently logged-in user.
     */
    private static User current_user;

    /**
     * Retrieves a user based on their username, email, or phone number.
     *
     * @param identifier The identifier (username, email, or phone number) of the user to retrieve.
     * @return The User object corresponding to the given identifier, or null if not found.
     */
    public static User retrieveUser(String identifier) {
        for (var user : users) {
            if (user.hasMatchingIdentity(identifier))
                return user;
        }
        return null;
    }

    /**
     * Sets the current user of the application.
     *
     * @param user The User object representing the current user.
     */
    public static void setCurrentUser(User user) {
        current_user = user;
    }

    /**
     * Retrieves the currently logged-in user.
     *
     * @return The User object representing the currently logged-in user.
     */
    public static User getCurrentUser() {
        return current_user;
    }

    /**
     * Retrieves the list of all registered users in the application.
     *
     * @return The ArrayList of User objects representing all registered users.
     */
    public static ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Adds a new user to the list of registered users.
     *
     * @param user_map A Map containing user information (username, email, etc.).
     */
    public static void addUser(Map<Object, Object> user_map) {
        users.add(new User(user_map));
    }
}
